package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class MaximumTotalRewardUsingOperationsI {
	/*
	You are given an integer array rewardValues of length n, representing the values of rewards.
	Initially, your total reward x is 0, and all indices are unmarked. You are allowed to perform the following operation any number of times:
		Choose an unmarked index i from the range [0, n - 1].
		If rewardValues[i] is greater than your current total reward x, then add rewardValues[i] to x (i.e., x = x + rewardValues[i]), and mark the index i.
	Return an integer denoting the maximum total reward you can collect by performing the operations optimally.

	Example 1:
	Input: rewardValues = [1,1,3,3]
	Output: 4
	Explanation:
	During the operations, we can choose to mark the indices 0 and 2 in order, and the total reward will be 4, which is the maximum.

	Example 2:
	Input: rewardValues = [1,6,4,3,2]
	Output: 11
	Explanation:
	Mark the indices 0, 2, and 1 in order. The total reward will then be 11, which is the maximum.

	Constraints:
		1 <= rewardValues.length <= 2000
		1 <= rewardValues[i] <= 2000
	*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, new int[]{1, 1, 3, 3}},
				{11, new int[]{1, 6, 4, 3, 2}},
				{3999, IntStream.rangeClosed(1, 2000).toArray()}
		});
	}

	private int sortedSet(int[] values) {
		int max = 0, uniqueCount = 0;
		for (int value : values) if (value > max) max = value;
		boolean[] alreadySeen = new boolean[max + 1];
		for (int value : values) {
			if (alreadySeen[value]) continue;
			uniqueCount++;
			alreadySeen[value] = true;
		}
		for (int i = uniqueCount; i > 0; max--) if (alreadySeen[max]) values[--i] = max;
		return uniqueCount;
	}

	public int maxTotalReward(int[] rewardValues) {
		int length = sortedSet(rewardValues), max = rewardValues[length - 1], maxSum = 0;
		boolean[] isSumPossible = new boolean[max];
		isSumPossible[0] = true;
		for (int sum = rewardValues[0], last = 1; sum < max; sum++) {
			while (last < length && rewardValues[last] <= sum) last++;
			for (int i = last - 1, halfSum = sum >> 1; i >= 0 && rewardValues[i] > halfSum; i--) {
				if (!isSumPossible[sum - rewardValues[i]]) continue;
				isSumPossible[maxSum = sum] = true;
				break;
			}
		}
		return maxSum + max;
	}

}
