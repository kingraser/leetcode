package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class BinaryTreeCameras {
	/*
	You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.
	Return the minimum number of cameras needed to monitor all nodes of the tree.

	Example 1:
	Input: root = [0,0,null,0,0]
	Output: 1
	Explanation: One camera is enough to monitor all nodes if placed as shown.

	Example 2:
	Input: root = [0,0,null,0,null,0,null,null,0]
	Output: 2
	Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.

	Constraints:
	The number of nodes in the tree is in the range [1, 1000].
	Node.val == 0
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{1, TreeNode.treeLevel("0,0,null,0,0")},
				{2, TreeNode.treeLevel("0,0,null,0,null,0,null,null,0")}
		});
	}

	/*
	Consider a node in the tree.
	It can be covered by its parent, itself, its two children.
	Four options.

	Consider the root of the tree.
	It can be covered by left child, or right child, or itself.
	Three options.

	Consider one leaf of the tree.
	It can be covered by its parent or by itself.
	Two options.

	If we set a camera at the leaf, the camera can cover the leaf and its parent.
	If we set a camera at its parent, the camera can cover the leaf, its parent and its sibling.

	We can see that the second plan is always better than the first.
	Now we have only one option, set up camera to all leaves' parent.

	Here is our greedy solution:
	Set cameras on all leaves' parents, then remove all covered nodes.
	Repeat step 1 until all nodes are covered.
	Explanation:

	Apply a recursive function dfs.
	Return 0 if it's a leaf.
	Return 1 if it's a parent of a leaf, with a camera on this node.
	Return 2 if it's covered, without a camera on this node.

	For each node,
	if it has a child, which is leaf (node 0), then it needs camera.
	if it has a child, which is the parent of a leaf (node 1), then it's covered.

	If it needs camera, then res++ and we return 1.
	If it's covered, we return 2.
	Otherwise, we return 0.
	*/
	int res = 0;

	public int minCameraCover(TreeNode root) {
		return (dfs(root) < 1 ? 1 : 0) + res;
	}

	public int dfs(TreeNode root) {
		if (root == null) return 2;
		int left = dfs(root.left), right = dfs(root.right);
		if (left == 0 || right == 0) {
			res++;
			return 1;
		}
		return left == 1 || right == 1 ? 2 : 0;
	}
}
