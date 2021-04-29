package leetcode;

/**
 * @author Wit
 */
public class MinimumOperationstoMaketheArrayIncreasing {
    /*
    Return the minimum number of operations needed to make nums strictly increasing.
    An array nums is strictly increasing if nums[i] < nums[i+1] for all 0 <= i < nums.length - 1. An array of length 1 is trivially strictly increasing.

    Example 1:
    Input: nums = [1,1,1]
    Output: 3
    Explanation: You can do the following operations:
    1) Increment nums[2], so nums becomes [1,1,2].
    2) Increment nums[1], so nums becomes [1,2,2].
    3) Increment nums[2], so nums becomes [1,2,3].

    Example 2:
    Input: nums = [1,5,2,4,1]
    Output: 14

    Example 3:
    Input: nums = [8]
    Output: 0

    Constraints:
    1 <= nums.length <= 5000
    1 <= nums[i] <= 10^4
    */
    public int minOperations(int[] nums) {
        int operations = 0;
        for (int i = 1, value; i < nums.length; i++)
            if (nums[i] < (value = nums[i - 1] + 1)) {
                operations += value - nums[i];
                nums[i] = value;
            }
        return operations;
    }
}
