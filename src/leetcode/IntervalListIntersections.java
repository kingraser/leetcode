package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class IntervalListIntersections {
    /*
    You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [start_i, end_i] and secondList[j] = [start_j, end_j]. Each list of intervals is pairwise disjoint and in sorted order.
    Return the intersection of these two interval lists.
    A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
    The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
    
    Example 1:
    Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
    Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
    
    Example 2:
    Input: firstList = [[1,3],[5,9]], secondList = []
    Output: []
    
    Example 3:
    Input: firstList = [], secondList = [[4,8],[10,12]]
    Output: []
    
    Example 4:
    Input: firstList = [[1,7]], secondList = [[3,10]]
    Output: [[3,7]]
    
    Constraints:
    0 <= firstList.length, secondList.length <= 1000
    firstList.length + secondList.length >= 1
    0 <= start_i < end_i <= 10^9
    end_i < start_i+1
    0 <= start_j < end_j <= 10^9
    end_j < start_j+1
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[][]{{1, 2}, {5, 5}, {8, 10}, {15, 23}, {24, 24}, {25, 25}}, new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}}, new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}}},
                {new int[][]{}, new int[][]{{1, 3}, {5, 9}}, new int[][]{}},
                {new int[][]{}, new int[][]{}, new int[][]{{4, 8}, {10, 12}}},
                {new int[][]{{3, 7}}, new int[][]{{1, 7}}, new int[][]{{3, 10}}}
        });
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0, j = 0, a[], b[], k; i < A.length && j < B.length; k = a[1] < b[1] ? ++i : a[1] > b[1] ? ++j : ++i - ++j)
            if ((a = A[i])[0] <= (b = B[j])[1] && b[0] <= a[1])
                res.add(new int[]{Math.max(a[0], b[0]), Math.min(a[1], b[1])});
        return res.toArray(int[][]::new);
    }
}
