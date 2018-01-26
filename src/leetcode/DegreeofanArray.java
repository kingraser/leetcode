package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DegreeofanArray {

    /*
    Given a non-empty array of non-negative integers nums,
    the degree of this array is defined as the maximum frequency of any one of its elements.

    Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

    Example 1:
    Input: [1, 2, 2, 3, 1]
    Output: 2
    Explanation:
    The input array has a degree of 2 because both elements 1 and 2 appear twice.
    Of the subarrays that have the same degree:
    [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
    The shortest length is 2. So return 2.

    Example 2:
    Input: [1,2,2,3,1,4,2]
    Output: 6

    Note:
    nums.length will be between 1 and 50,000.
    nums[i] will be an integer between 0 and 49,999.
    */

    @Test
    public void test() {
        assertEquals(2, findShortestSubArray(new int[] {1, 2, 2, 3, 1}));
        assertEquals(6, findShortestSubArray(new int[] {1, 2, 2, 3, 1, 4, 2}));
    }

    public int findShortestSubArray(int[] nums) {
        int result = 1;
        /* first for length; second for start idx */
        Map<Integer, int[]> map = new HashMap<>(80_000);
        for (int idx = 0, maxDegree = 1; idx < nums.length; idx++) {
            if (!map.containsKey(nums[idx])) {map.put(nums[idx], new int[] {0, idx - 1});}
            int array[] = map.get(nums[idx]), degree = ++array[0], length = idx - array[1];
            if (degree > maxDegree || (degree == maxDegree && length < result)) {
                maxDegree = degree;
                result = length;
            }
        }
        return result;
    }
}