package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MatrixBlockSum {
    /*
    Given a m x n matrix mat and an integer k, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for:
    i - k <= r <= i + k,
    j - k <= c <= j + k, and
    (r, c) is a valid position in the matrix.

    Example 1:
    Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
    Output: [[12,21,16],[27,45,33],[24,39,28]]

    Example 2:
    Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
    Output: [[45,45,45],[45,45,45],[45,45,45]]

    Constraints:
    m == mat.length
    n == mat[i].length
    1 <= m, n, k <= 100
    1 <= mat[i][j] <= 100
    */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int rowCount = mat.length, colCount = mat[0].length, ans[][] = new int[rowCount][colCount], rangeSum[][] = new int[rowCount + 1][colCount + 1];
        for (int i = 0; i < rowCount; i++)
            for (int j = 0; j < colCount; j++)
                rangeSum[i + 1][j + 1] = rangeSum[i + 1][j] + rangeSum[i][j + 1] - rangeSum[i][j] + mat[i][j];
        for (int i = 0; i < rowCount; i++)
            for (int j = 0, r1 = Math.max(0, i - k), r2 = Math.min(rowCount, i + k + 1), c1, c2; j < colCount; j++)
                ans[i][j] = rangeSum[r2][c2 = Math.min(colCount, j + k + 1)] - rangeSum[r2][c1 = Math.max(0, j - k)] - rangeSum[r1][c2] + rangeSum[r1][c1];
        return ans;
    }

    @Test
    public void test() {
        TestUtil.testArrayEquals(new Object[][]{
                {new int[][]{{12, 21, 16}, {27, 45, 33}, {24, 39, 28}}, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 1},
                {new int[][]{{45, 45, 45}, {45, 45, 45}, {45, 45, 45}}, new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 2}
        });
    }
}
