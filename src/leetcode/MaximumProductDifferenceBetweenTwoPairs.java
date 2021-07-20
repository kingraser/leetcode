package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumProductDifferenceBetweenTwoPairs {
    /*
    The product difference between two pairs (a, leetcode.b) and (c, d) is defined as (a * leetcode.b) - (c * d).
    For example, the product difference between (5, 6) and (2, 7) is (5 * 6) - (2 * 7) = 16.
    Given an integer array nums, choose four distinct indices w, x, y, and z such that the product difference between pairs (nums[w], nums[x]) and (nums[y], nums[z]) is maximized.
    Return the maximum such product difference.

    Example 1:
    Input: nums = [5,6,2,7,4]
    Output: 34
    Explanation: We can choose indices 1 and 3 for the first pair (6, 7) and indices 2 and 4 for the second pair (2, 4).
    The product difference is (6 * 7) - (2 * 4) = 34.

    Example 2:
    Input: nums = [4,2,5,9,7,4,8]
    Output: 64
    Explanation: We can choose indices 3 and 6 for the first pair (9, 8) and indices 1 and 5 for the second pair (2, 4).
    The product difference is (9 * 8) - (2 * 4) = 64.

    Constraints:
    4 <= nums.length <= 10^4
    1 <= nums[i] <= 10^4
    */
    public int maxProductDifference(int[] nums) {
        int max = Integer.MIN_VALUE, secMax = Integer.MIN_VALUE, min = Integer.MAX_VALUE, secMin = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num >= max) {
                secMax = max;
                max = num;
            } else if (num > secMax) secMax = num;
            if (num <= min) {
                secMin = min;
                min = num;
            } else if (num < secMin) secMin = num;
        }
        return max * secMax - min * secMin;
    }

    @Test
    public void test() {
        Assert.assertEquals(64, maxProductDifference(new int[]{4, 2, 5, 9, 7, 4, 8}));
    }
}
