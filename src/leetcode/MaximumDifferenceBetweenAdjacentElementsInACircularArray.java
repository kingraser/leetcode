package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class MaximumDifferenceBetweenAdjacentElementsInACircularArray {
    /*
    Given a circular array nums, find the maximum absolute difference between adjacent elements.
    Note: In a circular array, the first and last elements are adjacent.

    Example 1:
    Input: nums = [1,2,4]
    Output: 3
    Explanation:
    Because nums is circular, nums[0] and nums[2] are adjacent. They have the maximum absolute difference of |4 - 1| = 3.

    Example 2:
    Input: nums = [-5,-10,-5]
    Output: 5
    Explanation:
    The adjacent elements nums[0] and nums[1] have the maximum absolute difference of |-5 - (-10)| = 5.

    Constraints:
        2 <= nums.length <= 100
        -100 <= nums[i] <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {3, new int[]{1, 2, 4}},
                {5, new int[]{-5, -10, -5}},
        });
    }

    public int maxAdjacentDistance(int[] nums) {
        int result = Math.abs(nums[0] - nums[1]), i = 1, last = nums.length - 1;
        while (i < last) result = Math.max(result, Math.abs(nums[i] - nums[++i]));
        return Math.max(result, Math.abs(nums[0] - nums[last]));
    }
}
