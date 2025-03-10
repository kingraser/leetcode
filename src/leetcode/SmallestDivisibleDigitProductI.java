package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class SmallestDivisibleDigitProductI {
    /*
    You are given two integers n and t.
    Return the smallest number greater than or equal to n such that the product of its digits is divisible by t.

    Example 1:
    Input: n = 10, t = 2
    Output: 10
    Explanation:
    The digit product of 10 is 0, which is divisible by 2, making it the smallest number greater than or equal to 10 that satisfies the condition.

    Example 2:
    Input: n = 15, t = 3
    Output: 16
    Explanation:
    The digit product of 16 is 6, which is divisible by 3, making it the smallest number greater than or equal to 15 that satisfies the condition.

    Constraints:
        1 <= n <= 100
        1 <= t <= 10
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {10, 10, 2},
                {16, 15, 3},
        });
    }

    /*
    You have to check at most 10 numbers.
    Apply a brute-force approach by checking each possible number.
    */
    public int smallestNumber(int n, int t) {
        for (; ; n++) if (getDigitProduct(n) % t == 0) return n;
    }

    private int getDigitProduct(int n) {
        int result = 1;
        for (; n != 0 && result != 0; n /= 10) result *= n % 10;
        return result;
    }

}
