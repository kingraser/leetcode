package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Wit
 */
public class MinimumImpossibleOR {
	/*
	You are given a 0-indexed integer array nums.
	We say that an integer x is expressible from nums if there exist some integers 0 <= index1 < index2 < ... < indexk < nums.length for which nums[index1] | nums[index2] | ... | nums[indexk] = x. In other words, an integer is expressible if it can be written as the bitwise OR of some subsequence of nums.
	Return the minimum positive non-zero integer that is not expressible from nums.

	Example 1:
	Input: nums = [2,1]
	Output: 4
	Explanation: 1 and 2 are already present in the array. We know that 3 is expressible, since nums[0] | nums[1] = 2 | 1 = 3. Since 4 is not expressible, we return 4.

	Example 2:
	Input: nums = [5,3,2]
	Output: 1
	Explanation: We can show that 1 is the smallest number that is not expressible.

	Constraints:
	1 <= nums.length <= 10^5
	1 <= nums[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, new int[]{2, 1}},
				{1, new int[]{5, 3, 2}}
		});
	}

	public int minImpossibleOR(int[] nums) {
		Set<Integer> set = new HashSet<>(nums.length << 1);
		for (int num : nums) set.add(num);
		int result = 1;
		while (set.contains(result)) {result <<= 1;}
		return result;
	}

	public int minImpossibleORII(int[] nums) {
		int mask = 0;
		for (int num : nums) if ((num & (num - 1)) == 0) mask |= num;
		mask = ~mask;
		return (mask & (-mask));
	}
}
