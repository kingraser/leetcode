package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class BeautifulArray {
/*
An array nums of length n is beautiful if:
    nums is a permutation of the integers in the range [1, n].
    For every 0 <= i < j < n, there is no index k with i < k < j where 2 * nums[k] == nums[i] + nums[j].
Given the integer n, return any beautiful array nums of length n. There will be at least one valid answer for the given n.

Example 1:
Input: n = 4
Output: [2,1,4,3]

Example 2:
Input: n = 5
Output: [3,1,2,5,4]

Constraints:
    1 <= n <= 1000
*/

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{1, 3, 2, 4}, 4},
                {new int[]{1, 5, 3, 2, 4}, 5},
        });
    }

    public int[] beautifulArray(int n) {
        int[] result = new int[]{1};
        for (int tempSize = 0, temp[]; result.length < n; result = temp, tempSize = 0)
            if ((result.length << 1) <= n) {
                temp = new int[result.length << 1];
                for (int i : result) temp[tempSize++] = (i << 1) - 1;
                for (int i : result) temp[tempSize++] = i << 1;
            } else {
                temp = new int[n];
                for (int i : result) if ((i = (i << 1) - 1) <= n) temp[tempSize++] = i;
                for (int i : result) if ((i <<= 1) <= n) temp[tempSize++] = i;
            }
        return result;
    }
}
