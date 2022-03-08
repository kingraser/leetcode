package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountIntegersWithEvenDigitSum {
    /*
    Given a positive integer num, return the number of positive integers less than or equal to num whose digit sums are even.
    The digit sum of a positive integer is the sum of all its digits.

    Example 1:
    Input: num = 4
    Output: 2
    Explanation:
    The only integers less than or equal to 4 whose digit sums are even are 2 and 4.

    Example 2:
    Input: num = 30
    Output: 14
    Explanation:
    The 14 integers less than or equal to 30 whose digit sums are even are
    2, 4, 6, 8, 11, 13, 15, 17, 19, 20, 22, 24, 26, and 28.

    Constraints:
    1 <= num <= 1000
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, 4},
                {14, 30}
        });
    }

    public int countEven(int num) {
        return (num - ((getDigitSum(num) & 1) == 0 ? 0 : 1)) >> 1;
    }

    int getDigitSum(int num) {
        int result = 0;
        for (; num != 0; num /= 10) result += num % 10;
        return result;
    }
}
