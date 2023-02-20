package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Wit
 */
public class MaximumSubsequenceScore {
	/*
	You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k. You must choose a subsequence of indices from nums1 of length k.
	For chosen indices i0, i1, ..., ik - 1, your score is defined as:
	The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
	It can be defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
	Return the maximum possible score.

	A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting some or no elements.

	Example 1:
	Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
	Output: 12
	Explanation:
	The four possible subsequence scores are:
	- We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
	- We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
	- We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
	- We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
	Therefore, we return the max score, which is 12.

	Example 2:
	Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
	Output: 30
	Explanation:
	Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.

	Constraints:
	n == nums1.length == nums2.length
	1 <= n <= 10^5
	0 <= nums1[i], nums2[j] <= 10^5
	1 <= k <= n
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{12L, new int[]{1, 3, 3, 2}, new int[]{2, 1, 3, 4}, 3},
				{30L, new int[]{4, 2, 3, 1, 1}, new int[]{7, 5, 10, 9, 6}, 1},
		});
	}

	public long maxScore(int[] nums1, int[] nums2, int k) {
		int[][] pairArray = new int[nums1.length][2];
		for (int i = 0; i < nums1.length; i++) {
			pairArray[i][0] = nums2[i];
			pairArray[i][1] = nums1[i];
		}
		Arrays.sort(pairArray, (a1, a2) -> a2[0] - a1[0]);
		PriorityQueue<Integer> pq = new PriorityQueue<>(k);
		long res = 0, sumS = 0;
		for (int[] pair : pairArray) {
			pq.add(pair[1]);
			sumS = (sumS + pair[1]);
			if (pq.size() > k) sumS -= pq.poll();
			if (pq.size() == k) res = Math.max(res, (sumS * pair[0]));
		}
		return res;
	}
}
