package leetcode;

/**
 * @author Wit
 */
public class DetermineWhetherMatrixCanBeObtainedByRotation {
    /*
    Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.

    Example 1:
    Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
    Output: true
    Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.

    Example 2:
    Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
    Output: false
    Explanation: It is impossible to make mat equal to target by rotating mat.

    Example 3:
    Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
    Output: true
    Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.

    Constraints:
    n == mat.length == target.length
    n == mat[i].length == target[i].length
    1 <= n <= 10
    mat[i][j] and target[i][j] are either 0 or 1.
    */
    public boolean findRotation(int[][] mat, int[][] target) {
        boolean[] result = new boolean[]{true, true, true, true};
        for (int i = 0, size = mat.length; i < size; i++)
            for (int j = 0; j < size; j++) {
                if (result[0] && mat[i][j] != target[i][j]) result[0] = false;
                if (result[1] && mat[i][j] != target[size - j - 1][i]) result[1] = false;
                if (result[2] && mat[i][j] != target[size - i - 1][size - j - 1]) result[2] = false;
                if (result[3] && mat[i][j] != target[j][size - i - 1]) result[3] = false;
            }
        return result[0] || result[1] || result[2] || result[3];
    }
}
