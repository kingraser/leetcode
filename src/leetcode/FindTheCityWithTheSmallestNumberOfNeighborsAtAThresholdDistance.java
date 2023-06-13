package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {
	/*
	There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [from_i, toi, weight_i] represents a bidirectional and weighted edge between cities from_i and toi, and given the integer distanceThreshold.
	Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
	Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.
	
	Example 1:
	Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
	Output: 3
	Explanation: The figure above describes the graph. 
	The neighboring cities at a distanceThreshold = 4 for each city are:
	City 0 -> [City 1, City 2] 
	City 1 -> [City 0, City 2, City 3] 
	City 2 -> [City 0, City 1, City 3] 
	City 3 -> [City 1, City 2] 
	Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
	
	Example 2:
	Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
	Output: 0
	Explanation: The figure above describes the graph. 
	The neighboring cities at a distanceThreshold = 2 for each city are:
	City 0 -> [City 1] 
	City 1 -> [City 0, City 4] 
	City 2 -> [City 3, City 4] 
	City 3 -> [City 2, City 4]
	City 4 -> [City 1, City 2, City 3] 
	The city 0 has 1 neighboring city at a distanceThreshold = 2.
	
	Constraints:
	2 <= n <= 100
	1 <= edges.length <= n * (n - 1) / 2
	edges[i].length == 3
	0 <= from_i < toi < n
	1 <= weight_i, distanceThreshold <= 10^4
	All pairs (from_i, toi) are distinct.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, 4, TestUtil.getArray("[[0,1,3],[1,2,1],[1,3,4],[2,3,1]]"), 4},
				{0, 5, TestUtil.getArray("[[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]]"), 2},
		});
	}

	public int findTheCity(int n, int[][] edges, int distanceThreshold) {
		int[][] distance = new int[n][n];
		int res = 0, smallest = n, maxThreshold = 10001;
		for (int[] row : distance) Arrays.fill(row, maxThreshold);
		for (int[] edge : edges) distance[edge[0]][edge[1]] = distance[edge[1]][edge[0]] = edge[2];
		for (int i = 0; i < n; ++i) distance[i][i] = 0;
		for (int k = 0; k < n; ++k)
			for (int i = 0; i < n; ++i)
				for (int j = 0; j < n; ++j)
					distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
		for (int i = 0; i < n; i++) {
			int count = 0;
			for (int j = 0; j < n; ++j) if (distance[i][j] <= distanceThreshold) ++count;
			if (count <= smallest) {
				res = i;
				smallest = count;
			}
		}
		return res;
	}
}
