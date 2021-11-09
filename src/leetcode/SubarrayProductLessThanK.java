package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SubarrayProductLessThanK {

    /*
    Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

    Example 1:
    Input: nums = [10,5,2,6], k = 100
    Output: 8
    Explanation: The 8 subarrays that have product less than 100 are:
    [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
    Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

    Example 2:
    Input: nums = [1,2,3], k = 0
    Output: 0

    Constraints:
    1 <= nums.length <= 3 * 10^4
    1 <= nums[i] <= 1000
    0 <= k <= 10^6
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {8, new int[]{10, 5, 2, 6}, 100},
                {0, new int[]{1, 2, 3}, 0}
        });
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        int result = 0;
        for (int left = 0, right = 0, product = 1; right < nums.length; result += ++right - left)
            for (product *= nums[right]; left <= right && product >= k; ) product /= nums[left++];
        return result;
    }
}
