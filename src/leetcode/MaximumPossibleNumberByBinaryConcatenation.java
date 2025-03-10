package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class MaximumPossibleNumberByBinaryConcatenation {
    /*
    You are given an array of integers nums of size 3.
    Return the maximum possible number whose binary representation can be formed by concatenating the binary representation of all elements in nums in some order.
    Note that the binary representation of any number does not contain leading zeros.

    Example 1:
    Input: nums = [1,2,3]
    Output: 30
    Explanation:
    Concatenate the numbers in the order [3, 1, 2] to get the result "11110", which is the binary representation of 30.

    Example 2:
    Input: nums = [2,8,16]
    Output: 1296
    Explanation:
    Concatenate the numbers in the order [2, 8, 16] to get the result "10100010000", which is the binary representation of 1296.

    Constraints:
        nums.length == 3
        1 <= nums[i] <= 127
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {61294, new int[]{2, 91, 119}},
                {221, new int[]{1, 11, 5}},
                {30, new int[]{1, 2, 3}},
                {1296, new int[]{2, 8, 16}},
        });
    }

    public int maxGoodNumber(int[] nums) {
        int l1 = getLength(nums[0]), l2 = getLength(nums[1]), l3 = getLength(nums[2]), result = (nums[0] << (l2 + l3)) | Math.max((nums[1] << l3) | nums[2], (nums[2] << l2) | nums[1]);
        result = Math.max(result, (nums[1] << (l1 + l3)) | Math.max((nums[0] << l3) | nums[2], (nums[2] << l1) | nums[0]));
        result = Math.max(result, (nums[2] << (l1 + l2)) | Math.max((nums[0] << l2) | nums[1], (nums[1] << l1) | nums[0]));
        return result;
    }

    int getLength(int n) {
        int result = 1;
        for (; n > 1; result++) n >>= 1;
        return result;
    }
}
