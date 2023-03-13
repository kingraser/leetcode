package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Wit
 */
public class KthLargestSumInABinaryTree {
	/*
	You are given the root of a binary tree and a positive integer k.
	The level sum in the tree is the sum of the values of the nodes that are on the same level.
	Return the kth-largest level sum in the tree (not necessarily distinct). If there are fewer than k levels in the tree, return -1.
	Note that two nodes are on the same level if they have the same distance from the root.

	Example 1:
	Input: root = [5,8,9,2,1,3,7,4,6], k = 2
	Output: 13
	Explanation: The level sums are the following:
	- Level 1: 5.
	- Level 2: 8 + 9 = 17.
	- Level 3: 2 + 1 + 3 + 7 = 13.
	- Level 4: 4 + 6 = 10.
	The 2nd largest level sum is 13.

	Example 2:
	Input: root = [1,2,null,3], k = 1
	Output: 3
	Explanation: The largest level sum is 3.

	Constraints:
	The number of nodes in the tree is n.
	2 <= n <= 10^5
	1 <= Node.val <= 10^6
	1 <= k <= n
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{13L, TreeNode.treeLevel("5,8,9,2,1,3,7,4,6"), 2},
				{3L, TreeNode.treeLevel("1,2,null,3"), 1}
		});
	}

	public long kthLargestLevelSum(TreeNode root, int k) {
		List<Long> result = new ArrayList<>();
		dfs(root, result, 0);
		if (result.size() < k) return -1;
		Collections.sort(result);
		return result.get(result.size() - k);
	}

	private void dfs(TreeNode root, List<Long> result, int level) {
		if (root == null) return;
		while (level >= result.size()) result.add(0L);
		result.set(level, result.get(level) + root.val);
		dfs(root.left, result, level + 1);
		dfs(root.right, result, level + 1);
	}
}
