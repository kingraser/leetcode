package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class CountSubMatricesWithAllOnes {
    /*
    Given an m x n binary matrix mat, return the number of subMatrices that have all ones.

    Example 1:
    Input: mat = [[1,0,1],[1,1,0],[1,1,0]]
    Output: 13
    Explanation:
    There are 6 rectangles of side 1x1.
    There are 2 rectangles of side 1x2.
    There are 3 rectangles of side 2x1.
    There is 1 rectangle of side 2x2.
    There is 1 rectangle of side 3x1.
    Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.

    Example 2:
    Input: mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
    Output: 24
    Explanation:
    There are 8 rectangles of side 1x1.
    There are 5 rectangles of side 1x2.
    There are 2 rectangles of side 1x3.
    There are 4 rectangles of side 2x1.
    There are 2 rectangles of side 2x2.
    There are 2 rectangles of side 3x1.
    There is 1 rectangle of side 3x2.
    Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.

    Constraints:
        1 <= m, n <= 150
        mat[i][j] is either 0 or 1.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {5, TestUtil.getArray("[[1,0,1],[0,1,0],[1,0,1]]")},
                {13, TestUtil.getArray("[[1,0,1],[1,1,0],[1,1,0]]")},
                {24, TestUtil.getArray("[[0,1,1,0],[0,1,1,1],[1,1,1,0]]")},
        });
    }

    public int numSubmat(int[][] mat) {
        int colSize = mat[0].length, height[] = new int[colSize], result = 0;
        for (int[] row : mat) {
            for (int col = 0; col < colSize; col++) height[col] = (row[col] == 0) ? 0 : height[col] + 1;
            result += countInRow(height);
        }
        return result;
    }

    private int countInRow(int[] heights) {
        int stack[] = new int[heights.length], top = -1, result = 0, sum[] = new int[heights.length];
        for (int col = 0; col < heights.length; result += sum[stack[++top] = col++]) {
            while (top >= 0 && heights[stack[top]] >= heights[col]) top--;
            if (top >= 0) sum[col] = sum[stack[top]] + (col - stack[top]) * heights[col];
            else sum[col] = (col + 1) * heights[col];
        }
        return result;
    }
}
