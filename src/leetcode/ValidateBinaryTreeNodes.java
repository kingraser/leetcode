package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ValidateBinaryTreeNodes {
	/*
	You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.
	If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
	Note that the nodes have no values and that we only use the node numbers in this problem.

	Example 1:
	Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
	Output: true

	Example 2:
	Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
	Output: false

	Example 3:
	Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
	Output: false

	Constraints:
	n == leftChild.length == rightChild.length
	1 <= n <= 10^4
	-1 <= leftChild[i], rightChild[i] <= n - 1
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, 4, new int[]{1, -1, 3, -1}, new int[]{2, -1, -1, -1}},
				{false, 4, new int[]{1, -1, 3, -1}, new int[]{2, 3, -1, -1}},
				{false, 2, new int[]{1, 0}, new int[]{-1, -1}},
		});
	}

	public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
		int inDegree[] = new int[n], root = -1;
		for (int i = 0; i < n; i++) {
			if (leftChild[i] != -1 && inDegree[leftChild[i]]++ > 0) return false;
			if (rightChild[i] != -1 && inDegree[rightChild[i]]++ > 0) return false;
		}
		for (int i = 0; i < n; i++)
			if (inDegree[i] == 0) {
				root = i;
				break;
			}
		return root != -1 && dfs(leftChild, rightChild, root) == n;
	}

	private int dfs(int[] leftChild, int[] rightChild, int node) {
		if (node == -1) return 0;
		return 1 + dfs(leftChild, rightChild, leftChild[node]) + dfs(leftChild, rightChild, rightChild[node]);
	}
}
