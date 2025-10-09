package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class SmallestAbsentPositiveGreaterThanAverage {
    /*
    You are given an integer array nums.
    Return the smallest absent positive integer in nums such that it is strictly greater than the average of all elements in nums.
    The average of an array is defined as the sum of all its elements divided by the number of elements.

    Example 1:
    Input: nums = [3,5]
    Output: 6
    Explanation:
        The average of nums is (3 + 5) / 2 = 8 / 2 = 4.
        The smallest absent positive integer greater than 4 is 6.

    Example 2:
    Input: nums = [-1,1,2]
    Output: 3
    Explanation:
        The average of nums is (-1 + 1 + 2) / 3 = 2 / 3 = 0.667.
        The smallest absent positive integer greater than 0.667 is 3.

    Example 3:
    Input: nums = [4,-1]
    Output: 2
    Explanation:
        The average of nums is (4 + (-1)) / 2 = 3 / 2 = 1.50.
        The smallest absent positive integer greater than 1.50 is 2.

    Constraints:
        1 <= nums.length <= 100
        -100 <= nums[i] <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {101, new int[]{98, 100}},
                {1, new int[]{-34}},
                {6, new int[]{3, 5}},
                {3, new int[]{-1, 1, 2}},
                {2, new int[]{4, -1}},
        });
    }

    public int smallestAbsent(int[] nums) {
        int sum = 0, map[] = new int[101];
        for (int num : nums) {
            sum += num;
            if (num > 0) map[num] = 1;
        }
        int average = Math.max(1, (sum / nums.length) + 1);
        while (average <= map.length && map[average] > 0) average++;
        return average;
    }
}
