package leetcode;

/**
 * @author Wit
 */
public class SpecialPositionsinaBinaryMatrix {
    /*
    Given a rows x cols matrix mat, where mat[i][j] is either 0 or 1, return the number of special positions in mat.
    A position (i,j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and columns are 0-indexed).

    Example 1:
    Input: mat = [[1,0,0],
                  [0,0,1],
                  [1,0,0]]
    Output: 1
    Explanation: (1,2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.

    Example 2:
    Input: mat = [[1,0,0],
                  [0,1,0],
                  [0,0,1]]
    Output: 3
    Explanation: (0,0), (1,1) and (2,2) are special positions.

    Example 3:
    Input: mat = [[0,0,0,1],
                  [1,0,0,0],
                  [0,1,1,0],
                  [0,0,0,0]]
    Output: 2

    Example 4:
    Input: mat = [[0,0,0,0,0],
                  [1,0,0,0,0],
                  [0,1,0,0,0],
                  [0,0,1,0,0],
                  [0,0,0,1,1]]
    Output: 3

    Constraints:
    rows == mat.length
    cols == mat[i].length
    1 <= rows, cols <= 100
    mat[i][j] is 0 or 1.
    */

    public int numSpecial(int[][] mat) {
        int result = 0, rows[] = new int[mat.length], cols[] = new int[mat[0].length];
        for (int row = 0; row < mat.length; row++)
            for (int col = 0; col < mat[0].length; col++)
                if (mat[row][col] == 1) {
                    rows[row]++;
                    cols[col]++;
                }
        for (int row = 0; row < mat.length; row++)
            for (int col = 0; col < mat[0].length; col++)
                if (mat[row][col] == 1 && rows[row] == 1 && cols[col] == 1) result++;
        return result;
    }
}
