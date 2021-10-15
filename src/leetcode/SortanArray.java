package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class SortanArray {
    /*
    Given an array of integers nums, sort the array in ascending order.

    Example 1:
    Input: nums = [5,2,3,1]
    Output: [1,2,3,5]

    Example 2:
    Input: nums = [5,1,1,2,0,0]
    Output: [0,0,1,1,2,5]

    Constraints:
    1 <= nums.length <= 5 * 10^4
    -5 * 10^4 <= nums[i] <= 5 * 10^4
    */
    @Test
    public void test() {
        TestUtil.testArrayEquals(new Object[][]{
                {new int[]{1, 2, 3, 5}, new int[]{5, 2, 3, 1}},
                {new int[]{0, 0, 1, 1, 2, 5}, new int[]{5, 1, 1, 2, 0, 0}}
        });
    }

    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }
}