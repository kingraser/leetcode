package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CheckifNumberisaSumofPowersofThree {
    /*
    Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.
    An integer y is a power of three if there exists an integer x such that y == 3x.

    Example 1:
    Input: n = 12
    Output: true
    Explanation: 12 = 31 + 32

    Example 2:
    Input: n = 91
    Output: true
    Explanation: 91 = 30 + 32 + 34

    Example 3:
    Input: n = 21
    Output: false

    Constraints:
    1 <= n <= 10^7
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, 12}, {true, 91}, {false, 21}
        });
    }

    public boolean checkPowersOfThree(int n) {
        for (; n > 0; n /= 3)
            if (n % 3 == 2) return false;
        return true;
    }
}
