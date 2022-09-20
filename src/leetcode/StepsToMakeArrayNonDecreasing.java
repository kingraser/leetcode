package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;

/**
 * @author Wit
 */
public class StepsToMakeArrayNonDecreasing {
	/*
	You are given a 0-indexed integer array nums. In one step, remove all elements nums[i] where nums[i - 1] > nums[i] for all 0 < i < nums.length.
	Return the number of steps performed until nums becomes a non-decreasing array.

	Example 1:
	Input: nums = [5,3,4,4,7,3,6,11,8,5,11]
	Output: 3
	Explanation: The following are the steps performed:
	- Step 1: [5,3,4,4,7,3,6,11,8,5,11] becomes [5,4,4,7,6,11,11]
	- Step 2: [5,4,4,7,6,11,11] becomes [5,4,7,11,11]
	- Step 3: [5,4,7,11,11] becomes [5,7,11,11]
	[5,7,11,11] is a non-decreasing array. Therefore, we return 3.

	Example 2:
	Input: nums = [4,5,7,7,13]
	Output: 0
	Explanation: nums is already a non-decreasing array. Therefore, we return 0.

	Constraints:
	1 <= nums.length <= 10^5
	1 <= nums[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{5, 14, 15, 2, 11, 5, 13, 15}},
				{6, new int[]{10, 1, 2, 3, 4, 5, 6, 1, 2, 3}},
				{3, new int[]{5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11}},
				{0, new int[]{4, 5, 7, 7, 13}},
		});
	}

	//dp[i] for the count of nums that can be eaten by the num with i-th index on its right side
	public int totalSteps(int[] nums) {
		int result = 0, countOfNumCanBeEatenOnItsRightSide[] = new int[nums.length];
		ArrayDeque<Integer> stack = new ArrayDeque<>(nums.length);
		for (int i = nums.length - 1; i >= 0; result = Math.max(result, countOfNumCanBeEatenOnItsRightSide[i]), stack.push(i--))
			while (!stack.isEmpty() && nums[i] > nums[stack.peek()])
				countOfNumCanBeEatenOnItsRightSide[i] = Math.max(++countOfNumCanBeEatenOnItsRightSide[i], countOfNumCanBeEatenOnItsRightSide[stack.pop()]);
		return result;
	}
}
