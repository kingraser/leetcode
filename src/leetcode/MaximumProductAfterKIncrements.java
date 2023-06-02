package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class MaximumProductAfterKIncrements {
	/*
	You are given an array of non-negative integers nums and an integer k. In one operation, you may choose any element from nums and increment it by 1.
	Return the maximum product of nums after at most k operations. Since the answer may be very large, return it modulo 109 + 7. Note that you should maximize the product before taking the modulo.

	Example 1:
	Input: nums = [0,4], k = 5
	Output: 20
	Explanation: Increment the first number 5 times.
	Now nums = [5, 4], with a product of 5 * 4 = 20.
	It can be shown that 20 is maximum product possible, so we return 20.
	Note that there may be other ways to increment nums to have the maximum product.

	Example 2:
	Input: nums = [6,3,3,2], k = 2
	Output: 216
	Explanation: Increment the second number 1 time and increment the fourth number 1 time.
	Now nums = [6, 4, 3, 3], with a product of 6 * 4 * 3 * 3 = 216.
	It can be shown that 216 is maximum product possible, so we return 216.
	Note that there may be other ways to increment nums to have the maximum product.

	Constraints:
	1 <= nums.length, k <= 10^5
	0 <= nums[i] <= 10^6
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{20160, new int[]{7, 10, 6, 1, 8}, 5},
				{20, new int[]{0, 4}, 5},
				{216, new int[]{6, 3, 3, 2}, 2},
		});
	}

	int module = 1000_000_007;

	public int maximumProduct(int[] nums, int k) {
		Arrays.sort(nums);
		int right = 1;
		long result = 1, sum = k + nums[0];
		while (right < nums.length && sum >= ((long) nums[right]) * right) sum += nums[right++];
		long remainder = sum % right, remainderCopy = remainder, q = sum / right, qPlusOne = q + 1;
		while (remainder++ < right) result = result * q % module;
		while (remainderCopy-- > 0) result = result * qPlusOne % module;
		while (right < nums.length) result = result * nums[right++] % module;
		return (int) result;
	}
}
