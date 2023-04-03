package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import static leetcode.util.ArrayUtil.swap;

/**
 * @author Wit
 */
public class MiceAndCheese {
	/*
	There are two mice and n different types of cheese, each type of cheese should be eaten by exactly one mouse.
	A point of the cheese with index i (0-indexed) is:
	reward1[i] if the first mouse eats it.
	reward2[i] if the second mouse eats it.
	You are given a positive integer array reward1, a positive integer array reward2, and a non-negative integer k.
	Return the maximum points the mice can achieve if the first mouse eats exactly k types of cheese.

	Example 1:
	Input: reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
	Output: 15
	Explanation: In this example, the first mouse eats the 2nd (0-indexed) and the 3rd types of cheese, and the second mouse eats the 0th and the 1st types of cheese.
	The total points are 4 + 4 + 3 + 4 = 15.
	It can be proven that 15 is the maximum total points that the mice can achieve.

	Example 2:
	Input: reward1 = [1,1], reward2 = [1,1], k = 2
	Output: 2
	Explanation: In this example, the first mouse eats the 0th (0-indexed) and 1st types of cheese, and the second mouse does not eat any cheese.
	The total points are 1 + 1 = 2.
	It can be proven that 2 is the maximum total points that the mice can achieve.

	Constraints:
	1 <= n == reward1.length == reward2.length <= 10^5
	1 <= reward1[i], reward2[i] <= 1000
	0 <= k <= n
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{15, new int[]{1, 1, 3, 4}, new int[]{4, 4, 1, 1}, 2},
				{2, new int[]{1, 1}, new int[]{1, 1}, 2},
		});
	}

	public int miceAndCheese(int[] reward1, int[] reward2, int k) {
		if (k > reward1.length >> 1) return miceAndCheese(reward2, reward1, reward1.length - k);
		int result = 0, diffs[] = new int[reward1.length];
		for (int i = 0; i < diffs.length; result += reward2[i++]) diffs[i] = reward1[i] - reward2[i];
		for (int partition, start = 0, end = diffs.length - 1; ; )
			if ((partition = partition(diffs, start, end)) == k) break;
			else if (partition < k) start = partition + 1;
			else end = partition - 1;
		for (int i = 0; k-- > 0; ) result += diffs[i++];
		return result;
	}

	/**
	 * @param nums the array
	 * @param from from index (inclusive)
	 * @param to   to index(inclusive)
	 * @return index k where element k is smaller than elements in [from,k) and is larger than elements in (k,end]
	 */
	private int partition(int[] nums, int from, int to) {
		int element = nums[from], idx = from;
		for (int i = from + 1; i <= to; i++)
			if (nums[i] > element) swap(nums, ++idx, i);
		swap(nums, from, idx);
		return idx;
	}
}
