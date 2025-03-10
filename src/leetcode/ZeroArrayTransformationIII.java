package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ZeroArrayTransformationIII {
    /*
    You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri].
    Each queries[i] represents the following action on nums:
        Decrement the value at each index in the range [li, ri] in nums by at most 1.
        The amount by which the value is decremented can be chosen independently for each index.
    A Zero Array is an array with all its elements equal to 0.
    Return the maximum number of elements that can be removed from queries, such that nums can still be converted to a zero array using the remaining queries. If it is not possible to convert nums to a zero array, return -1.

    Example 1:
    Input: nums = [2,0,2], queries = [[0,2],[0,2],[1,1]]
    Output: 1
    Explanation:
    After removing queries[2], nums can still be converted to a zero array.
        Using queries[0], decrement nums[0] and nums[2] by 1 and nums[1] by 0.
        Using queries[1], decrement nums[0] and nums[2] by 1 and nums[1] by 0.

    Example 2:
    Input: nums = [1,1,1,1], queries = [[1,3],[0,2],[1,3],[1,2]]
    Output: 2
    Explanation:
    We can remove queries[2] and queries[3].

    Example 3:
    Input: nums = [1,2,3,4], queries = [[0,3]]
    Output: -1
    Explanation:
    nums cannot be converted to a zero array even after using all the queries.

    Constraints:
        1 <= nums.length <= 10^5
        0 <= nums[i] <= 10^5
        1 <= queries.length <= 10^5
        queries[i].length == 2
        0 <= li <= ri < nums.length
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, new int[]{2, 0, 2}, TestUtil.getArray("[[0,2],[0,2],[1,1]]")},
                {2, new int[]{1, 1, 1, 1}, TestUtil.getArray("[[1,3],[0,2],[1,3],[1,2]]")},
                {-1, new int[]{1, 2, 3, 4}, TestUtil.getArray("[[0,3]]")},
        });
    }

    /*
    Hint 1
    Sort the queries.
    Hint 2
    We need to greedily pick the queries with the furthest ending point first.
    */
    public int maxRemoval(int[] nums, int[][] queries) {
        Arrays.sort(queries, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> rightHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0, length = nums.length, sweepLine[] = new int[length + 1], queryIndex = 0, cur = 0; i < length; i++) {
            while (queryIndex < queries.length && queries[queryIndex][0] == i) rightHeap.add(queries[queryIndex++][1]);
            for (cur += sweepLine[i]; cur < nums[i] && !rightHeap.isEmpty() && rightHeap.peek() >= i; cur++)
                sweepLine[rightHeap.poll() + 1]--;
            if (cur < nums[i]) return -1;
        }
        return rightHeap.size();
    }
}
