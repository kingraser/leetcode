package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Wit
 */
public class RemoveStonesToMinimizeTheTotal {
	/*
	You are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile, and an integer k. You should apply the following operation exactly k times:
	Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
	Notice that you can apply the operation on the same pile more than once.
	Return the minimum possible total number of stones remaining after applying the k operations.
	floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).

	Example 1:
	Input: piles = [5,4,9], k = 2
	Output: 12
	Explanation: Steps of a possible scenario are:
	- Apply the operation on pile 2. The resulting piles are [5,4,5].
	- Apply the operation on pile 0. The resulting piles are [3,4,5].
	The total number of stones in [3,4,5] is 12.

	Example 2:
	Input: piles = [4,3,6,7], k = 3
	Output: 12
	Explanation: Steps of a possible scenario are:
	- Apply the operation on pile 2. The resulting piles are [4,3,3,7].
	- Apply the operation on pile 3. The resulting piles are [4,3,3,4].
	- Apply the operation on pile 0. The resulting piles are [2,3,3,4].
	The total number of stones in [2,3,3,4] is 12.

	Constraints:
	1 <= piles.length <= 10^5
	1 <= piles[i] <= 10^4
	1 <= k <= 10^5
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{12, new int[]{5, 4, 9}, 2},
				{12, new int[]{4, 3, 6, 7}, 3}
		});
	}

	public int minStoneSum(int[] piles, int k) {
		int result = 0, max;
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(piles.length, Comparator.reverseOrder());
		for (int pile : piles) {
			result += pile;
			priorityQueue.add(pile);
		}
		while (k-- > 0 && (max = priorityQueue.poll()) > 1) {
			result -= max >> 1;
			priorityQueue.add(max - (max >> 1));
		}
		return result;
	}
}
