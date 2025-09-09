package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class MinimumOperationsToEqualizeArray {
    /*
    You are given an integer array `nums` of length n.
    In one operation, choose any sub-array nums[l...r] (0 <= l <= r < n) and replace each element in that sub-array with the bitwise AND of all elements.
    Return the minimum number of operations required to make all elements of nums equal.
    A sub-array is a contiguous non-empty sequence of elements within an array.

    Example 1:
    Input: nums = [1,2]
    Output: 1
    Explanation:
    Choose nums[0...1]: (1 AND 2) = 0, so the array becomes [0, 0] and all elements are equal in 1 operation.

    Example 2:
    Input: nums = [5,5,5]
    Output: 0
    Explanation:
    nums is [5, 5, 5] which already has all elements equal, so 0 operations are required.

    Constraints:
        1 <= n == nums.length <= 100
        1 <= nums[i] <= 10^5
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, new int[]{1, 2}},
                {0, new int[]{5, 5, 5}},
        });
    }

    public int minOperations(int[] nums) {
        for (int first = nums[0], i = 1, length = nums.length; i < length; ) if (nums[i++] != first) return 1;
        return 0;
    }
}
