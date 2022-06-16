package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class BinaryStringWithSubstringsRepresenting1ToN {
    /*
    Given a binary string s and a positive integer n, return true if the binary representation of all the integers in the range [1, n] are substrings of s, or false otherwise.
    A substring is a contiguous sequence of characters within a string.

    Example 1:
    Input: s = "0110", n = 3
    Output: true

    Example 2:
    Input: s = "0110", n = 4
    Output: false

    Constraints:
    1 <= s.length <= 1000
    s[i] is either '0' or '1'.
    1 <= n <= 10^9
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, "0110", 3},
                {false, "0110", 4}
        });
    }

    public boolean queryString(String s, int n) {
        for (int i = n, half = n >> 1; i > half; )
            if (!s.contains(Integer.toBinaryString(i--))) return false;
        return true;
    }
}
