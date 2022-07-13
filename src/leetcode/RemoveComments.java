package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class RemoveComments {
    /*
    Given a C++ program, remove comments from it. The program source is an array of strings `source` where source[i] is the ith line of the source code. This represents the result of splitting the original source code string by the newline character '\n'.
    In C++, there are two types of comments, line comments, and block comments.
    The string "//" denotes a line comment, which represents that it and the rest of the characters to the right of it in the same line should be ignored.
    The string "/＊" denotes a block comment, which represents that all characters until the next (non-overlapping) occurrence of "＊/" should be ignored. (Here, occurrences happen in reading order: line by line from left to right.) To be clear, the string "/＊/" does not yet end the block comment, as the ending would be overlapping the beginning.
    The first effective comment takes precedence over others.
    For example, if the string "//" occurs in a block comment, it is ignored.
    Similarly, if the string "/＊" occurs in a line or block comment, it is also ignored.
    If a certain line of code is empty after removing comments, you must not output that line: each string in the answer list will be non-empty.
    There will be no control characters, single quote, or double quote characters.
    For example, `source = "string s = "/＊ Not a comment. ＊/";"` will not be a test case.
    Also, nothing else such as defines or macros will interfere with the comments.
    It is guaranteed that every open block comment will eventually be closed, so `"/＊"` outside a line or block comment always starts a new comment.
    Finally, implicit newline characters can be deleted by block comments. Please see the examples below for details.
    After removing the comments from the source code, return the source code in the same format.

    Example 1:
    Input: source = ["/＊Test program ＊/", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/＊ This is a test", "   multiline  ", "   comment for ", "   testing ＊/", "a = b + c;", "}"]
    Output: ["int main()","{ ","  ","int a, b, c;","a = b + c;","}"]
    Explanation: The line by line code is visualized as below:
    /＊Test program ＊/
    int main()
    {
        // variable declaration
        int a, b, c;
    /＊ This is a test
    multiline
    comment for
    testing ＊/
        a = b + c;
    }
    The string /＊ denotes a block comment, including line 1 and lines 6-9. The string // denotes line 4 as comments.
    The line by line output code is visualized as below:
    int main()
    {

    int a, b, c;
    a = b + c;
    }

    Example 2:
    Input: source = ["a/＊comment", "line", "more_comment＊/b"]
    Output: ["ab"]
    Explanation: The original source string is "a/＊comment\nline\nmore_comment＊/b", where we have bolded the newline characters.  After deletion, the implicit newline characters are deleted, leaving the string "ab", which when delimited by newline characters becomes ["ab"].

    Constraints:
    1 <= source.length <= 100
    0 <= source[i].length <= 80
    source[i] consists of printable ASCII characters.
    Every open block comment is eventually closed.
    There are no single-quote or double-quote in the input.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of("main() { ", "  int a = 1;  return 0;", "} "), new String[]{"main() { ", "  int a = 1; /* Its comments here ", "", "  ", "  */ return 0;", "} "}},
                {List.of("int main()", "{ ", "  ", "int a, b, c;", "a = b + c;", "}"), new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"}},
                {List.of("ab"), new String[]{"a/*comment", "line", "more_comment*/b"}}
        });
    }

    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int sIdx = 0, idx = 0, inBlock = 0, blockIdx, lineIdx; sIdx < source.length; )
            if (idx >= source[sIdx].length()) {
                sIdx++;
                idx = 0;
                if (inBlock == 1 || sb.length() == 0) continue;
                result.add(sb.toString());
                sb.setLength(0);
            } else if (inBlock == 1) {
                if ((blockIdx = source[sIdx].indexOf("*/", idx)) != -1) {
                    inBlock = 0;
                    idx = blockIdx + 2;
                } else idx = source[sIdx].length();
            } else if ((blockIdx = source[sIdx].indexOf("/*", idx)) != -1 & ((lineIdx = source[sIdx].indexOf("//", idx)) == -1 || blockIdx < lineIdx)) {
                inBlock = 1;
                sb.append(source[sIdx], idx, blockIdx);
                idx = blockIdx + 2;
            } else {
                sb.append(source[sIdx], idx, lineIdx == -1 ? source[sIdx].length() : lineIdx);
                idx = source[sIdx].length();
            }
        return result;
    }

    public List<String> removeCommentsII(String[] source) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int sIdx = 0, inBlock = 0; sIdx < source.length; sIdx++) {
            for (int i = 0, chars[] = source[sIdx].chars().toArray(); i < chars.length; i++)
                if (inBlock == 1) {
                    if (chars[i] != '*' || i == chars.length - 1 || source[sIdx].charAt(i + 1) != '/') continue;
                    inBlock = 0;
                    i++;
                } else if (chars[i] == '/' && i < chars.length - 1) {
                    if (chars[i + 1] == '*') {
                        inBlock = 1;
                        i++;
                    } else if (chars[i + 1] == '/') break;
                    else sb.append((char) chars[i]);
                } else sb.append((char) chars[i]);
            if (inBlock == 1 || sb.length() == 0) continue;
            res.add(sb.toString());
            sb.setLength(0);
        }
        return res;
    }
}
