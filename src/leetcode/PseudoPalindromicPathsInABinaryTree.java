package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class PseudoPalindromicPathsInABinaryTree {
	/*
	Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
	Return the number of pseudo-palindromic paths going from the root node to leaf nodes.

	Example 1:
	Input: root = [2,3,1,3,1,null,1]
	Output: 2
	Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).

	Example 2:
	Input: root = [2,1,1,1,3,null,null,null,null,null,1]
	Output: 1
	Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).

	Example 3:
	Input: root = [9]
	Output: 1

	Constraints:
	The number of nodes in the tree is in the range [1, 10^5].
	1 <= Node.val <= 9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, TreeNode.treeLevel("2,3,1,3,1,null,1")},
				{1, TreeNode.treeLevel("2,1,1,1,3,null,null,null,null,null,1")},
				{1, TreeNode.treeLevel("9")}
		});
	}

	public int pseudoPalindromicPaths(TreeNode root) {
		List list = new List();
		dfs(root, list);
		return list.pathCount;
	}

	private void dfs(TreeNode node, List list) {
		if (node == null) return;
		list.push(node.val);
		if (!node.isLeaf()) {
			dfs(node.left, list);
			dfs(node.right, list);
		} else list.checkLeaf();
		list.pop();
	}


	public static class List {
		int pathCount = 0, counts[] = new int[10], path[] = new int[1_00_000], size = 0;

		void push(int n) {counts[path[size++] = n]++;}

		void pop() {counts[path[--size]]--;}

		void checkLeaf() {
			int oddCount = 0;
			for (int i = 1; i < 10 && oddCount < 2; ) if ((counts[i++] & 1) == 1) oddCount++;
			if (oddCount < 2) pathCount++;
		}
	}
}
