package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * @author Wit
 */
public class JumpGameVI {
	/*
	You are given a 0-indexed integer array nums and an integer k.
	You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
	You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
	Return the maximum score you can get.

	Example 1:
	Input: nums = [1,-1,-2,4,-7,3], k = 2
	Output: 7
	Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.

	Example 2:
	Input: nums = [10,-5,-2,4,0,3], k = 3
	Output: 17
	Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.

	Example 3:
	Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
	Output: 0

	Constraints:
	1 <= nums.length, k <= 10^5
	-10^4 <= nums[i] <= 10^4
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{7, new int[]{1, -1, -2, 4, -7, 3}, 2},
				{17, new int[]{10, -5, -2, 4, 0, 3}, 3},
				{0, new int[]{1, -5, -20, 4, -1, 3, -6, -3}, 2},
		});
	}

	//Let dp[i] be "the maximum score to reach the end starting at index i".
	//The answer for dp[i] is nums[i] + max{dp[i+j]} for 1 <= j <= k. That gives an O(n*k) solution.
	//Instead of checking every j for every i, keep track of the largest dp[i] values in a heap and calculate dp[i] from right to left.
	//When the largest value in the heap is out of bounds of the current index, remove it and keep checking.
	public int maxResult(int[] nums, int k) {
		int res = nums[0];
		ArrayDeque<int[]> q = new ArrayDeque<>(nums.length) {{addLast(new int[]{nums[0], 0});}};
		for (int i = 1; i < nums.length; ++i) {
			while (i - q.peekFirst()[1] > k) q.pollFirst();
			res = q.peekFirst()[0] + nums[i];
			while (!q.isEmpty() && res >= q.peekLast()[0]) q.pollLast();
			q.addLast(new int[]{res, i});
		}
		return res;
	}
}
