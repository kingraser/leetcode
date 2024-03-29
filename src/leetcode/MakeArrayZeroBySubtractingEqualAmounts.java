package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MakeArrayZeroBySubtractingEqualAmounts {
	/*
	You are given a non-negative integer array nums. In one operation, you must:
	Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
	Subtract x from every positive element in nums.
	Return the minimum number of operations to make every element in nums equal to 0.

	Example 1:
	Input: nums = [1,5,0,3,5]
	Output: 3
	Explanation:
	In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
	In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
	In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].

	Example 2:
	Input: nums = [0]
	Output: 0
	Explanation: Each element in nums is already 0 so no operations are needed.

	Constraints:
	1 <= nums.length <= 100
	0 <= nums[i] <= 100
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, new int[]{1, 5, 0, 3, 5}},
				{0, new int[1]}
		});
	}

	//It is always best to set x as the smallest non-zero element in nums.
	//Elements with the same value will always take the same number of operations to become 0.
	//Contrarily, elements with different values will always take a different number of operations to become 0.
	//The answer is the number of unique non-zero numbers in nums.
	public int minimumOperations(int[] nums) {
		int result = 0, map[] = new int[101];
		for (int num : nums) if (num > 0 && map[num]++ == 0) result++;
		return result;
	}
}
