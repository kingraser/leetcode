package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Wit
 */
public class FindSubsequenceOfLengthKWithTheLargestSum {

    /*
    You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.
    Return any such subsequence as an integer array of length k.
    A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

    Example 1:
    Input: nums = [2,1,3,3], k = 2
    Output: [3,3]
    Explanation:
    The subsequence has the largest sum of 3 + 3 = 6.

    Example 2:
    Input: nums = [-1,-2,3,4], k = 3
    Output: [-1,3,4]
    Explanation:
    The subsequence has the largest sum of -1 + 3 + 4 = 6.

    Example 3:
    Input: nums = [3,4,3,3], k = 2
    Output: [3,4]
    Explanation:
    The subsequence has the largest sum of 3 + 4 = 7.
    Another possible subsequence is [4, 3].

    Constraints:
    1 <= nums.length <= 1000
    -10^5 <= nums[i] <= 10^5
    1 <= k <= nums.length
    */

    @Test
    public void test() {
        TestUtil.testArrayEquals(new Object[][]{
                {new int[]{3, 3}, new int[]{2, 1, 3, 3,}, 2},
                {new int[]{-1, 3, 4}, new int[]{-1, -2, 3, 4}, 3}
        });
    }

    public int[] maxSubsequence(int[] nums, int k) {
        int result[] = new int[k], map[][] = new int[nums.length][2];
        for (int i = 0; i < nums.length; ) {map[i] = new int[]{i, nums[i++]};}
        Arrays.sort(map, Comparator.comparingInt(a -> a[1]));
        Arrays.sort(map, map.length - k, map.length, Comparator.comparingInt(a -> a[0]));
        for (int i = map.length - k, j = 0; i < map.length; ) {result[j++] = map[i++][1];}
        return result;
    }
}
