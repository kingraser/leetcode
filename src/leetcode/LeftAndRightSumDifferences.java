package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LeftAndRightSumDifferences {
	/*
	Given a 0-indexed integer array nums, find a 0-indexed integer array answer where:

	answer.length == nums.length.
	answer[i] = |leftSum[i] - rightSum[i]|.
	Where:
	leftSum[i] is the sum of elements to the left of the index i in the array nums. If there is no such element, leftSum[i] = 0.
	rightSum[i] is the sum of elements to the right of the index i in the array nums. If there is no such element, rightSum[i] = 0.
	Return the array answer.

	Example 1:
	Input: nums = [10,4,8,3]
	Output: [15,1,11,22]
	Explanation: The array leftSum is [0,10,14,22] and the array rightSum is [15,11,3,0].
	The array answer is [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22].

	Example 2:
	Input: nums = [1]
	Output: [0]
	Explanation: The array leftSum is [0] and the array rightSum is [0].
	The array answer is [|0 - 0|] = [0].

	Constraints:
	1 <= nums.length <= 1000
	1 <= nums[i] <= 10^5C
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{15, 1, 11, 22}, new int[]{10, 4, 8, 3}},
				{new int[]{0}, new int[]{1}},
		});
	}

	public int[] leftRightDifference(int[] nums) {
		int sum = 0, result[] = new int[nums.length];
		for (int num : nums) sum += num;
		for (int i = 0, left = 0; i < nums.length; left += nums[i++]) result[i] = Math.abs((sum -= nums[i]) - left);
		return result;
	}
}
