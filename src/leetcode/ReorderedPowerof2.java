package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class ReorderedPowerof2 {
    /*
    You are given an integer n. We reorder the digits in any order (including the original order) such that the leading digit is not zero.
    Return true if and only if we can do this so that the resulting number is a power of two.

    Example 1:
    Input: n = 1
    Output: true

    Example 2:
    Input: n = 10
    Output: false

    Example 3:
    Input: n = 16
    Output: true

    Example 4:
    Input: n = 24
    Output: false

    Example 5:
    Input: n = 46
    Output: true

    Constraints:
    1 <= n <= 10^9
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, 1},
                {false, 10},
                {true, 16},
                {false, 24},
                {true, 46}
        });
    }

    public boolean reorderedPowerOf2(int n) {
        for (int i = 1, hash[] = hash(n); i <= 1_000_000_000; i <<= 1) if (Arrays.equals(hash, hash(i))) return true;
        return false;
    }

    int[] hash(int n) {
        int[] result = new int[10];
        for (; n > 0; n /= 10) result[n % 10]++;
        return result;
    }
}
