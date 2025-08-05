package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class TrionicArrayII {
    /*
    You are given an integer array nums of length n.
    A trionic subarray is a contiguous subarray nums[l...r] (with 0 <= l < r < n) for which there exist indices l < p < q < r such that:
        nums[l...p] is strictly increasing,
        nums[p...q] is strictly decreasing,
        nums[q...r] is strictly increasing.
    Return the maximum sum of any trionic subarray in nums.

    Example 1:
    Input: nums = [0,-2,-1,-3,0,2,-1]
    Output: -4
    Explanation:
    Pick l = 1, p = 2, q = 3, r = 5:
        nums[l...p] = nums[1...2] = [-2, -1] is strictly increasing (-2 < -1).
        nums[p...q] = nums[2...3] = [-1, -3] is strictly decreasing (-1 > -3)
        nums[q...r] = nums[3...5] = [-3, 0, 2] is strictly increasing (-3 < 0 < 2).
        Sum = (-2) + (-1) + (-3) + 0 + 2 = -4.

    Example 2:
    Input: nums = [1,4,2,7]
    Output: 14
    Explanation:
    Pick l = 0, p = 1, q = 2, r = 3:
        nums[l...p] = nums[0...1] = [1, 4] is strictly increasing (1 < 4).
        nums[p...q] = nums[1...2] = [4, 2] is strictly decreasing (4 > 2).
        nums[q...r] = nums[2...3] = [2, 7] is strictly increasing (2 < 7).
        Sum = 1 + 4 + 2 + 7 = 14.

    Constraints:
        4 <= n = nums.length <= 10^5
        -10^9 <= nums[i] <= 10^9
        It is guaranteed that at least one trionic subarray exists.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {-4L, new int[]{0, -2, -1, -3, 0, 2, -1}}, {14L, new int[]{1, 4, 2, 7}},
                });
    }

    public long maxSumTrionic(int[] nums) {
        long result = Long.MIN_VALUE, prefixSum = nums[0];
        for (int left = 0, p = 0, q = 0, right = 1; right < nums.length; right++) {
            prefixSum += nums[right];
            if (nums[right - 1] == nums[right]) prefixSum = nums[left = right];
            else if (nums[right - 1] > nums[right]) {
                if (right > 1 && nums[right - 2] < nums[right - 1]) { // flip
                    p = right - 1;
                    while (left < q) prefixSum -= nums[left++];
                    while (left + 1 < p && nums[left] < 0) prefixSum -= nums[left++];
                }
            } else {
                // flip
                if (right > 1 && nums[right - 2] > nums[right - 1]) q = right - 1;
                if (left < p && p < q) result = Math.max(result, prefixSum);
            }
        }
        return result;
    }
}
