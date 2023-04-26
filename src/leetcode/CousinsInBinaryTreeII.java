package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Wit
 */
public class CousinsInBinaryTreeII {
	/*
	Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins' values.
	Two nodes of a binary tree are cousins if they have the same depth with different parents.
	Return the root of the modified tree.
	Note that the depth of a node is the number of edges in the path from the root node to it.

	Example 1:
	Input: root = [5,4,9,1,10,null,7]
	Output: [0,0,0,7,7,null,11]
	Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
	- Node with value 5 does not have any cousins so its sum is 0.
	- Node with value 4 does not have any cousins so its sum is 0.
	- Node with value 9 does not have any cousins so its sum is 0.
	- Node with value 1 has a cousin with value 7 so its sum is 7.
	- Node with value 10 has a cousin with value 7 so its sum is 7.
	- Node with value 7 has cousins with values 1 and 10 so its sum is 11.

	Example 2:
	Input: root = [3,1,2]
	Output: [0,0,0]
	Explanation: The diagram above shows the initial binary tree and the binary tree after changing the value of each node.
	- Node with value 3 does not have any cousins so its sum is 0.
	- Node with value 1 does not have any cousins so its sum is 0.
	- Node with value 2 does not have any cousins so its sum is 0.

	Constraints:
	The number of nodes in the tree is in the range [1, 10^5].
	1 <= Node.val <= 10^4
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TreeNode.treeLevel("0,0,0"), TreeNode.treeLevel("3,1,2")},
				{TreeNode.treeLevel("0,0,0,7,7,null,11"), TreeNode.treeLevel("5,4,9,1,10,null,7")}
		});
	}

	public TreeNode replaceValueInTree(TreeNode root) {
		Queue<TreeNode> queue = new ArrayDeque<>(50_000) {{offer(root);}};
		TreeNode node;
		for (int currentLevelSum = root.val, nextLevelSum, size; !queue.isEmpty(); currentLevelSum = nextLevelSum)
			for (size = queue.size(), nextLevelSum = 0; size-- > 0; node.val = currentLevelSum - node.val)
				if ((node = queue.poll()).left != null) {
					queue.offer(node.left);
					if (node.right != null) {
						queue.offer(node.right);
						nextLevelSum += node.right.val = node.left.val += node.right.val;
					} else nextLevelSum += node.left.val;
				} else if (node.right != null) {
					queue.offer(node.right);
					nextLevelSum += node.right.val;
				}
		return root;
	}
}
