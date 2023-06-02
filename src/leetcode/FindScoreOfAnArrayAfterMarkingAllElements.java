package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author Wit
 */
public class FindScoreOfAnArrayAfterMarkingAllElements {
	/*
	You are given an array nums consisting of positive integers.
	Starting with score = 0, apply the following algorithm:
	Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest index.
	Add the value of the chosen integer to score.
	Mark the chosen element and its two adjacent elements if they exist.
	Repeat until all the array elements are marked.
	Return the score you get after applying the above algorithm.

	Example 1:
	Input: nums = [2,1,3,4,5,2]
	Output: 7
	Explanation: We mark the elements as follows:
	- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,1,3,4,5,2].
	- 2 is the smallest unmarked element, so we mark it and its left adjacent element: [2,1,3,4,5,2].
	- 4 is the only remaining unmarked element, so we mark it: [2,1,3,4,5,2].
	Our score is 1 + 2 + 4 = 7.

	Example 2:
	Input: nums = [2,3,5,1,3,2]
	Output: 5
	Explanation: We mark the elements as follows:
	- 1 is the smallest unmarked element, so we mark it and its two adjacent elements: [2,3,5,1,3,2].
	- 2 is the smallest unmarked element, since there are two of them, we choose the left-most one, so we mark the one at index 0 and its right adjacent element: [2,3,5,1,3,2].
	- 2 is the only remaining unmarked element, so we mark it: [2,3,5,1,3,2].
	Our score is 1 + 2 + 2 = 5.

	Constraints:
	1 <= nums.length <= 10^5
	1 <= nums[i] <= 10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{7L, new int[]{2, 1, 3, 4, 5, 2}},
				{5L, new int[]{2, 3, 5, 1, 3, 2}},
		});
	}

	public long findScore(int[] nums) {
		long result = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[0] == a2[0] ? a1[1] - a2[1] : a1[0] - a2[0]);
		for (int i = 0; i < nums.length; ) pq.add(new int[]{nums[i], i++});
		boolean[] marked = new boolean[nums.length];
		for (int poll[], index; !pq.isEmpty(); ) {
			if (marked[index = (poll = pq.poll())[1]]) continue;
			result += poll[0];
			marked[index] = true;
			if (index > 0) marked[index - 1] = true;
			if (++index < nums.length) marked[index] = true;
		}
		return result;
	}

	public long findScoreII(int[] nums) {
		long res = 0;
		for (int i = 0, length = nums.length; i < length; i += 2) {
			int start = i;
			while (i + 1 < length && nums[i] > nums[i + 1]) i++; // i 停在local min
			// [start, i] length: 1 ~ x 单调递减
			for (int j = i; j >= start; j -= 2) res += nums[j];
		}
		return res;
	}
}
