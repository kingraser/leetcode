package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class CountUnreachablePairsOfNodesInAnUndirectedGraph {
	/*
	You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
	Return the number of pairs of different nodes that are unreachable from each other.

	Example 1:
	Input: n = 3, edges = [[0,1],[0,2],[1,2]]
	Output: 0
	Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.

	Example 2:
	Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
	Output: 14
	Explanation: There are 14 pairs of nodes that are unreachable from each other:
	[[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
	Therefore, we return 14.

	Constraints:
	1 <= n <= 10^5
	0 <= edges.length <= 2 * 10^5
	edges[i].length == 2
	0 <= ai, bi < n
	ai != bi
	There are no repeated edges.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{66L, 12, new int[0][]},
				{0L, 3, TestUtil.getArray("[[0,1],[0,2],[1,2]]")},
				{14L, 7, TestUtil.getArray("[[0,2],[0,5],[2,4],[1,6],[5,4]]")}
		});
	}

	public long countPairs(int n, int[][] edges) {
		int[] graph = new int[n];
		for (int i = 1; i < graph.length; ) graph[i] = i++;
		for (int[] edge : edges) union(edge[0], edge[1], graph);
		Map<Integer, Integer> map = new HashMap<>(n);
		for (int i = 0; i < graph.length; i++) map.merge(getRoot(i, graph), 1, Integer::sum);
		return getResult(map.values(), n);
	}

	void union(int a, int b, int[] graph) {
		int rootA = getRoot(a, graph), rootB = getRoot(b, graph);
		if (rootA == rootB) return;
		if (rootA > rootB) {
			int temp = rootA;
			rootA = rootB;
			rootB = temp;
		}
		graph[b] = graph[rootB] = rootA;
	}

	int getRoot(int num, int[] graph) {
		int result = num;
		while (graph[result] != result) result = graph[result];
		return graph[num] = result;
	}

	long getResult(Collection<Integer> values, int sum) {
		if (values.size() < 2) return 0;
		long result = 0;
		for (Integer value : values) result += ((long) value) * (sum -= value);
		return result;
	}
}
