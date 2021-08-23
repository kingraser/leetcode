package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Wit
 */
public class WidestVerticalAreaBetweenTwoPointsContainingNoPoints {
    /*
    Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area between two points such that no points are inside the area.
    A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height). The widest vertical area is the one with the maximum width.
    Note that points on the edge of a vertical area are not considered included in the area.
    
    Example 1:
    Input: points = [[8,7],[9,9],[7,4],[9,7]]
    Output: 1
    Explanation: Both the red and the blue area are optimal.
    
    Example 2:
    Input: points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
    Output: 3
    
    Constraints:
    n == points.length
    2 <= n <= 10^5
    points[i].length == 2
    0 <= xi, yi <= 10^9
    */
    public int maxWidthOfVerticalArea(int[][] points) {
        int result = Integer.MIN_VALUE;
        Arrays.sort(points, Comparator.comparingInt(point -> point[0]));
        for (int i = 1; i < points.length; i++)
            result = Integer.max(result, points[i][0] - points[i - 1][0]);
        return result;
    }

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, new int[][]{{8, 7}, {9, 9}, {7, 4}, {9, 7}}},
                {3, new int[][]{{3, 1}, {9, 0}, {1, 0}, {1, 4}, {5, 3}, {8, 8}}}
        });
    }

}
