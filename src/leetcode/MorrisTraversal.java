package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MorrisTraversal {
	/*
	Morris Traversal cost O(1) space to traverse a tree without using stack
	The basic idea is find the precedent node `prev` and then point the right child of the `prev` to current node
	*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{null, TreeNode.treeLevel("1,2,3,4,5,6,7,8")},
		});
	}

	public void inOrderMorrisTraversal(TreeNode node) {
		while (node != null) {
			// If left child is null, print the current node data. Move to right child.
			if (node.left == null) {
				System.out.println(node.val);
				node = node.right;
				continue;
			}
			// Find inorder predecessor
			TreeNode prev = node.left;
			while (prev.right != null && prev.right != node) prev = prev.right;
			// If the right child of inorder predecessor already points to this node
			if (prev.right == node) {
				prev.right = null;
				node = node.right;
			} else { // If right child doesn't point to this node, then print this node and make right child point to this node
				System.out.println(node.val);
				prev.right = node;
				node = node.left;
			}
		}
	}
}
