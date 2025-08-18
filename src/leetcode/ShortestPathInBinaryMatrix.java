package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    /*
    Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
    A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
        All the visited cells of the path are 0.
        All the adjacent cells of the path are 8-directionally connected (i.e., they are different, and they share an edge or a corner).
    The length of a clear path is the number of visited cells of this path.

    Example 1:
    Input: grid = [[0,1],[1,0]]
    Output: 2

    Example 2:
    Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
    Output: 4

    Example 3:
    Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
    Output: -1

    Constraints:
        n == grid.length
        n == grid[i].length
        1 <= n <= 100
        grid[i][j] is 0 or 1
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, TestUtil.getArray("[[0,1],[1,0]]")},
                {4, TestUtil.getArray("[[0,0,0],[1,1,0],[1,1,0]]")},
                {-1, TestUtil.getArray("[[1,0,0],[1,1,0],[1,1,0]]")},
                });
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0) return -1;
        if (grid.length == 1) return 1;
        Queue<Integer> queue = new ArrayDeque<>();
        log(grid, 0, 0, queue);
        int[][] directions = new int[][]{{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        for (int length = grid.length, last = length - 1, result = 1; !queue.isEmpty(); result++)
            for (int size = queue.size(), mask = 127; size-- > 0; ) {
                for (int i = 0, position = queue.remove(), col = position & mask, row = (position >> 7) & mask, newRow, newCol; i < directions.length; i++) {
                    if ((newRow = row + (directions[i])[0]) < 0 || newRow >= length || (newCol = col + directions[i][1]) < 0 || newCol >= length || grid[newRow][newCol] != 0)
                        continue;
                    if (newRow == last && newCol == last) return result + 1;
                    log(grid, newRow, newCol, queue);
                }
            }
        return -1;
    }

    void log(int[][] grid, int row, int col, Queue<Integer> queue) {
        queue.add((row << 7) | col);
        grid[row][col] = -1;
    }
}
