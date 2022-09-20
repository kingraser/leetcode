package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * @author Wit
 */
public class MinimumCostTreeFromLeafValues {
	/*
	Given an array arr of positive integers, consider all binary trees such that:
	Each node has either 0 or 2 children;
	The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
	The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree, respectively.
	Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node. It is guaranteed this sum fits into a 32-bit integer.
	A node is a leaf if and only if it has zero children.

	Example 1:
	Input: arr = [6,2,4]
	Output: 32
	Explanation: There are two possible trees shown.
	The first has a non-leaf node sum 36, and the second has non-leaf node sum 32.

	Example 2:
	Input: arr = [4,11]
	Output: 44

	Constraints:
	2 <= arr.length <= 40
	1 <= arr[i] <= 15
	It is guaranteed that the answer fits into a 32-bit signed integer (i.e., it is less than 231).
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{32, new int[]{6, 2, 4}},
				{44, new int[]{4, 11}}
		});
	}
	/*
	Let's review the problem again.

	When we build a node in the tree, we compared the two numbers a and b.
	In this process, the smaller one is removed, and we won't use it anymore, and the bigger one actually stays.

	The problem can be translated as following:
	Given an array A, choose two neighbors in the array a and b,
	we can remove the smaller one min(a,b) with the cost is a * b.
	What is the minimum cost to remove the whole array until only one left?

	To remove a number `a`, it needs a cost a * b, where `b >= a`.
	So a has to be removed by a bigger number.
	We want to minimize this cost, so we need to minimize b.

	b has two candidates, the first bigger number on the left, the first bigger number on the right.

	The cost to remove `a` is a * min(left, right).
	*/
	public int mctFromLeafValues(int[] arr) {
		int result = 0;
		ArrayDeque<Integer> stack = new ArrayDeque<>(arr.length) {{addLast(Integer.MAX_VALUE);}};
		for (int num : arr) {
			while (stack.peekLast() <= num) result += stack.pollLast() * Math.min(stack.peekLast(), num);
			stack.addLast(num);
		}
		while (stack.size() > 2) result += stack.pollLast() * stack.peekLast();
		return result;
	}
}
