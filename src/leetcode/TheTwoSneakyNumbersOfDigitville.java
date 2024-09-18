package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class TheTwoSneakyNumbersOfDigitville {
    /*
    In the town of Digitville, there was a list of numbers called nums containing integers from 0 to n - 1. Each number was supposed to appear exactly once in the list, however, two mischievous numbers sneaked in an additional time, making the list longer than usual.
    As the town detective, your task is to find these two sneaky numbers. Return an array of size two containing the two numbers (in any order), so peace can return to Digitville.

    Example 1:
    Input: nums = [0,1,1,0]
    Output: [0,1]
    Explanation:
    The numbers 0 and 1 each appear twice in the array.

    Example 2:
    Input: nums = [0,3,2,1,3,2]
    Output: [2,3]
    Explanation:
    The numbers 2 and 3 each appear twice in the array.

    Example 3:
    Input: nums = [7,1,5,4,3,4,6,0,9,5,8,2]
    Output: [4,5]
    Explanation:
    The numbers 4 and 5 each appear twice in the array.

    Constraints:
        2 <= n <= 100
        nums.length == n + 2
        0 <= nums[i] < n
        The input is generated such that nums contains exactly two repeated elements.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{0, 1}, new int[]{0, 1, 1, 0}},
                {new int[]{2, 3}, new int[]{0, 3, 2, 1, 3, 2}},
                {new int[]{4, 5}, new int[]{7, 1, 5, 4, 3, 4, 6, 0, 9, 5, 8, 2}},
        });
    }

    public int[] getSneakyNumbers(int[] nums) {
        int xorNums = 0, originalLength = nums.length - 2, xorOriginal = 0;
        for (int num : nums) xorNums ^= num;
        for (int i = 1; i < originalLength; ) xorOriginal ^= i++;
        int xorAB = xorNums ^ xorOriginal;
        int firstDifferentBit = xorAB & -xorAB;
        int xorNumsOne = 0, xorNumsZero = 0, xorOriginalOne = 0, xorOriginalZero = 0;
        for (int num : nums)
            if ((num & firstDifferentBit) == 0) xorNumsZero ^= num;
            else xorNumsOne ^= num;
        for (int i = 1; i < originalLength; i++)
            if ((i & firstDifferentBit) == 0) xorOriginalZero ^= i;
            else xorOriginalOne ^= i;
        return new int[]{xorNumsZero ^ xorOriginalZero, xorNumsOne ^ xorOriginalOne};
    }
}
