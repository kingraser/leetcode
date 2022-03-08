package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class BinarySubarraysWithSum {
    /*
    Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
    A subarray is a contiguous part of the array.

    Example 1:
    Input: nums = [1,0,1,0,1], goal = 2
    Output: 4
    Explanation: The 4 subarrays are bolded and underlined below:
    [1,0,1,0,1]
    [1,0,1,0,1]
    [1,0,1,0,1]
    [1,0,1,0,1]

    Example 2:
    Input: nums = [0,0,0,0,0], goal = 0
    Output: 15

    Constraints:
    1 <= nums.length <= 3 * 10^4
    nums[i] is either 0 or 1.
    0 <= goal <= nums.length
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {4, new int[]{1, 0, 1, 0, 1}, 2},
                {15, new int[]{0, 0, 0, 0, 0}, 0}
        });
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    int atMost(int[] nums, int goal) {
        int result = 0;
        if (goal >= 0) for (int left = 0, right = 0; right < nums.length; result += ++right - left)
            for (goal -= nums[right]; goal < 0; ) goal += nums[left++];
        return result;
    }
}
