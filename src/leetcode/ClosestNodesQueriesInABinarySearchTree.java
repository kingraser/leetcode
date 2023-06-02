package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wit
 */
public class ClosestNodesQueriesInABinarySearchTree {
	/*
	You are given the root of a binary search tree and an array queries of size n consisting of positive integers.
	Find a 2D array answer of size n where answer[i] = [mini, maxi]:
	mini is the largest value in the tree that is smaller than or equal to queries[i]. If a such value does not exist, add -1 instead.
	maxi is the smallest value in the tree that is greater than or equal to queries[i]. If a such value does not exist, add -1 instead.
	Return the array answer.

	Example 1:
	Input: root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
	Output: [[2,2],[4,6],[15,-1]]
	Explanation: We answer the queries in the following way:
	- The largest number that is smaller or equal than 2 in the tree is 2, and the smallest number that is greater or equal than 2 is still 2. So the answer for the first query is [2,2].
	- The largest number that is smaller or equal than 5 in the tree is 4, and the smallest number that is greater or equal than 5 is 6. So the answer for the second query is [4,6].
	- The largest number that is smaller or equal than 16 in the tree is 15, and the smallest number that is greater or equal than 16 does not exist. So the answer for the third query is [15,-1].

	Example 2:
	Input: root = [4,null,9], queries = [3]
	Output: [[-1,4]]
	Explanation: The largest number that is smaller or equal to 3 in the tree does not exist, and the smallest number that is greater or equal to 3 is 4. So the answer for the query is [-1,4].

	Constraints:
	The number of nodes in the tree is in the range [2, 10^5].
	1 <= Node.val <= 10^6
	n == queries.length
	1 <= n <= 10^5
	1 <= queries[i] <= 10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.transferToInteger("[[2,2],[4,6],[15,-1]]"),
						TreeNode.treeLevel("6,2,13,1,4,9,15,null,null,null,null,null,null,14"),
						List.of(2, 5, 16)},
				{TestUtil.transferToInteger("[[-1,4]]"),
						TreeNode.treeLevel("4,null,9"),
						List.of(3)}
		});
	}

	int nodes[], count, index;

	public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
		count = 0;
		nodes = new int[100_000];
		inOrderTraverse(root);
		List<List<Integer>> res = new ArrayList<>(queries.size());
		for (Integer key : queries)
			if ((index = Arrays.binarySearch(nodes, 0, count, key)) >= 0) res.add(List.of(key, key));
			else res.add(List.of((index = -index - 1) < 1 ? -1 : nodes[index - 1], index < count ? nodes[index] : -1));
		return res;
	}

	void inOrderTraverse(TreeNode root) {
		if (root == null) return;
		inOrderTraverse(root.left);
		nodes[count++] = root.val;
		inOrderTraverse(root.right);
	}
}
