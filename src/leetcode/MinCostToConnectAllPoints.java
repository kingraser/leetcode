package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class MinCostToConnectAllPoints {
	/*
	You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
	The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
	Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

	Example 1:
	Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
	Output: 20
	Explanation:
	We can connect the points as shown above to get the minimum cost of 20.
	Notice that there is a unique path between every pair of points.

	Example 2:
	Input: points = [[3,12],[-2,5],[-4,1]]
	Output: 18

	Constraints:
	1 <= points.length <= 1000
	-10^6 <= xi, yi <= 10^6
	All pairs (xi, yi) are distinct.
	*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{20, TestUtil.getArray("[[0,0],[2,2],[3,10],[5,2],[7,0]]")},
				{18, TestUtil.getArray("[[3,12],[-2,5],[-4,1]]")},
		});
	}

	public int minCostConnectPoints(int[][] points) {
		int result = 0, pointCount = points.length, distances[] = new int[pointCount], visited[] = new int[pointCount];
		Arrays.fill(distances, 1, pointCount, Integer.MAX_VALUE);
		for (int counted = 0, current = 0, minEdge, next = -1, point; ++counted < pointCount; current = next, result += minEdge)
			for (point = 0, minEdge = Integer.MAX_VALUE, visited[current]++; point < pointCount; point++)
				if (visited[point] == 0 && (distances[point] = Math.min(getDistance(points[current], points[point]), distances[point])) < minEdge)
					minEdge = distances[next = point];
		return result;
	}

	int getDistance(int[] a, int[] b) {return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);}
}
