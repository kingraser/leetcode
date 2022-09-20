package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class AmountOfTimeForBinaryTreeToBeInfected {
	/*
	You are given the root of a binary tree with unique values, and an integer start. At minute 0, an infection starts from the node with value start.
	Each minute, a node becomes infected if:
	The node is currently uninfected.
	The node is adjacent to an infected node.
	Return the number of minutes needed for the entire tree to be infected.

	Example 1:
	Input: root = [1,5,3,null,4,10,6,9,2], start = 3
	Output: 4
	Explanation: The following nodes are infected during:
	- Minute 0: Node 3
	- Minute 1: Nodes 1, 10 and 6
	- Minute 2: Node 5
	- Minute 3: Node 4
	- Minute 4: Nodes 9 and 2
	It takes 4 minutes for the whole tree to be infected so we return 4.

	Example 2:
	Input: root = [1], start = 1
	Output: 0
	Explanation: At minute 0, the only node in the tree is infected so we return 0.

	Constraints:
	The number of nodes in the tree is in the range [1, 10^5].
	1 <= Node.val <= 10^5
	Each node has a unique value.
	A node with a value of start exists in the tree.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5, TreeNode.treeLevel("19,4,6,9,11,18,1,null,null,null,17,null,8,null,null,null,null,null,20"), 1},
				{6, TreeNode.treeLevel("16,null,20,7,12,null,15,null,19,null,1,2"), 1},
				{4, TreeNode.treeLevel("1,5,3,null,4,10,6,9,2"), 3},
				{0, TreeNode.treeLevel("1"), 1}
		});
	}

	int max;

	public int amountOfTime(TreeNode root, int start) {
		max = 0;
		getDistance(root, start);
		return max;
	}

	int getHeight(TreeNode node) {return node == null ? 0 : 1 + Math.max(getHeight(node.left), getHeight(node.right));}

	// index 0 for height
	// index 1 for distance to start
	int[] getDistance(TreeNode node, int start) {
		if (node == null) return new int[2];
		if (node.val == start) return new int[]{1 + (max = Math.max(getHeight(node.left), getHeight(node.right))), 1};
		int height, distance[] = getDistance(node.left, start);
		if (distance[1] == 0) {
			height = distance[0];
			distance = getDistance(node.right, start);
		} else height = getHeight(node.right);
		if (distance[1] > 0) max = Math.max(max, height + distance[1]++);
		distance[0] = 1 + Math.max(distance[0], height);
		return distance;
	}
}
