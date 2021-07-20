package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ConsecutiveCharacters {
    /*
    Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one unique character.
    Return the power of the string.

    Example 1:
    Input: s = "leetcode"
    Output: 2
    Explanation: The substring "ee" is of length 2 with the character 'e' only.

    Example 2:
    Input: s = "abbcccddddeeeeedcba"
    Output: 5
    Explanation: The substring "eeeee" is of length 5 with the character 'e' only.

    Example 3:
    Input: s = "triplepillooooow"
    Output: 5

    Example 4:
    Input: s = "hooraaaaaaaaaaay"
    Output: 11

    Example 5:
    Input: s = "tourist"
    Output: 1

    Constraints:
    1 <= s.length <= 500
    s contains only lowercase English letters.
    */
    public int maxPower(String s) {
        int result = 1;
        for (int i = 1, length = s.length(), current = 1, lastChar = s.charAt(0), newChar; i < length; lastChar = newChar)
            if (lastChar == (newChar = s.charAt(i++))) result = Integer.max(result, ++current);
            else current = 1;
        return result;
    }

    @Test
    public void test() throws NoSuchMethodException {
        TestUtil.testEquals(this,
                new Object[][]{
                        {2, "leetcode"},
                        {5, "abbcccddddeeeeedcba"},
                        {5, "triplepillooooow"},
                        {11, "hooraaaaaaaaaaay"},
                        {1, "tourist"}
                });
    }
}
