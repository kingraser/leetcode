package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import static java.lang.Math.*;

/**
 * @author Wit
 */
public class MaximumPointsInsideTheSquare {
/*
You are given a 2D array points and a string s where, points[i] represents the coordinates of point i, and s[i] represents the tag of point i.
A valid square is a square centered at the origin (0, 0), has edges parallel to the axes, and does not contain two points with the same tag.
Return the maximum number of points contained in a valid square.
Note:
    A point is considered to be inside the square if it lies on or within the square's boundaries.
    The side length of the square can be zero.

Example 1:
Input: points = [[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]], s = "abdca"
Output: 2
Explanation:
The square of side length 4 covers two points points[0] and points[1].

Example 2:
Input: points = [[1,1],[-2,-2],[-2,2]], s = "abb"
Output: 1
Explanation:
The square of side length 2 covers one point, which is points[0].

Example 3:
Input: points = [[1,1],[-1,-1],[2,-2]], s = "ccd"
Output: 0
Explanation:
It's impossible to make any valid squares centered at the origin such that it covers only one point among points[0] and points[1].

Constraints:
    1 <= s.length, points.length <= 10^5
    points[i].length == 2
    -10^9 <= points[i][0], points[i][1] <= 10^9
    s.length == points.length
    points consists of distinct coordinates.
    s consists only of lowercase English letters.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, TestUtil.getArray("[[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]]"), "abdca"},
				{1, TestUtil.getArray("[[1,1],[-2,-2],[-2,2]]"), "abb"},
				{0, TestUtil.getArray("[[1,1],[-1,-1],[2,-2]]"), "ccd"},
		});
	}

	public int maxPointsInsideSquare(int[][] points, String s) {
		int result = 0, minOfSecondMin = Integer.MAX_VALUE, minDistance[] = new int[128];
		for (int i = 'a'; i <= 'z'; ) minDistance[i++] = Integer.MAX_VALUE;
		for (int i = 0, distance, tag; i < points.length; minOfSecondMin = min(minOfSecondMin, distance))
			if (minDistance[tag = s.charAt(i)] > (distance = getDistance(points[i++]))) {
				int temp = minDistance[tag];
				minDistance[tag] = distance;
				distance = temp;
			}
		for (int i = 'a'; i <= 'z'; ) if (minDistance[i++] < minOfSecondMin) result++;
		return result;
	}

	int getDistance(int[] point) {return max(abs(point[0]), abs(point[1]));}
}
