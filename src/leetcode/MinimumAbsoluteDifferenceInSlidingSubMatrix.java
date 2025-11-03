package leetcode;

import java.util.Arrays;

import leetcode.util.TestUtil;
import org.junit.Test;

import static leetcode.util.TestUtil.getArray;

public class MinimumAbsoluteDifferenceInSlidingSubMatrix {
    /*
    You are given an m x n integer matrix grid and an integer k.
    For every contiguous k x k sub-matrix of grid, compute the minimum absolute difference between any two distinct values within that sub-matrix.
    Return a 2D array `ans` of size (m - k + 1) x (n - k + 1), where ans[i][j] is the minimum absolute difference in the sub-matrix whose top-left corner is (i, j) in grid.
    Note: If all elements in the sub-matrix have the same value, the answer will be 0.
    A sub-matrix (x1, y1, x2, y2) is a matrix that is formed by choosing all cells which matrix[x][y] where x1 <= x <= x2 and y1 <= y <= y2.
    
    Example 1:
    Input: grid = [[1,8],[3,-2]], k = 2
    Output: [[2]]
    Explanation:
        There is only one possible k x k sub-matrix: [[1, 8], [3, -2]].
        Distinct values in the sub-matrix are [1, 8, 3, -2].
        The minimum absolute difference in the sub-matrix is |1 - 3| = 2. Thus, the answer is [[2]].
    
    Example 2:
    Input: grid = [[3,-1]], k = 1
    Output: [[0,0]]
    Explanation:
        Both k x k sub-matrix has only one distinct element.
        Thus, the answer is [[0, 0]].
    
    Example 3:
    Input: grid = [[1,-2,3],[2,3,5]], k = 2
    Output: [[1,2]]
    Explanation:
        There are two possible k × k sub-matrix:
            Starting at (0, 0): [[1, -2], [2, 3]].
                Distinct values in the sub-matrix are [1, -2, 2, 3].
                The minimum absolute difference in the sub-matrix is |1 - 2| = 1.
            Starting at (0, 1): [[-2, 3], [3, 5]].
                Distinct values in the sub-matrix are [-2, 3, 5].
                The minimum absolute difference in the sub-matrix is |3 - 5| = 2.
        Thus, the answer is [[1, 2]].
    
    Constraints:
        1 <= m == grid.length <= 30
        1 <= n == grid[i].length <= 30
        -10^5 <= grid[i][j] <= 10^5
        1 <= k <= min(m, n)
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {getArray("[[2]]"), getArray("[[1,8],[3,-2]]"), 2},
                {getArray("[[0,0]]"), getArray("[[3,-1]]"), 1},
                {getArray("[[1,2]]"), getArray("[[1,-2,3],[2,3,5]]"), 2},
        });
    }


    public int[][] minAbsDiff(int[][] grid, int k) {
        int rowSize = grid.length - k + 1, colSize = grid[0].length - k + 1, ans[][] = new int[rowSize][colSize];
        for (int startRow = 0, temp[] = new int[k * k]; startRow < rowSize; startRow++)
            for (int startCol = 0; startCol < colSize; startCol++) {
                for (int row = startRow, index = 0; row < startRow + k; row++)
                    for (int col = startCol; col < startCol + k; col++)
                        temp[index++] = grid[row][col];
                Arrays.sort(temp);
                int minDiff = Integer.MAX_VALUE;
                for (int i = 1, diff; i < temp.length; i++)
                    if ((diff = temp[i] - temp[i - 1]) > 0 && diff < minDiff) minDiff = diff;
                ans[startRow][startCol] = minDiff == Integer.MAX_VALUE ? 0 : minDiff;
            }
        return ans;
    }
}
