package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountNodesWithTheHighestScore {
	/*
	There is a binary tree rooted at 0 consisting of n nodes. The nodes are labeled from 0 to n - 1. You are given a 0-indexed integer array parents representing the tree, where parents[i] is the parent of node i. Since node 0 is the root, parents[0] == -1.
	Each node has a score. To find the score of a node, consider if the node and the edges connected to it were removed. The tree would become one or more non-empty subtrees. The size of a subtree is the number of the nodes in it. The score of the node is the product of the sizes of all those subtrees.
	Return the number of nodes that have the highest score.

	Example 1:
	Input: parents = [-1,2,0,2,0]
	Output: 3
	Explanation:
	- The score of node 0 is: 3 * 1 = 3
	- The score of node 1 is: 4 = 4
	- The score of node 2 is: 1 * 1 * 2 = 2
	- The score of node 3 is: 4 = 4
	- The score of node 4 is: 4 = 4
	The highest score is 4, and three nodes (node 1, node 3, and node 4) have the highest score.
	
	Example 2:
	Input: parents = [-1,2,0]
	Output: 2
	Explanation:
	- The score of node 0 is: 2 = 2
	- The score of node 1 is: 2 = 2
	- The score of node 2 is: 1 * 1 = 1
	The highest score is 2, and two nodes (node 0 and node 1) have the highest score.
	
	Constraints:
	n == parents.length
	2 <= n <= 10^5
	parents[0] == -1
	0 <= parents[i] <= n - 1 for i != 0
	parents represents a valid binary tree.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{-1, 2, 0, 2, 0}},
				{2, new int[]{-1, 2, 0}},
		});
	}

	long max;
	int total, maxCount;

	public int countHighestScoreNodes(int[] parents) {
		max = (total = parents.length) - 1;
		maxCount = 0;
		int[][] tree = new int[parents.length][2];
		for (int i = 1; i < parents.length; i++) tree[parents[i]][tree[parents[i]][0] == 0 ? 0 : 1] = i;
		dfs(tree, 0);
		return maxCount;
	}


	/**
	 * dfs get children count
	 *
	 * @param tree the tree
	 * @param node current node index
	 * @return children count
	 */
	int dfs(int[][] tree, int node) {
		int leftChildrenCount = tree[node][0] == 0 ? 0 : dfs(tree, tree[node][0]);
		int rightChildrenCount = tree[node][1] == 0 ? 0 : dfs(tree, tree[node][1]);
		long current = (long) Math.max(1, leftChildrenCount) * Math.max(1, rightChildrenCount) * Math.max(1, total - 1 - leftChildrenCount - rightChildrenCount);
		if (current == max) maxCount++;
		else if (current > max) {
			max = current;
			maxCount = 1;
		}
		return 1 + leftChildrenCount + rightChildrenCount;
	}
}
