package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class MaxSumOfAPairWithEqualSumOfDigits {
	/*
	You are given a 0-indexed array nums consisting of positive integers. You can choose two indices i and j, such that i != j, and the sum of digits of the number nums[i] is equal to that of nums[j].
	Return the maximum value of nums[i] + nums[j] that you can obtain over all possible indices i and j that satisfy the conditions.

	Example 1:
	Input: nums = [18,43,36,13,7]
	Output: 54
	Explanation: The pairs (i, j) that satisfy the conditions are:
	- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
	- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
	So the maximum sum that we can obtain is 54.

	Example 2:
	Input: nums = [10,12,19,14]
	Output: -1
	Explanation: There are no two numbers that satisfy the conditions, so we return -1.

	Constraints:
	1 <= nums.length <= 10^5
	1 <= nums[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{54, new int[]{18, 43, 36, 13, 7}},
				{-1, new int[]{10, 12, 19, 14}}
		});
	}

	int result;

	public int maximumSum(int[] nums) {
		result = -1;
		Map<Integer, int[]> map = new HashMap<>(nums.length);
		for (int num : nums) add(map.computeIfAbsent(getDigitSum(num), k -> new int[2]), num);
		return result;
	}

	int getDigitSum(int num) {
		int result = 0;
		while (num != 0) {
			result += num % 10;
			num /= 10;
		}
		return result;
	}

	void add(int[] array, int num) {
		if (array[0] == 0) array[0] = num;
		else if (num > array[0]) result = Integer.max(result, (array[1] = array[0]) + (array[0] = num));
		else if (num > array[1]) result = Integer.max(result, array[0] + (array[1] = num));
	}
}
