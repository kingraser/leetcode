package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class MaximumProductOfTwoDigits {
    /*
    You are given a positive integer n.
    Return the maximum product of any two digits in n.
    Note: You may use the same digit twice if it appears more than once in n.

    Example 1:
    Input: n = 31
    Output: 3
    Explanation:
        The digits of n are [3, 1].
        The possible products of any two digits are: 3 * 1 = 3.
        The maximum product is 3.

    Example 2:
    Input: n = 22
    Output: 4
    Explanation:
        The digits of n are [2, 2].
        The possible products of any two digits are: 2 * 2 = 4.
        The maximum product is 4.

    Example 3:
    Input: n = 124
    Output: 8
    Explanation:
        The digits of n are [1, 2, 4].
        The possible products of any two digits are: 1 * 2 = 2, 1 * 4 = 4, 2 * 4 = 8.
        The maximum product is 8.

    Constraints:
        10 <= n <= 10^9
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {0, 20},
                {3, 31},
                {4, 22},
                {8, 124},
        });
    }

    public int maxProduct(int n) {
        int max = -1, secondMax = -1;
        for (int remainder; n > 0; n /= 10)
            if ((remainder = n % 10) >= max) {
                secondMax = max;
                max = remainder;
            } else if (remainder > secondMax) secondMax = remainder;
        return max * secondMax;
    }
}
