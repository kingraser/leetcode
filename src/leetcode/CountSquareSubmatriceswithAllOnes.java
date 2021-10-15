package leetcode;

import leetcode.util.MathUtil;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountSquareSubmatriceswithAllOnes {
    /*
    Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
    
    Example 1:
    Input: matrix =
    [
      [0,1,1,1],
      [1,1,1,1],
      [0,1,1,1]
    ]
    Output: 15
    Explanation: 
    There are 10 squares of side 1.
    There are 4 squares of side 2.
    There is  1 square of side 3.
    Total number of squares = 10 + 4 + 1 = 15.
    
    Example 2:
    Input: matrix = 
    [
      [1,0,1],
      [1,1,0],
      [1,1,0]
    ]
    Output: 7
    Explanation: 
    There are 6 squares of side 1.  
    There is 1 square of side 2. 
    Total number of squares = 6 + 1 = 7.
    
    Constraints:
    1 <= arr.length <= 300
    1 <= arr[0].length <= 300
    0 <= arr[i][j] <= 1
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {15, new int[][]{
                        {0, 1, 1, 1},
                        {1, 1, 1, 1},
                        {0, 1, 1, 1}}},
                {7, new int[][]{
                        {1, 0, 1},
                        {1, 1, 0},
                        {1, 1, 0}}}
        });
    }

    public int countSquares(int[][] matrix) {
        int res = 0;
        for (int r = 0, rowSize = matrix.length, colSize = matrix[0].length; r < rowSize; r++)
            for (int c = 0; c < colSize; c++)
                if (matrix[r][c] == 0) continue;
                else if (r == 0 || c == 0) res++;
                else res += matrix[r][c] += MathUtil.min(matrix[r - 1][c], matrix[r][c - 1], matrix[r - 1][c - 1]);
        return res;
    }
}
