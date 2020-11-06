package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class BinarySearch {
    /*
    Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search target in nums. If target exists, then return its index, otherwise return -1.

    Example 1:
    Input: nums = [-1,0,3,5,9,12], target = 9
    Output: 4
    Explanation: 9 exists in nums and its index is 4

    Example 2:
    Input: nums = [-1,0,3,5,9,12], target = 2
    Output: -1
    Explanation: 2 does not exist in nums so return -1

    Note:
    You may assume that all elements in nums are unique.
    n will be in the range [1, 10000].
    The value of each element in nums will be in the range [-9999, 9999].
    */

    public int search(int[] nums, int target) {
        return Integer.max(-1, Arrays.binarySearch(nums, target));
    }

    @Test
    public void test() {
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        Assert.assertEquals(-1, search(nums, 2));
        Assert.assertEquals(4, search(nums, 9));
    }
}
