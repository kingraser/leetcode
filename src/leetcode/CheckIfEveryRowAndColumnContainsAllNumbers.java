package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CheckIfEveryRowAndColumnContainsAllNumbers {
    /*
    An n x n matrix is valid if every row and every column contains all the integers from 1 to n (inclusive).
    Given an n x n integer matrix, return true if the matrix is valid. Otherwise, return false.

    Example 1:
    Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
    Output: true
    Explanation: In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
    Hence, we return true.

    Example 2:
    Input: matrix = [[1,1,1],[1,2,3],[1,2,3]]
    Output: false
    Explanation: In this case, n = 3, but the first row and the first column do not contain the numbers 2 or 3.
    Hence, we return false.

    Constraints:
    n == matrix.length == matrix[i].length
    1 <= n <= 100
    1 <= matrix[i][j] <= n
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, new int[][]{{1, 2, 3}, {3, 1, 2}, {2, 3, 1}}},
                {false, new int[][]{{1, 1, 1}, {1, 2, 3}, {1, 2, 3}}}
        });
    }

    public boolean checkValid(int[][] matrix) {
        int len = matrix.length, xor = 0;
        for (int i = 1; i <= len; ) xor ^= i++;
        for (int[] arr : matrix) {
            int x = xor;
            for (int n : arr) x ^= n;
            if (x != 0) return false;
        }
        for (int c = 0; c < len; c++) {
            int x = xor;
            for (int r = 0; r < len; ) x ^= matrix[r++][c];
            if (x != 0) return false;
        }
        return true;
    }
}
