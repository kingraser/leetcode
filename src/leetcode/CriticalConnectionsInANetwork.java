package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wit
 */
public class CriticalConnectionsInANetwork {
	/*
	There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.
	A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
	Return all critical connections in the network in any order.

	Example 1:
	Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
	Output: [[1,3]]
	Explanation: [[3,1]] is also accepted.

	Example 2:
	Input: n = 2, connections = [[0,1]]
	Output: [[0,1]]

	Constraints:
	2 <= n <= 10^5
	n - 1 <= connections.length <= 10^5
	0 <= ai, bi <= n - 1
	ai != bi
	There are no repeated connections.
	*/
	/*
	trajan
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.transferToInteger("[[1,3]]"), 4, TestUtil.transferToInteger("[[0,1],[1,2],[2,0],[1,3]]")},
				{TestUtil.transferToInteger("[[0,1]]"), 2, TestUtil.transferToInteger("[[0,1]]")}
		});
	}

	int time; // time when discover each vertex

	@SuppressWarnings("unchecked")
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		List<List<Integer>> res = new ArrayList<>();
		int[] discover = new int[n], low = new int[n];
		Arrays.fill(discover, -1); // use discover to track if visited (disc[i] == -1)
		// use adjacency list instead of matrix will save some memory, adj-matrix will cause MLE
		List<Integer>[] graph = new List[n];
		for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
		// build graph
		for (List<Integer> connection : connections) {
			graph[connection.get(0)].add(connection.get(1));
			graph[connection.get(1)].add(connection.get(0));
		}
		time = 0;
		for (int nodeIdx = 0; nodeIdx < n; nodeIdx++)
			if (discover[nodeIdx] == -1) dfs(nodeIdx, low, discover, graph, res, nodeIdx);
		return res;
	}


	private void dfs(int currentNode, int[] low, int[] discover, List<Integer>[] graph, List<List<Integer>> res, int parent) {
		discover[currentNode] = low[currentNode] = ++time; // discover u
		for (int child : graph[currentNode]) {
			// if parent vertex, ignore
			if (child == parent) continue;
			// if v discovered and is not parent of u, update low[u], cannot use low[v] because u is not subtree of v
			else if (discover[child] != -1) low[currentNode] = Math.min(low[currentNode], discover[child]);
			// if not discovered
			else {
				dfs(child, low, discover, graph, res, currentNode);
				low[currentNode] = Math.min(low[currentNode], low[child]);
				// u - v is critical, there is no path for v to reach back to u or previous vertices of u
				if (low[child] > discover[currentNode]) res.add(Arrays.asList(currentNode, child));
			}
		}
	}
}
