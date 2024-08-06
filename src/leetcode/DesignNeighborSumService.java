package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class DesignNeighborSumService {
    /*
    You are given a n x n 2D array grid containing distinct elements in the range [0, n2 - 1].
    Implement the neighborSum class:
        neighborSum(int [][]grid) initializes the object.
        int adjacentSum(int value) returns the sum of elements which are adjacent neighbors of value, that is either to the top, left, right, or bottom of value in grid.
        int diagonalSum(int value) returns the sum of elements which are diagonal neighbors of value, that is either to the top-left, top-right, bottom-left, or bottom-right of value in grid.

    Example 1:
    Input:
    ["neighborSum", "adjacentSum", "adjacentSum", "diagonalSum", "diagonalSum"]
    [[[[0, 1, 2], [3, 4, 5], [6, 7, 8]]], [1], [4], [4], [8]]
    Output: [null, 6, 16, 16, 4]
    Explanation:
        The adjacent neighbors of 1 are 0, 2, and 4.
        The adjacent neighbors of 4 are 1, 3, 5, and 7.
        The diagonal neighbors of 4 are 0, 2, 6, and 8.
        The diagonal neighbor of 8 is 4.

    Example 2:
    Input:
    ["neighborSum", "adjacentSum", "diagonalSum"]
    [[[[1, 2, 0, 3], [4, 7, 15, 6], [8, 9, 10, 11], [12, 13, 14, 5]]], [15], [9]]
    Output: [null, 23, 45]
    Explanation:
        The adjacent neighbors of 15 are 0, 10, 7, and 6.
        The diagonal neighbors of 9 are 4, 12, 14, and 15.

    Constraints:
        3 <= n == grid.length == grid[0].length <= 10
        0 <= grid[i][j] <= n^2 - 1
        All grid[i][j] are distinct.
        value in adjacentSum and diagonalSum will be in the range [0, n^2 - 1].
        At most 2 * n^2 calls will be made to adjacentSum and diagonalSum.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new neighborSum(new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}}),
                new String[]{"adjacentSum", "adjacentSum", "diagonalSum", "diagonalSum"},
                new Object[]{6, 16, 16, 4},
                new Object[][]{{1}, {4}, {4}, {8}});
        TestUtil.testEquals(new neighborSum(new int[][]{{1, 2, 0, 3}, {4, 7, 15, 6}, {8, 9, 10, 11}, {12, 13, 14, 5}}),
                new String[]{"adjacentSum", "diagonalSum"},
                new Object[]{23, 45},
                new Object[][]{{15}, {9}});
    }

    public static class neighborSum {
        int n, last, flag = 1023, grid[][], map[];

        public neighborSum(int[][] grid) {
            map = new int[(n = (this.grid = grid).length) * n];
            for (int row = 0; row < n; row++) for (int col = 0; col < n; col++) map[grid[row][col]] = (row << 10) | col;
            last = n - 1;
        }

        public int adjacentSum(int value) {
            return adjacentSum1(map[value] >> 10, map[value] & flag);
        }

        public int diagonalSum(int value) {
            return diagonalSum1(map[value] >> 10, map[value] & flag);
        }

        private int adjacentSum1(int row, int col) {
            return (row > 0 ? grid[row - 1][col] : 0) + (col > 0 ? grid[row][col - 1] : 0) +
                    (row < last ? grid[row + 1][col] : 0) + (col < last ? grid[row][col + 1] : 0);
        }


        private int diagonalSum1(int row, int col) {
            int result = 0;
            if (row > 0) {
                if (col > 0) result += grid[row - 1][col - 1];
                if (col < last) result += grid[row - 1][col + 1];
            }
            if (row < last) {
                if (col > 0) result += grid[row + 1][col - 1];
                if (col < last) result += grid[row + 1][col + 1];
            }
            return result;
        }
    }
}
