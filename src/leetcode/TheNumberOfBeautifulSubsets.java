package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class TheNumberOfBeautifulSubsets {
	/*
	You are given an array nums of positive integers and a positive integer k.
	A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
	Return the number of non-empty beautiful subsets of the array nums.
	A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two subsets are different if and only if the chosen indices to delete are different.

	Example 1:
	Input: nums = [2,4,6], k = 2
	Output: 4
	Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
	It can be proved that there are only 4 beautiful subsets in the array [2,4,6].

	Example 2:
	Input: nums = [1], k = 1
	Output: 1
	Explanation: The beautiful subset of the array nums is [1].
	It can be proved that there is only 1 beautiful subset in the array [1].

	Constraints:
	1 <= nums.length <= 20
	1 <= nums[i], k <= 1000
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				//{1048575, new int[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000}, 1},
				{23, new int[]{10, 4, 5, 7, 2, 1}, 3},
				{4, new int[]{2, 4, 6}, 2},
				{1, new int[]{1}, 1}
		});
	}

	public int beautifulSubsets(int[] nums, int k) {
		int result = 1, frequency[] = new int[1001];
		for (int num : nums) frequency[num]++;
		for (int num = 1; num < frequency.length; num++) {
			if (frequency[num] == 0) continue;
			int subsetWithout = 0, subsetWith = 0;
			for (int i = num; i < frequency.length && frequency[i] > 0; frequency[i] = 0, i += k) {
				subsetWithout += subsetWith;
				subsetWith = ((1 << frequency[i]) - 1) * ((subsetWithout - subsetWith) + 1);
			}
			result *= (subsetWith + subsetWithout + 1);
		}
		return result - 1;
	}
}
