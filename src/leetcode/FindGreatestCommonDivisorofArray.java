package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindGreatestCommonDivisorofArray {
    /*
    Given an integer array nums, return the greatest common divisor of the smallest number and largest number in nums.
    The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.

    Example 1:
    Input: nums = [2,5,6,9,10]
    Output: 2
    Explanation:
    The smallest number in nums is 2.
    The largest number in nums is 10.
    The greatest common divisor of 2 and 10 is 2.

    Example 2:
    Input: nums = [7,5,6,8,3]
    Output: 1
    Explanation:
    The smallest number in nums is 3.
    The largest number in nums is 8.
    The greatest common divisor of 3 and 8 is 1.

    Example 3:
    Input: nums = [3,3]
    Output: 3
    Explanation:
    The smallest number in nums is 3.
    The largest number in nums is 3.
    The greatest common divisor of 3 and 3 is 3.

    Constraints:
    2 <= nums.length <= 1000
    1 <= nums[i] <= 1000
    */
    public int findGCD(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        return getGcd(min, max);
    }

    int getGcd(int a, int b) {
        return (b = b % a) == 0 ? a : getGcd(b, a);
    }

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, new int[]{2, 5, 6, 9, 10}},
                {1, new int[]{7, 5, 6, 8, 3}},
                {3, new int[]{3, 3}}
        });
    }
}
