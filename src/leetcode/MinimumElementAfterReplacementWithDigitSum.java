package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class MinimumElementAfterReplacementWithDigitSum {
    /*
    You are given an integer array nums.
    You replace each element in nums with the sum of its digits.
    Return the minimum element in nums after all replacements.

    Example 1:
    Input: nums = [10,12,13,14]
    Output: 1
    Explanation:
    nums becomes [1, 3, 4, 5] after all replacements, with minimum element 1.

    Example 2:
    Input: nums = [1,2,3,4]
    Output: 1
    Explanation:
    nums becomes [1, 2, 3, 4] after all replacements, with minimum element 1.

    Example 3:
    Input: nums = [999,19,199]
    Output: 10
    Explanation:
    nums becomes [27, 10, 19] after all replacements, with minimum element 10.

    Constraints:
        1 <= nums.length <= 100
        1 <= nums[i] <= 10^4
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, new int[]{10, 12, 13, 14}},
                {1, new int[]{1, 2, 3, 4}},
                {10, new int[]{999, 19, 199}},
        });
    }

    public int minElement(int[] nums) {
        int result = Integer.MAX_VALUE;
        for (int num : nums) if ((result = Integer.min(result, getDigitSum(num))) == 1) break;
        return result;
    }

    private int getDigitSum(int num) {
        int result = 0;
        for (; num != 0; num /= 10) result += num % 10;
        return result;
    }
}
