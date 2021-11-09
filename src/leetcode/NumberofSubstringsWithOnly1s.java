package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class NumberofSubstringsWithOnly1s {
    /*
    Given a binary string s, return the number of substrings with all characters 1's. Since the answer may be too large, return it modulo 109 + 7.

    Example 1:
    Input: s = "0110111"
    Output: 9
    Explanation: There are 9 substring in total with only 1's characters.
    "1" -> 5 times.
    "11" -> 3 times.
    "111" -> 1 time.

    Example 2:
    Input: s = "101"
    Output: 2
    Explanation: Substring "1" is shown 2 times in s.

    Example 3:
    Input: s = "111111"
    Output: 21
    Explanation: Each substring contains only 1's characters.

    Example 4:
    Input: s = "000"
    Output: 0

    Constraints:
    1 <= s.length <= 10^5
    s[i] is either '0' or '1'.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {9, "0110111"},
                {2, "101"},
                {21, "111111"},
                {0, "000"}
        });
    }

    static final long MASK = 1_000_000_000 + 7;

    public int numSub(String s) {
        long result = 0, ones = 0;
        for (char c : s.toCharArray())
            if (c == '0') ones = 0;
            else result += ++ones;
        return (int) (result % MASK);
    }
}
