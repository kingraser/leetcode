package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumProductOfSplitBinaryTree {
	/*
	Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
	Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.
	Note that you need to maximize the answer before taking the mod and not after taking it.

	Example 1:
	Input: root = [1,2,3,4,5,6]
	Output: 110
	Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)

	Example 2:
	Input: root = [1,null,2,3,4,null,null,5,6]
	Output: 90
	Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)

	Constraints:
	The number of nodes in the tree is in the range [2, 5 * 10^4].
	1 <= Node.val <= 10^4
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{110, TreeNode.treeLevel("1,2,3,4,5,6")},
				{90, TreeNode.treeLevel("1,null,2,3,4,null,null,5,6")},
		});
	}

	long res, total, module = 1_000_000_000 + 7;

	public int maxProduct(TreeNode root) {
		res = total = 0;
		total = sum(root);
		sum(root);
		return (int) (res % module);
	}

	private long sum(TreeNode root) {
		if (root == null) return 0;
		long sum = root.val + sum(root.left) + sum(root.right);
		res = Math.max(res, sum * (total - sum));
		return sum;
	}
}
