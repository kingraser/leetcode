package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CyclicallyRotatingAGrid {
    /*
    You are given an m x n integer matrix grid, where m and n are both even integers, and an integer k.
    The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:
    
    A cyclic rotation of the matrix is done by cyclically rotating each layer in the matrix. To cyclically rotate a layer once, each element in the layer will take the place of the adjacent element in the counter-clockwise direction. An example rotation is shown below:
    
    Return the matrix after applying k cyclic rotations to it.
    
    Example 1:
    Input: grid = [[40,10],[30,20]], k = 1
    Output: [[10,20],[40,30]]
    Explanation: The figures above represent the grid at every state.
    
    Example 2:
    Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], k = 2
    Output: [[3,4,8,12],[2,11,10,16],[1,7,6,15],[5,9,13,14]]
    Explanation: The figures above represent the grid at every state.
    
    Constraints:
    m == grid.length
    n == grid[i].length
    2 <= m, n <= 50
    Both m and n are even integers.
    1 <= grid[i][j] <= 5000
    1 <= k <= 10^9
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[][]{{10, 20}, {40, 30}}, new int[][]{{40, 10}, {30, 20}}, 1},
                {new int[][]{{3, 4, 8, 12}, {2, 11, 10, 16}, {1, 7, 6, 15}, {5, 9, 13, 14}}, new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}, 2}
        });
    }

    static final int ILLEGAL_FLAG = -1;

    public int[][] rotateGrid(int[][] grid, int k) {
        for (int rowTop = 0, rowBottom = grid.length - 1, colLeft = 0, colRight = grid[0].length - 1; rowTop < rowBottom && colLeft < colRight; rowTop++, rowBottom--, colLeft++, colRight--)
            for (int circleLength = (rowBottom - rowTop + colRight - colLeft) << 1, step = k % circleLength, actions = step == 0 ? circleLength : 0, nextVal = ILLEGAL_FLAG, position[] = new int[]{rowTop, colLeft}; actions < circleLength; rotate(position, 1, rowTop, rowBottom, colLeft, colRight), nextVal = ILLEGAL_FLAG)
                for (int startRow, startCol, val = grid[startRow = position[0]][startCol = position[1]]; position[0] != startRow || position[1] != startCol || nextVal == ILLEGAL_FLAG; val = nextVal, actions++) {
                    rotate(position, step, rowTop, rowBottom, colLeft, colRight);
                    nextVal = grid[position[0]][position[1]];
                    grid[position[0]][position[1]] = val;
                }
        return grid;
    }

    /**
     * get the position after rotation with step times
     *
     * @param position  the start position
     *                  0 for row
     *                  1 for col
     *                  will change to the target position
     * @param step      the count of rotations
     * @param rowTop    the top limit of row
     * @param rowBottom the bottom limit of row
     * @param colLeft   the left limit of column
     * @param colRight  the right limit of column
     */
    void rotate(int[] position, int step, int rowTop, int rowBottom, int colLeft, int colRight) {
        // direction: 0 for down, 1 for right, 2 for up, 3 for left
        for (int direction = 0, move = 0; (step -= move) > 0; direction = ++direction & 3)
            if (direction == 0) {
                if (position[1] != colLeft) continue;
                position[0] += move = Integer.min(step, rowBottom - position[0]);
            } else if (direction == 1) {
                if (position[0] != rowBottom) continue;
                position[1] += move = Integer.min(step, colRight - position[1]);
            } else if (direction == 2) {
                if (position[1] != colRight) continue;
                position[0] -= move = Integer.min(step, position[0] - rowTop);
            } else if (direction == 3) {
                if (position[0] != rowTop) continue;
                position[1] -= move = Integer.min(step, position[1] - colLeft);
            }
    }

}
