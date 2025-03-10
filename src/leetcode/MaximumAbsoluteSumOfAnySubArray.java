package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;


public class MaximumAbsoluteSumOfAnySubArray {
    /*
    You are given an integer array nums.
    The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is abs(numsl + numsl+1 + ... + numsr-1 + numsr).
    Return the maximum absolute sum of any (possibly empty) subarray of nums.
    Note that abs(x) is defined as follows:
        If x is a negative integer, then abs(x) = -x.
        If x is a non-negative integer, then abs(x) = x.

    Example 1:
    Input: nums = [1,-3,2,3,-4]
    Output: 5
    Explanation: The subarray [2,3] has absolute sum = abs(2+3) = abs(5) = 5.

    Example 2:
    Input: nums = [2,-5,1,-4,3,-2]
    Output: 8
    Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8) = 8.

    Constraints:
        1 <= nums.length <= 10^5
        -10^4 <= nums[i] <= 10^4
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {5, new int[]{1, -3, 2, 3, -4}},
                {8, new int[]{2, -5, 1, -4, 3, -2}},
        });
    }

    /*
    What if we asked for maximum sum, not absolute sum?
    It's a standard problem that can be solved by Kadane's algorithm.
    The key idea is the max absolute sum will be either the max sum or the min sum.
    So just run kadane twice, once calculating the max sum and once calculating the min sum.
    */
    public int maxAbsoluteSum(int[] nums) {
        int maxSoFar = MIN_VALUE, minSoFar = Integer.MAX_VALUE, maxEndingHere = 0, minEndingHere = 0;
        for (int num : nums) {
            maxSoFar = max(maxSoFar, maxEndingHere = max(num, maxEndingHere + num));
            minSoFar = min(minSoFar, minEndingHere = min(num, minEndingHere + num));
        }
        return max(abs(maxSoFar), abs(minSoFar));
    }
}
