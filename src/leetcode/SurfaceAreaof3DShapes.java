package leetcode;

/**
 * @author Wit
 */
public class SurfaceAreaof3DShapes {
    /*
    On a N * N grid, we place some 1 * 1 * 1 cubes.
    Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
    Return the total surface area of the resulting shapes.

    Example 1:
    Input: [[2]]
    Output: 10

    Example 2:
    Input: [[1,2],[3,4]]
    Output: 34

    Example 3:
    Input: [[1,0],[0,2]]
    Output: 16

    Example 4:
    Input: [[1,1,1],[1,0,1],[1,1,1]]
    Output: 32

    Example 5:
    Input: [[2,2,2],[2,1,2],[2,2,2]]
    Output: 46

    Note:
    1 <= N <= 50
    0 <= grid[i][j] <= 50
    */

    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int surfaceArea(int[][] grid) {
        int result = 0;
        for (int row = 0, length = grid.length; row < length; row++)
            for (int column = 0, newRow, newColumn, newValue; column < length; column++)
                if (grid[row][column] > 0) {
                    result += 2;
                    for (int[] direction : DIRECTIONS)
                        result += Math.max(grid[row][column] - (0 <= (newRow = row + direction[0]) && newRow < length && 0 <= (newColumn = column + direction[1]) && newColumn < length ? grid[newRow][newColumn] : 0), 0);
                }
        return result;
    }
}
