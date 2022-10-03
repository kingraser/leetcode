package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class FindUniqueBinaryString {
	/*
	Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.

	Example 1:
	Input: nums = ["01","10"]
	Output: "11"
	Explanation: "11" does not appear in nums. "00" would also be correct.

	Example 2:
	Input: nums = ["00","01"]
	Output: "11"
	Explanation: "11" does not appear in nums. "10" would also be correct.

	Example 3:
	Input: nums = ["111","011","001"]
	Output: "101"
	Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.

	Constraints:
	n == nums.length
	1 <= n <= 16
	nums[i].length == n
	nums[i] is either '0' or '1'.
	All the strings of nums are unique.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"11", new String[]{"01", "10"}},
				{"10", new String[]{"00", "01"}},
				{"000", new String[]{"111", "011", "001"}},
		});
	}

	public String findDifferentBinaryString(String[] nums) {
		StringBuilder ans = new StringBuilder(16);
		for (int i = 0; i < nums.length; i++)
			ans.append(nums[i].charAt(i) == '0' ? '1' : '0');              // Using ternary operator
		return ans.toString();
	}
}
