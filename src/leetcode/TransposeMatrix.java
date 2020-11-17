package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class TransposeMatrix {
    /*
    Given a matrix A, return the transpose of A.
    The transpose of a matrix is the matrix flipped over it's main diagonal, switching the row and column indices of the matrix.

    123    147
    456 -> 258
    789    369

    Example 1:
    Input: [[1,2,3],[4,5,6],[7,8,9]]
    Output: [[1,4,7],[2,5,8],[3,6,9]]

    Example 2:
    Input: [[1,2,3],[4,5,6]]
    Output: [[1,4],[2,5],[3,6]]

    Note:
    1 <= A.length <= 1000
    1 <= A[0].length <= 1000
    */

    public int[][] transpose(int[][] array) {
        int[][] result = new int[array[0].length][array.length];
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[0].length; j++)
                result[j][i] = array[i][j];
        return result;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}}, transpose(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        Assert.assertArrayEquals(new int[][]{{1, 4}, {2, 5}, {3, 6}}, transpose(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }
}
