package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class XORQueriesOfASubarray {
	/*
	You are given an array arr of positive integers. You are also given the array queries where queries[i] = [left_i, right_i].
	For each query i compute the XOR of elements from left_i to right_i (that is, arr[left_i] XOR arr[left_i + 1] XOR ... XOR arr[right_i] ).
	Return an array answer where answer[i] is the answer to the ith query.

	Example 1:
	Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
	Output: [2,7,14,8]
	Explanation:
	The binary representation of the elements in the array are:
	1 = 0001
	3 = 0011
	4 = 0100
	8 = 1000
	The XOR values for queries are:
	[0,1] = 1 xor 3 = 2
	[1,2] = 3 xor 4 = 7
	[0,3] = 1 xor 3 xor 4 xor 8 = 14
	[3,3] = 8

	Example 2:
	Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
	Output: [8,0,4,4]

	Constraints:
	1 <= arr.length, queries.length <= 3 * 10^4
	1 <= arr[i] <= 10^9
	queries[i].length == 2
	0 <= left_i <= right_i < arr.length
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.getArray("[2,7,14,8]"), TestUtil.getArray("[1,3,4,8]"), TestUtil.getArray("[[0,1],[1,2],[0,3],[3,3]]")},
				{TestUtil.getArray("[8,0,4,4]"), TestUtil.getArray("[4,8,2,10]"), TestUtil.getArray("[[2,3],[1,3],[0,0],[0,3]]")},
		});
	}

	public int[] xorQueries(int[] arr, int[][] queries) {
		int[] xorPrefix = new int[arr.length + 1], result = new int[queries.length];
		for (int i = 0; i < arr.length; i++) xorPrefix[i + 1] = xorPrefix[i] ^ arr[i];
		for (int i = 0; i < queries.length; i++) result[i] = xorPrefix[queries[i][1] + 1] ^ xorPrefix[queries[i][0]];
		return result;
	}

}