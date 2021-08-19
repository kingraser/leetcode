package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountNegativeNumbersinaSortedMatrix {
    /*
    Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.

    Example 1:
    Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
    Output: 8
    Explanation: There are 8 negatives number in the matrix.

    Example 2:
    Input: grid = [[3,2],[1,0]]
    Output: 0

    Example 3:
    Input: grid = [[1,-1],[-1,-1]]
    Output: 3

    Example 4:
    Input: grid = [[-1]]
    Output: 1

    Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 100
    -100 <= grid[i][j] <= 100

    Follow up: Could you find an O(n + m) solution?
    */
    public int countNegatives(int[][] grid) {
        int rowSize = grid.length, colSize = grid[0].length, row = rowSize - 1, col = 0, result = 0;
        while (row >= 0 && col < colSize)
            if (grid[row][col] < 0) result += (colSize - col) * (row - (row = getNewRowIdx(grid, row - 1, col)));
            else col = getNewColIdx(grid[row], ++col);
        return result;
    }

    int getNewRowIdx(int[][] grid, int row, int col) {
        for (int middle, low = 0; low < row; )
            if (grid[middle = (low + row) >> 1][col] > -1) low = middle + 1;
            else row = middle;
        return row;
    }

    int getNewColIdx(int[] array, int left) {
        for (int middle, right = array.length; left < right; )
            if (array[middle = (left + right) >> 1] > -1) left = middle + 1;
            else right = middle;
        return left;
    }

    @Test
    public void test() throws NoSuchMethodException {
        TestUtil.testEquals(
                new Object[][]{
                        {8, new int[][]{{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}}},
                        {0, new int[][]{{3, 2}, {1, 0}}},
                        {3, new int[][]{{1, -1}, {-1, -1}}},
                        {1, new int[][]{{-1}}}
                });
    }
}
