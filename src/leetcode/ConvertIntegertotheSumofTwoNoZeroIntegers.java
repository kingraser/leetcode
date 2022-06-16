package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ConvertIntegertotheSumofTwoNoZeroIntegers {
    /*
    Given an integer n. No-Zero integer is a positive integer which doesn't contain any 0 in its decimal representation.
    Return a list of two integers [A, B] where:
    A and B are No-Zero integers.
    A + B = n
    It's guarateed that there is at least one valid solution. If there are many valid solutions you can return any of them.

    Example 1:
    Input: n = 2
    Output: [1,1]
    Explanation: A = 1, B = 1. A + B = n and both A and B don't contain any 0 in their decimal representation.

    Example 2:
    Input: n = 11
    Output: [2,9]

    Example 3:
    Input: n = 10000
    Output: [1,9999]

    Example 4:
    Input: n = 69
    Output: [1,68]

    Example 5:
    Input: n = 1010
    Output: [11,999]

    Constraints:
    2 <= n <= 10^4
    */
    public int[] getNoZeroIntegers(int n) {
        int a = 0, b = 0, step = 1;
        for (int d; n > 0; step *= 10)
            if (((d = n % 10) == 0 || d == 1) & (n /= 10) > 0) {
                a += step * ++d;
                b += step * 9;
                n--;
            } else {
                a += step;
                b += step * --d;
            }
        return new int[]{a, b};
    }

    @Test
    public void test() {
        TestUtil.testEquals(
                new Object[][]{
                        {new int[]{1, 1}, 2},
                        {new int[]{2, 9}, 11},
                        {new int[]{1111, 8889}, 10000},
                        {new int[]{11, 58}, 69},
                        {new int[]{111, 899}, 1010},
                }
        );
    }
}
