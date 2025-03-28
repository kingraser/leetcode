package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class MaximumUniqueSubArraySumAfterDeletion {
    /*
    You are given an integer array nums.
    You are allowed to delete any number of elements from nums without making it empty.
    After performing the deletions, select a of nums such that:
        All elements in the subarray are unique.
        The sum of the elements in the subarray is maximized.
    Return the maximum sum of such a subarray.

    Example 1:
    Input: nums = [1,2,3,4,5]
    Output: 15
    Explanation:
    Select the entire array without deleting any element to obtain the maximum sum.

    Example 2:
    Input: nums = [1,1,0,1,1]
    Output: 1
    Explanation:
    Delete the element nums[0] == 1, nums[1] == 1, nums[2] == 0, and nums[3] == 1. Select the entire array [1] to obtain the maximum sum.

    Example 3:
    Input: nums = [1,2,-1,-2,1,0,-1]
    Output: 3
    Explanation:
    Delete the elements nums[2] == -1 and nums[3] == -2, and select the subarray [2, 1] from [1, 2, 1, 0, -1] to obtain the maximum sum.

    Constraints:
        1 <= nums.length <= 100
        -100 <= nums[i] <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {-100, new int[]{-100}},
                {15, new int[]{1, 2, 3, 4, 5}},
                {1, new int[]{1, 1, 0, 1, 1}},
                {3, new int[]{1, 2, -1, -2, 1, 0, -1}},
        });
    }

    public int maxSum(int[] nums) {
        int result = 0, count[] = new int[101], maxNegative = -101;
        for (int num : nums)
            if (num < 0) maxNegative = Math.max(maxNegative, num);
            else if (count[num]++ == 0) result += num;
        return result == 0 && count[0] == 0 ? maxNegative : result;
    }
}
