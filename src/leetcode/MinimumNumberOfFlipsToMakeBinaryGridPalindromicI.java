package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class MinimumNumberOfFlipsToMakeBinaryGridPalindromicI {
    /*
    You are given an m x n binary matrix grid.
    A row or column is considered palindromic if its values read the same forward and backward.
    You can flip any number of cells in grid from 0 to 1, or from 1 to 0.
    Return the minimum number of cells that need to be flipped to make either all rows palindromic or all columns palindromic.

    Example 1:
    Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
    Output: 2
    Explanation:
    Flipping the highlighted cells makes all the rows palindromic.

    Example 2:
    Input: grid = [[0,1],[0,1],[0,0]]
    Output: 1
    Explanation:
    Flipping the highlighted cell makes all the columns palindromic.

    Example 3:
    Input: grid = [[1],[0]]
    Output: 0
    Explanation:
    All rows are already palindromic.

    Constraints:
        m == grid.length
        n == grid[i].length
        1 <= m * n <= 2 * 10^5
        0 <= grid[i][j] <= 1
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, TestUtil.getArray("[[1,0,0],[0,0,0],[0,0,1]]")},
                {1, TestUtil.getArray("[[0,1],[0,1],[0,0]]")},
                {0, TestUtil.getArray("[[1],[0]]")},
        });
    }

    public int minFlips(int[][] grid) {
        int rowResult = 0, colResult = 0, rowSize = grid.length, colSize = grid[0].length;
        for (int[] row : grid)
            for (int left = 0, right = colSize - 1; left < right; ) rowResult += row[left++] ^ row[right--];
        for (int col = 0; col < colSize; col++)
            for (int up = 0, down = rowSize - 1; up < down; )
                colResult += grid[up++][col] ^ grid[down--][col];
        return Math.min(rowResult, colResult);
    }
}
