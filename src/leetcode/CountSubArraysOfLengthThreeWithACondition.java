package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class CountSubArraysOfLengthThreeWithACondition {
    /*
    Given an integer array nums, return the number of subArrays of length 3 such that the sum of the first and third numbers equals exactly half of the second number.

    Example 1:
    Input: nums = [1,2,1,4,1]
    Output: 1
    Explanation:
    Only the subarray [1,4,1] contains exactly 3 elements where the sum of the first and third numbers equals half the middle number.

    Example 2:
    Input: nums = [1,1,1]
    Output: 0
    Explanation:
    [1,1,1] is the only subarray of length 3. However, its first and third numbers do not add to half the middle number.

    Constraints:
        3 <= nums.length <= 100
        -100 <= nums[i] <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, new int[]{1, 2, 1, 4, 1}},
                {0, new int[]{1, 1, 1}},
                {0, new int[]{2, -7, -6}},
        });
    }

    public int countSubarrays(int[] nums) {
        int result = 0;
        for (int i = 0, last = nums.length - 2; i < last; i++)
            if (nums[i + 1] == (nums[i] + nums[i + 2]) << 1) result++;
        return result;
    }
}
