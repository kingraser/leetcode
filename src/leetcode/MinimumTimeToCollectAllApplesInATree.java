package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Wit
 */
public class MinimumTimeToCollectAllApplesInATree {
	/*
	Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
	The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.

	Example 1:
	Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
	Output: 8
	Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.

	Example 2:
	Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
	Output: 6
	Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.

	Example 3:
	Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
	Output: 0

	Constraints:
	1 <= n <= 10^5
	edges.length == n - 1
	edges[i].length == 2
	0 <= ai < bi <= n - 1
	from_i < to_i
	hasApple.length == n
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{8, 7, TestUtil.getArray("[[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]"), List.of(false, false, true, false, true, true, false)},
				{6, 7, TestUtil.getArray("[[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]"), List.of(false, false, true, false, false, true, false)},
				{0, 7, TestUtil.getArray("[[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]"), List.of(false, false, false, false, false, false, false)}
		});
	}

	int result = 0;

	public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
		result = 0;
		HashMap<Integer, List<Integer>> tree = new HashMap<>(n);
		for (int[] edge : edges) {
			tree.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
			tree.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
		}
		return dfs(tree, hasApple, 0, -1) ? result - 2 : result;
	}

	boolean dfs(HashMap<Integer, List<Integer>> tree, List<Boolean> hasApple, int node, int parent) {
		boolean childrenHaveApple = false;
		List<Integer> children = tree.get(node);
		if (children != null)
			for (Integer child : children) if (child != parent) childrenHaveApple |= dfs(tree, hasApple, child, node);
		if (childrenHaveApple || (childrenHaveApple |= hasApple.get(node))) result += 2;
		return childrenHaveApple;
	}
}
