package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class RemoveZerosInDecimalRepresentation {
    /*
    You are given a positive integer n.
    Return the integer obtained by removing all zeros from the decimal representation of n.

    Example 1:
    Input: n = 1020030
    Output: 123
    Explanation:
    After removing all zeros from 1020030, we get 123.

    Example 2:
    Input: n = 1
    Output: 1
    Explanation:
    1 has no zero in its decimal representation. Therefore, the answer is 1.

    Constraints:
        1 <= n <= 10^15
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {123L, 1020030L},
                {1L, 1L},
        });
    }

    public long removeZeros(long n) {
        long result = 0, digit, base = 1;
        while (n != 0) {
            if ((digit = n % 10) != 0) {
                result += base * digit;
                base *= 10;
            }
            n /= 10;
        }
        return result;
    }
}
