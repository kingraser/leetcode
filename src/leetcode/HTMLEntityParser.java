package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class HTMLEntityParser {
    /*
    HTML entity parser is the parser that takes HTML code as input and replace all the entities of the special characters by the characters itself.
    The special characters and their entities for HTML are:
    Quotation Mark: the entity is &quot; and symbol character is ".
    Single Quote Mark: the entity is &apos; and symbol character is '.
    Ampersand: the entity is &amp; and symbol character is &.
    Greater Than Sign: the entity is &gt; and symbol character is >.
    Less Than Sign: the entity is &lt; and symbol character is <.
    Slash: the entity is &frasl; and symbol character is /.
    Given the input text string to the HTML parser, you have to implement the entity parser.
    Return the text after replacing the entities by the special characters.

    Example 1:
    Input: text = "&amp; is an HTML entity but &ambassador; is not."
    Output: "& is an HTML entity but &ambassador; is not."
    Explanation: The parser will replace the &amp; entity by &

    Example 2:
    Input: text = "and I quote: &quot;...&quot;"
    Output: "and I quote: \"...\""

    Constraints:
    1 <= text.length <= 10^5
    The string may contain any possible characters out of all the 256 ASCII characters.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"& is an HTML entity but &ambassador; is not.", "&amp; is an HTML entity but &ambassador; is not."},
                {"and I quote: \"...\"", "and I quote: &quot;...&quot;"}
        });
    }

    static final char[] QUOT = "quot".toCharArray(), APOS = "apos".toCharArray(), AMP = "amp".toCharArray(), GT = "gt".toCharArray(), LT = "lt".toCharArray(), FRASL = "frasl".toCharArray();

    public String entityParser(String text) {
        char chars[] = text.toCharArray(), c;
        int writeIndex = 0, readIndex = 0, and = -1;
        for (int len; readIndex < chars.length; readIndex++) {
            if ((c = chars[readIndex]) == '&') {
                if (and >= 0) while (and < readIndex) chars[writeIndex++] = chars[and++];
                and = readIndex;
            } else if (c == ';' && and >= 0) {
                if ((len = readIndex - and) == 3) {
                    if (match(chars, and, GT)) c = '>';
                    else if (match(chars, and, LT)) c = '<';
                } else if (len == 4 && match(chars, and, AMP)) c = '&';
                else if (len == 5) {
                    if (match(chars, and, QUOT)) c = '"';
                    else if (match(chars, and, APOS)) c = '\'';
                } else if (len == 6 && match(chars, and, FRASL)) c = '/';
                if (c == ';') while (and < readIndex) chars[writeIndex++] = chars[and++];
                and = -1;
            }
            if (and == -1) chars[writeIndex++] = c;
        }
        if (and >= 0) while (and < chars.length) chars[writeIndex++] = chars[and++];
        return new String(chars, 0, writeIndex);
    }

    boolean match(char[] chars, int start, char[] target) {
        for (int i = 0; i < target.length; ) if (chars[++start] != target[i++]) return false;
        return true;
    }
}
