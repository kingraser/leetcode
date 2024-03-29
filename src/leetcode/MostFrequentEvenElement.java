package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MostFrequentEvenElement {
	/*
	Given an integer array nums, return the most frequent even element.
	If there is a tie, return the smallest one. If there is no such element, return -1.

	Example 1:
	Input: nums = [0,1,2,2,4,4,1]
	Output: 2
	Explanation:
	The even elements are 0, 2, and 4. Of these, 2 and 4 appear the most.
	We return the smallest one, which is 2.

	Example 2:
	Input: nums = [4,4,4,9,2,4]
	Output: 4
	Explanation: 4 is the even element appears the most.

	Example 3:
	Input: nums = [29,47,21,41,13,37,25,7]
	Output: -1
	Explanation: There is no even element.

	Constraints:
	1 <= nums.length <= 2000
	0 <= nums[i] <= 10^5
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, new int[]{0, 1, 2, 2, 4, 4, 1}},
				{4, new int[]{4, 4, 4, 9, 2, 4}},
				{-1, new int[]{29, 47, 21, 41, 13, 37, 25, 7}},
		});
	}

	public int mostFrequentEven(int[] nums) {
		int result = -1, map[] = new int[1_00_001];
		for (int num : nums)
			if ((num & 1) == 0 && (result == -1 || ++map[num] > map[result] || map[num] == map[result] && num < result))
				result = num;
		return result;
	}
}
