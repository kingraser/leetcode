package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountEqualAndDivisiblePairsInAnArray {
    /*
    Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j) where 0 <= i < j < n, such that nums[i] == nums[j] and (i * j) is divisible by k.

    Example 1:
    Input: nums = [3,1,2,2,2,1,3], k = 2
    Output: 4
    Explanation:
    There are 4 pairs that meet all the requirements:
    - nums[0] == nums[6], and 0 * 6 == 0, which is divisible by 2.
    - nums[2] == nums[3], and 2 * 3 == 6, which is divisible by 2.
    - nums[2] == nums[4], and 2 * 4 == 8, which is divisible by 2.
    - nums[3] == nums[4], and 3 * 4 == 12, which is divisible by 2.

    Example 2:
    Input: nums = [1,2,3,4], k = 1
    Output: 0
    Explanation: Since no value in nums is repeated, there are no pairs (i,j) that meet all the requirements.

    Constraints:
    1 <= nums.length <= 100
    1 <= nums[i], k <= 100
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {4, new int[]{3, 1, 2, 2, 2, 1, 3}, 2},
                {0, new int[]{1, 2, 3, 4}, 1}
        });
    }

    public int countPairs(int[] nums, int k) {
        int result = 0, map[][] = new int[101][101];
        for (int i = 0; i < nums.length; map[nums[i]][++map[nums[i]][0]] = i++)
            for (int j = 1; j <= map[nums[i]][0]; j++) if ((map[nums[i]][j] * i) % k == 0) result++;
        return result;
    }
}
