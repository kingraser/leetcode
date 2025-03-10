package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class ZeroArrayTransformationII {
    /*
    You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri, vali].
    Each queries[i] represents the following action on nums:
        Decrement the value at each index in the range [li, ri] in nums by at most vali.
        The amount by which each value is decremented can be chosen independently for each index.
    A Zero Array is an array with all its elements equal to 0.
    Return the minimum possible non-negative value of k, such that after processing the first k queries in sequence, nums becomes a Zero Array. If no such k exists, return -1.

    Example 1:
    Input: nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]
    Output: 2
    Explanation:
        For i = 0 (l = 0, r = 2, val = 1):
            Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
            The array will become [1, 0, 1].
        For i = 1 (l = 0, r = 2, val = 1):
            Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
            The array will become [0, 0, 0], which is a Zero Array. Therefore, the minimum value of k is 2.

    Example 2:
    Input: nums = [4,3,2,1], queries = [[1,3,2],[0,2,1]]
    Output: -1
    Explanation:
        For i = 0 (l = 1, r = 3, val = 2):
            Decrement values at indices [1, 2, 3] by [2, 2, 1] respectively.
            The array will become [4, 1, 0, 0].
        For i = 1 (l = 0, r = 2, val = 1):
            Decrement values at indices [0, 1, 2] by [1, 1, 0] respectively.
            The array will become [3, 0, 0, 0], which is not a Zero Array.

    Constraints:
        1 <= nums.length <= 10^5
        0 <= nums[i] <= 5 * 10^5
        1 <= queries.length <= 10^5
        queries[i].length == 3
        0 <= li <= ri < nums.length
        1 <= vali <= 5
    */
/*
Hint 1
Can we apply binary search here?
Hint 2
Utilize a difference array to optimize the processing of queries.   
*/
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {0, new int[]{0}, TestUtil.getArray("[[0,0,2],[0,0,4],[0,0,4],[0,0,3],[0,0,5]]")},
                {2, new int[]{2, 0, 2}, TestUtil.getArray("[[0,2,1],[0,2,1],[1,1,3]]")},
                {-1, new int[]{4, 3, 2, 1}, TestUtil.getArray("[[1,3,2],[0,2,1]]")},
        });
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int result = -1, min = 0, max = queries.length, mid;
        while (min <= max)
            if (isZeroArray(nums, queries, mid = ((min + max) >> 1))) max = (result = mid) - 1;
            else min = mid + 1;
        return result;
    }

    boolean isZeroArray(int[] nums, int[][] queries, int k) {
        int length = nums.length, sweepLine[] = new int[length + 1];
        for (int i = 0, query[]; i < k; sweepLine[query[1] + 1] -= query[2])
            sweepLine[(query = queries[i++])[0]] += query[2];
        for (int i = 0, value = 0; i < length; ) if ((value += sweepLine[i]) < nums[i++]) return false;
        return true;
    }
}
