package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {
	/*
	There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
	Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
	This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
	Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
	It's guaranteed that each city can reach city 0 after reorder.

	Example 1:
	Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
	Output: 3
	Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

	Example 2:
	Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
	Output: 2
	Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

	Example 3:
	Input: n = 3, connections = [[1,0],[2,0]]
	Output: 0

	Constraints:
	2 <= n <= 5 * 10^4
	connections.length == n - 1
	connections[i].length == 2
	0 <= ai, bi <= n - 1
	ai != bi
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, 6, TestUtil.getArray("[[0,1],[1,3],[2,3],[4,0],[4,5]]")},
				{2, 5, TestUtil.getArray("[[1,0],[1,2],[3,2],[3,4]]")},
				{0, 3, TestUtil.getArray("[[1,0],[2,0]]")},
		});
	}

	int result;

	public int minReorder(int n, int[][] connections) {
		result = 0;
		List<Integer>[] graph = new List[n];
		for (int i = 0; i < n; ) graph[i++] = new ArrayList<>();
		for (int[] connection : connections) {
			graph[connection[0]].add(connection[1]);
			graph[connection[1]].add(-connection[0]);
		}
		dfs(graph, 0, n);
		return result;
	}

	void dfs(List<Integer>[] graph, int node, int from) {
		for (int next : graph[node]) {
			if (Math.abs(next) == from) continue;
			if (next > 0) result++;
			dfs(graph, Math.abs(next), node);
		}
	}
}
