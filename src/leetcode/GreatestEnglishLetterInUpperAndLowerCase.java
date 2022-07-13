package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class GreatestEnglishLetterInUpperAndLowerCase {
    /*
    Given a string of English letters s, return the greatest English letter which occurs as both a lowercase and uppercase letter in s. The returned letter should be in uppercase. If no such letter exists, return an empty string.
    An English letter b is greater than another letter a if b appears after a in the English alphabet.

    Example 1:
    Input: s = "lEeTcOdE"
    Output: "E"
    Explanation:
    The letter 'E' is the only letter to appear in both lower and upper case.

    Example 2:
    Input: s = "arRAzFif"
    Output: "R"
    Explanation:
    The letter 'R' is the greatest letter to appear in both lower and upper case.
    Note that 'A' and 'F' also appear in both lower and upper case, but 'R' is greater than 'F' or 'A'.

    Example 3:
    Input: s = "AbCdEfGhIjK"
    Output: ""
    Explanation:
    There is no letter that appears in both lower and upper case.

    Constraints:
    1 <= s.length <= 1000
    s consists of lowercase and uppercase English letters.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"E", "lEeTcOdE"},
                {"R", "arRAzFif"},
                {"", "AbCdEfGhIjK"}
        });
    }

    public String greatestLetter(String s) {
        int[] letters = new int[26];
        for (char c : s.toCharArray())
            if (c >= 'a') letters[c - 'a'] |= 2;
            else letters[c - 'A'] |= 1;
        for (int i = letters.length - 1; i >= 0; i--)
            if (letters[i] == 3) return Character.toString('A' + i);
        return "";
    }
}
