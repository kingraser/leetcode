package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class LetterCasePermutation {
    /*
    Given a string s, we can transform every letter individually to be lowercase or uppercase to create another string.
    Return a list of all possible strings we could create. You can return the output in any order.

    Example 1:
    Input: s = "a1b2"
    Output: ["a1b2","a1B2","A1b2","A1B2"]

    Example 2:
    Input: s = "3z4"
    Output: ["3z4","3Z4"]

    Example 3:
    Input: s = "12345"
    Output: ["12345"]

    Example 4:
    Input: s = "0"
    Output: ["0"]

    Constraints:
    s will be a string with length between 1 and 12.
    s will consist only of letters or digits.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of("a1b2", "a1B2", "A1B2", "A1b2"), "a1b2"},
                {List.of("3z4", "3Z4"), "3z4"},
                {List.of("12345"), "12345"},
                {List.of("0"), "0"}
        });
    }

    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>(1 << s.length());
        letterCasePermutation(s.toCharArray(), result, 0);
        return result;
    }

    private void letterCasePermutation(char[] chars, List<String> result, int index) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }
        int otherCase = getOtherCase(chars[index] - 'A');
        if (otherCase != 0) {
            letterCasePermutation(chars, result, index + 1);
            chars[index] = (char) otherCase;
        }
        letterCasePermutation(chars, result, index + 1);
    }

    /*
    ascii code
    a 97
    A 65
    */
    private int getOtherCase(int index) {
        return isLetter(index) ? 'a' + index : isLetter(index -= 32) ? 'A' + index : 0;
    }

    private boolean isLetter(int index) {
        return index >= 0 && index < 26;
    }
}


