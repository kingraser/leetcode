package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ZigzagGridTraversalWithSkip {
    /*
    You are given an m x n 2D array grid of positive integers.
    Your task is to traverse grid in a zigzag pattern while skipping every alternate cell.
    Zigzag pattern traversal is defined as following the below actions:
        Start at the top-left cell (0, 0).
        Move right within a row until the end of the row is reached.
        Drop down to the next row, then traverse left until the beginning of the row is reached.
        Continue alternating between right and left traversal until every row has been traversed.
    Note that you must skip every alternate cell during the traversal.
    Return an array of integers result containing, in order, the value of the cells visited during the zigzag traversal with skips.

    Example 1:
    Input: grid = [[1,2],[3,4]]
    Output: [1,4]

    Example 2:
    Input: grid = [[2,1],[2,1],[2,1]]
    Output: [2,1,2]


    Example 3:
    Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [1,3,5,7,9]

    Constraints:
        2 <= n == grid.length <= 50
        2 <= m == grid[i].length <= 50
        1 <= grid[i][j] <= 2500
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(1, 4), TestUtil.getArray("[[1,2],[3,4]]")},
                {List.of(2, 1, 2), TestUtil.getArray("[[2,1],[2,1],[2,1]]")},
                {List.of(1, 3, 5, 7, 9), TestUtil.getArray("[[1,2,3],[4,5,6],[7,8,9]]")},
        });
    }

    public List<Integer> zigzagTraversal(int[][] grid) {
        int rowSize = grid.length, colSize = grid[0].length;
        List<Integer> result = new ArrayList<>((rowSize * colSize + 1) >> 1);
        for (int row = 0, col = -1, left = 0, choose = 0; row < rowSize; row++)
            if ((left ^= 1) == 1) while (++col < colSize) {
                if ((choose ^= 1) == 1) result.add(grid[row][col]);
            }
            else while (--col >= 0) if ((choose ^= 1) == 1) result.add(grid[row][col]);
        return result;
    }
}
