package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

public class RegionsCutBySlashes {
    /*
    An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '.
    These characters divide the square into contiguous regions.
    Given the grid 'grid' represented as a string array, return the number of regions.
    Note that backslash characters are escaped, so a '\' is represented as '\\'.

    Example 1:
    Input: grid = [" /","/ "]
    Output: 2

    Example 2:
    Input: grid = [" /","  "]
    Output: 1

    Example 3:
    Input: grid = ["/\\","\\/"]
    Output: 5
    Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.

    Constraints:
        n == grid.length == grid[i].length
        1 <= n <= 30
        grid[i][j] is either '/', '\', or ' '.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, new String[]{" /", "/ "}},
                {1, new String[]{" /", "  "}},
                {5, new String[]{"/\\", "\\/"}},
        });
    }

    public int regionsBySlashes(String[] grid) {
        int gridSize = grid.length;
        int totalTriangles = gridSize * gridSize * 4;
        int[] parentArray = new int[totalTriangles];
        Arrays.fill(parentArray, -1);

        // Initially, each small triangle is a separate region
        int regionCount = totalTriangles;

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                // Connect with the cell above
                if (row > 0) {
                    regionCount -=
                            unionTriangles(
                                    parentArray,
                                    getTriangleIndex(gridSize, row - 1, col, 2),
                                    getTriangleIndex(gridSize, row, col, 0)
                            );
                }
                // Connect with the cell to the left
                if (col > 0) {
                    regionCount -=
                            unionTriangles(
                                    parentArray,
                                    getTriangleIndex(gridSize, row, col - 1, 1),
                                    getTriangleIndex(gridSize, row, col, 3)
                            );
                }

                // If not '/', connect triangles 0-1 and 2-3
                if (grid[row].charAt(col) != '/') {
                    regionCount -=
                            unionTriangles(
                                    parentArray,
                                    getTriangleIndex(gridSize, row, col, 0),
                                    getTriangleIndex(gridSize, row, col, 1)
                            );
                    regionCount -=
                            unionTriangles(
                                    parentArray,
                                    getTriangleIndex(gridSize, row, col, 2),
                                    getTriangleIndex(gridSize, row, col, 3)
                            );
                }

                // If not '\', connect triangles 0-3 and 1-2
                if (grid[row].charAt(col) != '\\') {
                    regionCount -=
                            unionTriangles(
                                    parentArray,
                                    getTriangleIndex(gridSize, row, col, 0),
                                    getTriangleIndex(gridSize, row, col, 3)
                            );
                    regionCount -=
                            unionTriangles(
                                    parentArray,
                                    getTriangleIndex(gridSize, row, col, 2),
                                    getTriangleIndex(gridSize, row, col, 1)
                            );
                }
            }
        }
        return regionCount;
    }

    // Calculate the index of a triangle in the flattened array
    // Each cell is divided into 4 triangles, numbered 0 to 3 clockwise from the top
    private int getTriangleIndex(int gridSize, int row, int col, int triangleNum) {
        return ((gridSize * row + col) << 2) + triangleNum;
    }

    // Union two triangles and return 1 if they were not already connected, 0 otherwise
    private int unionTriangles(int[] parentArray, int x, int y) {
        int parentX = findParent(parentArray, x);
        int parentY = findParent(parentArray, y);

        if (parentX != parentY) {
            parentArray[parentX] = parentY;
            return 1; // Regions were merged, so count decreases by 1
        }

        return 0; // Regions were already connected
    }

    // Find the parent (root) of a set
    private int findParent(int[] parentArray, int n) {
        while (parentArray[n] != -1) n = parentArray[n];
        return n;
    }
}
