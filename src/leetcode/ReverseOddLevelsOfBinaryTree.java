package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;


/**
 * @author Wit
 */
public class ReverseOddLevelsOfBinaryTree {
	/*
	Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.
	For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
	Return the root of the reversed tree.
	A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.
	The level of a node is the number of edges along the path between it and the root node.

	Example 1:
	Input: root = [2,3,5,8,13,21,34]
	Output: [2,5,3,8,13,21,34]
	Explanation:
	The tree has only one odd level.
	The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.

	Example 2:
	Input: root = [7,13,11]
	Output: [7,11,13]
	Explanation:
	The nodes at level 1 are 13, 11, which are reversed and become 11, 13.

	Example 3:
	Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
	Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
	Explanation:
	The odd levels have non-zero values.
	The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
	The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.

	Constraints:
	The number of nodes in the tree is in the range [1, 2^14].
	0 <= Node.val <= 10^5
	root is a perfect binary tree.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TreeNode.treeLevel("2,5,3,8,13,21,34"), TreeNode.treeLevel("2,3,5,8,13,21,34")},
				{TreeNode.treeLevel("7,11,13"), TreeNode.treeLevel("7,13,11")},
				{TreeNode.treeLevel("0,2,1,0,0,0,0,2,2,2,2,1,1,1,1"), TreeNode.treeLevel("0,1,2,0,0,0,0,1,1,1,1,2,2,2,2")},
		});
	}

	public TreeNode reverseOddLevels(TreeNode root) {
		bfs(new List() {{add(root);}}, 0);
		return root;
	}

	private void bfs(List list, int level) {
		if ((level & 1) == 1)
			for (int left = list.head, right = list.tail - 1; left < right; )
				swap(list.nodes[left++], list.nodes[right--]);
		if (list.nodes[list.head].left == null) return;
		TreeNode node;
		for (int index = list.head, end = list.tail; index++ < end; list.add(node.right))
			list.add((node = list.poll()).left);
		bfs(list, ++level);
	}

	void swap(TreeNode a, TreeNode b) {
		if (a.val == b.val) return;
		int temp = b.val;
		b.val = a.val;
		a.val = temp;
	}

	class List {
		TreeNode[] nodes = new TreeNode[1 << 14];
		int head, tail;

		void add(TreeNode node) {nodes[tail++] = node;}

		TreeNode poll() {return nodes[head++];}
	}
}
