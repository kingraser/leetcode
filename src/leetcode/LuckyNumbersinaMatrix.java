package leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wit
 */
public class LuckyNumbersinaMatrix {
    /*
    Given a m * n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
    A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.

    Example 1:
    Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
    Output: [15]
    Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column

    Example 2:
    Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
    Output: [12]
    Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.

    Example 3:
    Input: matrix = [[7,8],[1,2]]
    Output: [7]

    Constraints:
    m == mat.length
    n == mat[i].length
    1 <= n, m <= 50
    1 <= matrix[i][j] <= 10^5.
    All elements in the matrix are distinct.
    */

    public List<Integer> luckyNumbers(int[][] matrix) {
        Set<Integer> minSet = Arrays.stream(matrix).map(Arrays::stream).map(stream -> stream.min().getAsInt()).collect(Collectors.toSet()), maxSet = new HashSet<>();
        for (int row, col = 0, mx; col < matrix[0].length; col++) {
            for (row = 0, mx = matrix[0][col]; row < matrix.length; row++) mx = Math.max(matrix[row][col], mx);
            if (minSet.contains(mx)) maxSet.add(mx);
        }
        return new ArrayList<>(maxSet);
    }
}
