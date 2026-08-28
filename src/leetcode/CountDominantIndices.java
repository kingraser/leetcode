package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class CountDominantIndices {
    /*
    You are given an integer array nums of length n.
    An element at index i is called dominant if: nums[i] > average(nums[i + 1], nums[i + 2], ..., nums[n - 1])
    Your task is to count the number of indices i that are dominant.
    The average of a set of numbers is the value obtained by adding all the numbers together and dividing the sum by the total number of numbers.
    Note: The rightmost element of any array is not dominant.

    Example 1:
    Input: nums = [5,4,3]
    Output: 2
    Explanation:
    At index i = 0, the value 5 is dominant as 5 > average(4, 3) = 3.5.
    At index i = 1, the value 4 is dominant over the subarray [3].
    Index i = 2 is not dominant as there are no elements to its right. Thus, the answer is 2.

    Example 2:
    Input: nums = [4,1,2]
    Output: 1
    Explanation:
    At index i = 0, the value 4 is dominant over the subarray [1, 2].
    At index i = 1, the value 1 is not dominant.
    Index i = 2 is not dominant as there are no elements to its right. Thus, the answer is 1.

    Constraints:
    1 <= nums.length <= 100
    1 <= nums[i] <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(
                new Object[]{2, 1},
                new Object[][]{
                        {new int[]{5, 4, 3}},
                        {new int[]{4, 1, 2}}
                }
        );
    }

    public int dominantIndices(int[] nums) {
        int result = 0;
        for (int i = nums.length - 2, average = nums[i + 1], count = 1; i >= 0; average += nums[i--]) {
            if (nums[i] * count++ > average) result++;
        }
        return result;
    }
}
