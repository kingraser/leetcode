package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindNUniqueIntegersSumuptoZero {
    /*
    Given an integer n, return any array containing n unique integers such that they add up to 0.

    Example 1:
    Input: n = 5
    Output: [-7,-1,1,3,4]
    Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].

    Example 2:
    Input: n = 3
    Output: [-1,0,1]

    Example 3:
    Input: n = 1
    Output: [0]

    Constraints:
    1 <= n <= 1000
    */

    public int[] sumZero(int n) {
        int[] result = new int[n];
        for (int i = (n & 1) == 0 ? 0 : 1, j = 1; i < n; result[i++] = -j++) result[i++] = j;
        return result;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[1], sumZero(1));
        Assert.assertArrayEquals(new int[]{1, -1}, sumZero(2));
        Assert.assertArrayEquals(new int[]{0, 1, -1}, sumZero(3));
        Assert.assertArrayEquals(new int[]{1, -1, 2, -2}, sumZero(4));
    }
}
