package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class MaximizeExpressionOfThreeElements {
    /*
    You are given an integer array nums.
    Choose three elements a, b, and c from nums at distinct indices such that the value of the expression a + b - c is maximized.
    Return an integer denoting the maximum possible value of this expression.

    Example 1:
    Input: nums = [1,4,2,5]
    Output: 8
    Explanation:
    We can choose a = 4, b = 5, and c = 1. The expression value is 4 + 5 - 1 = 8, which is the maximum possible.

    Example 2:
    Input: nums = [-2,0,5,-2,4]
    Output: 11
    Explanation:
    We can choose a = 5, b = 4, and c = -2. The expression value is 5 + 4 - (-2) = 11, which is the maximum possible.

    Constraints:
        3 <= nums.length <= 100
        -100 <= nums[i] <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {8, new int[]{1, 4, 2, 5}},
                {11, new int[]{-2, 0, 5, -2, 4}},
        });
    }

    public int maximizeExpressionOfThree(int[] nums) {
        int max, secondMax, min;
        if (nums[0] <= nums[1]) {
            if (nums[1] <= nums[2]) {
                min = nums[0];
                secondMax = nums[1];
                max = nums[2];
            } else if (nums[0] <= nums[2]) {
                min = nums[0];
                secondMax = nums[2];
                max = nums[1];
            } else {
                min = nums[2];
                secondMax = nums[0];
                max = nums[1];
            }
        } else if (nums[1] >= nums[2]) {
            max = nums[0];
            secondMax = nums[1];
            min = nums[2];
        } else if (nums[2] >= nums[0]) {
            max = nums[2];
            secondMax = nums[0];
            min = nums[1];
        } else {
            max = nums[0];
            secondMax = nums[2];
            min = nums[1];
        }
        for (int i = 3, length = nums.length; i < length; i++)
            if (nums[i] < min) min = nums[i];
            else if (nums[i] >= max) {
                secondMax = max;
                max = nums[i];
            } else if (nums[i] > secondMax) secondMax = nums[i];
        return max + secondMax - min;
    }
}
