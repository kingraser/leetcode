package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Wit
 */
public class MaximumStarSumOfAGraph {
	/*
	There is an undirected graph consisting of n nodes numbered from 0 to n - 1. You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the ith node.
	You are also given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
	A star graph is a subgraph of the given graph having a center node containing 0 or more neighbors. In other words, it is a subset of edges of the given graph such that there exists a common node for all edges.
	The image below shows star graphs with 3 and 4 neighbors respectively, centered at the blue node.
	The star sum is the sum of the values of all the nodes present in the star graph.
	Given an integer k, return the maximum star sum of a star graph containing at most k edges.

	Example 1:
	Input: vals = [1,2,3,4,10,-10,-20], edges = [[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]], k = 2
	Output: 16
	Explanation: The above diagram represents the input graph.
	The star graph with the maximum star sum is denoted by blue. It is centered at 3 and includes its neighbors 1 and 4.
	It can be shown it is not possible to get a star graph with a sum greater than 16.

	Example 2:
	Input: vals = [-5], edges = [], k = 0
	Output: -5
	Explanation: There is only one possible star graph, which is node 0 itself.
	Hence, we return -5.

	Constraints:
	n == vals.length
	1 <= n <= 10^5
	-10^4 <= vals[i] <= 10^4
	0 <= edges.length <= min(n * (n - 1) / 2, 10^5)
	edges[i].length == 2
	0 <= ai, bi <= n - 1
	ai != bi
	0 <= k <= n - 1
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{16, new int[]{1, 2, 3, 4, 10, -10, -20}, TestUtil.getArray("[[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]]"), 2},
				{-5, new int[]{-5}, new int[0][], 0},
		});
	}

	public int maxStarSum(int[] vals, int[][] edges, int k) {
		int[] results = Arrays.copyOf(vals, vals.length);
		PriorityQueue<Integer>[] graph = new PriorityQueue[vals.length];
		for (int[] edge : edges) {
			add(edge[0], vals[edge[1]], k, graph, results);
			add(edge[1], vals[edge[0]], k, graph, results);
		}
		int result = results[0];
		for (int i = 0; i < results.length; ) result = Integer.max(result, results[i++]);
		return result;
	}

	private void add(int parent, int child, int maxSize, PriorityQueue<Integer>[] graph, int[] results) {
		if (child < 0 || maxSize == 0) return;
		if (graph[parent] == null) graph[parent] = new PriorityQueue<>();
		if (graph[parent].size() == maxSize) {
			if (graph[parent].peek() > child) return;
			results[parent] -= graph[parent].poll();
		}
		graph[parent].add(child);
		results[parent] += child;
	}

}
