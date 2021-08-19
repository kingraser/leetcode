package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumNumberofWordsYouCanType {
    /*
    There is a malfunctioning keyboard where some letter keys do not work. All other keys on the keyboard work properly.
    Given a string text of words separated by a single space (no leading or trailing spaces) and a string brokenLetters of all distinct letter keys that are broken, return the number of words in text you can fully type using this keyboard.

    Example 1:
    Input: text = "hello world", brokenLetters = "ad"
    Output: 1
    Explanation: We cannot type "world" because the 'd' key is broken.

    Example 2:
    Input: text = "leet code", brokenLetters = "lt"
    Output: 1
    Explanation: We cannot type "leet" because the 'l' and 't' keys are broken.

    Example 3:
    Input: text = "leet code", brokenLetters = "e"
    Output: 0
    Explanation: We cannot type either word because the 'e' key is broken.

    Constraints:
    1 <= text.length <= 10^4
    0 <= brokenLetters.length <= 26
    text consists of words separated by a single space without any leading or trailing spaces.
    Each word only consists of lowercase English letters.
    brokenLetters consists of distinct lowercase English letters.
    */
    public int canBeTypedWords(String text, String brokenLetters) {
        int result = 0, brokenLettersMap[] = new int[128];
        for (int i = 0, length = brokenLetters.length(); i < length; ) brokenLettersMap[brokenLetters.charAt(i++)]++;
        for (int start = 0, length = text.length(); start < length; )
            if ((start = canNotBeTyped(text, start, length, brokenLettersMap)) > 0) result++;
            else start = -start;
        return result;
    }

    int canNotBeTyped(String text, int start, int length, int[] brokenLetters) {
        boolean canBeTyped = true;
        for (char c; start < length && (c = text.charAt(start++)) != ' '; )
            if (canBeTyped && brokenLetters[c] > 0) canBeTyped = false;
        return canBeTyped ? start : -start;
    }

    @Test
    public void test() throws NoSuchMethodException {
        TestUtil.testEquals(
                new Object[][]{
                        {1, "hello world", "ad"},
                        {1, "leet code", "lt"},
                        {0, "leet code", "e"},
                });
    }
}
