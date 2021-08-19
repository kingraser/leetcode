package leetcode;

import leetcode.util.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author Wit
 */
public class TheKWeakestRowsinaMatrix {
    /*
    You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians). The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
    A row i is weaker than a row j if one of the following is true:
    The number of soldiers in row i is less than the number of soldiers in row j.
    Both rows have the same number of soldiers and i < j.
    Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.

    Example 1:
    Input: mat =
    [[1,1,0,0,0],
     [1,1,1,1,0],
     [1,0,0,0,0],
     [1,1,0,0,0],
     [1,1,1,1,1]],
    k = 3
    Output: [2,0,3]
    Explanation:
    The number of soldiers in each row is:
    - Row 0: 2
    - Row 1: 4
    - Row 2: 1
    - Row 3: 2
    - Row 4: 5
    The rows ordered from weakest to strongest are [2,0,3,1,4].

    Example 2:
    Input: mat =
    [[1,0,0,0],
     [1,1,1,1],
     [1,0,0,0],
     [1,0,0,0]],
    k = 2
    Output: [0,2]
    Explanation:
    The number of soldiers in each row is:
    - Row 0: 1
    - Row 1: 4
    - Row 2: 1
    - Row 3: 1
    The rows ordered from weakest to strongest are [0,2,3,1].

    Constraints:
    m == mat.length
    n == mat[i].length
    2 <= n, m <= 100
    1 <= k <= m
    matrix[i][j] is either 0 or 1.
    */

    public int[] kWeakestRows(int[][] mat, int k) {
        int[] ans = new int[k];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
        for (int i = 0; i < mat.length; i++) {
            pq.offer(new int[]{countOnes(mat[i]), i});
            if (pq.size() > k) pq.poll();
        }
        while (k-- > 0) ans[k] = pq.poll()[1];
        return ans;
    }

    private int countOnes(int[] row) {
        int low = 0, high = row.length;
        for (int mid; low < high; )
            if (row[mid = ((low + high) >> 1)] == 1) low = mid + 1;
            else high = mid;
        return low;
    }

    @Test
    public void test() throws NoSuchMethodException {
        TestUtil.testArrayEquals(
                new Object[][]{
                        {
                                new int[]{2, 0, 3},
                                new int[][]{{1, 1, 0, 0, 0},
                                        {1, 1, 1, 1, 0},
                                        {1, 0, 0, 0, 0},
                                        {1, 1, 0, 0, 0},
                                        {1, 1, 1, 1, 1}},
                                3
                        },
                        {
                                new int[]{0, 2},
                                new int[][]{{1, 0, 0, 0},
                                        {1, 1, 1, 1},
                                        {1, 0, 0, 0},
                                        {1, 0, 0, 0}},
                                2
                        }
                });
    }
}
