package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

public class FindTheLargestAlmostMissingInteger {
    /*
    You are given an integer array nums and an integer k.
    An integer x is almost missing from nums if x appears in exactly one sub-array of size k within nums.
    Return the largest almost missing integer from nums. If no such integer exists, return -1.
    A sub-array is a contiguous sequence of elements within an array.

    Example 1:
    Input: nums = [3,9,2,1,7], k = 3
    Output: 7
    Explanation:
        1 appears in 2 sub-arrays of size 3: [9, 2, 1] and [2, 1, 7].
        2 appears in 3 sub-arrays of size 3: [3, 9, 2], [9, 2, 1], [2, 1, 7].
        3 appears in 1 sub-array of size 3: [3, 9, 2].
        7 appears in 1 sub-array of size 3: [2, 1, 7].
        9 appears in 2 sub-arrays of size 3: [3, 9, 2], and [9, 2, 1].
    We return 7 since it is the largest integer that appears in exactly one sub-array of size k.

    Example 2:
    Input: nums = [3,9,7,2,1,7], k = 4
    Output: 3
    Explanation:
        1 appears in 2 sub-arrays of size 4: [9, 7, 2, 1], [7, 2, 1, 7].
        2 appears in 3 sub-arrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1], [7, 2, 1, 7].
        3 appears in 1 sub-array of size 4: [3, 9, 7, 2].
        7 appears in 3 sub-arrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1], [7, 2, 1, 7].
        9 appears in 2 sub-arrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1].
    We return 3 since it is the largest and only integer that appears in exactly one sub-array of size k.

    Example 3:
    Input: nums = [0,0], k = 1
    Output: -1
    Explanation:
    There is no integer that appears in only one sub-array of size 1.

    Constraints:
        1 <= nums.length <= 50
        0 <= nums[i] <= 50
        1 <= k <= nums.length
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {7, new int[]{3, 9, 2, 1, 7}, 3},
                {3, new int[]{3, 9, 7, 2, 1, 7}, 4},
                {-1, new int[]{0, 0}, 1},
                {0, new int[]{0, 0}, 2},
        });
    }

    public int largestInteger(int[] nums, int k) {
        if (k == nums.length) return Arrays.stream(nums).max().orElse(-1);
        int count[] = new int[51];
        for (int num : nums) count[num]++;
        if (k == 1) return Arrays.stream(nums).filter(num -> count[num] == 1).max().orElse(-1);
        return Math.max(getNum(count, nums[0]), getNum(count, nums[nums.length - 1]));
    }

    int getNum(int[] count, int num) {
        return count[num] == 1 ? num : -1;
    }
}
