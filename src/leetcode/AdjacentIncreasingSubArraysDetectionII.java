package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.List;

public class AdjacentIncreasingSubArraysDetectionII {
    /*
    Given an array nums of n integers,
    your task is to find the maximum value of k for which there exist two adjacent sub-arrays of length k each,
    such that both sub-arrays are strictly increasing.
    Specifically, check if there are two sub-arrays of length k starting at indices a and b (a < b), where:
        Both sub-arrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
        The sub-arrays must be adjacent, meaning b = a + k.
    Return the maximum possible value of k.
    A sub-array is a contiguous non-empty sequence of elements within an array.

    Example 1:
    Input: nums = [2,5,7,8,9,2,3,4,3,1]
    Output: 3
    Explanation:
        The sub-array starting at index 2 is [7, 8, 9], which is strictly increasing.
        The sub-array starting at index 5 is [2, 3, 4], which is also strictly increasing.
        These two sub-arrays are adjacent, and 3 is the maximum possible value of k for which two such adjacent strictly increasing sub-arrays exist.

    Example 2:
    Input: nums = [1,2,3,4,4,4,4,5,6,7]
    Output: 2
    Explanation:
        The sub-array starting at index 0 is [1, 2], which is strictly increasing.
        The sub-array starting at index 2 is [3, 4], which is also strictly increasing.
        These two sub-arrays are adjacent, and 2 is the maximum possible value of k for which two such adjacent strictly increasing sub-arrays exist.

    Constraints:
        2 <= nums.length <= 2 * 10^5
        -10^9 <= nums[i] <= 10^9
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {3, List.of(2, 5, 7, 8, 9, 2, 3, 4, 3, 1)},
                {2, List.of(1, 2, 3, 4, 4, 4, 4, 5, 6, 7)},
        });
    }

    public int maxIncreasingSubarrays(List<Integer> nums) {
        int result = 0;
        for (int i = 1, length = nums.size(), currentLength = 1, prevLength = 0, prev = nums.get(0); i < length; result = Math.max(result, Math.max(currentLength >> 1, Math.min(prevLength, currentLength))))
            if (prev >= (prev = nums.get(i++))) {
                prevLength = currentLength;
                currentLength = 1;
            } else currentLength++;
        return result;
    }
}
