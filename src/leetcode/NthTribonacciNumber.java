package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class NthTribonacciNumber {
    /*
    The Tribonacci sequence Tn is defined as follows:
    T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
    Given n, return the value of Tn.

    Example 1:
    Input: n = 4
    Output: 4
    Explanation:
    T_3 = 0 + 1 + 1 = 2
    T_4 = 1 + 1 + 2 = 4

    Example 2:
    Input: n = 25
    Output: 1389537

    Constraints:
    0 <= n <= 37
    The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
    */

    static final int[] A = new int[38];

    static {
        A[1] = 1;
        A[2] = 1;
        for (int i = 3; i < 38; i++) A[i] = A[i - 1] + A[i - 2] + A[i - 3];
    }

    public int tribonacci(int n) {
        return A[n];
    }

    @Test
    public void test() {
        Assert.assertEquals(1389537, tribonacci(25));
    }

}
