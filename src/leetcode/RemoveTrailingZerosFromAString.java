package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class RemoveTrailingZerosFromAString {
	/*
	Given a positive integer num represented as a string, return the integer num without trailing zeros as a string.

	Example 1:
	Input: num = "51230100"
	Output: "512301"
	Explanation: Integer "51230100" has 2 trailing zeros, we remove them and return integer "512301".

	Example 2:
	Input: num = "123"
	Output: "123"
	Explanation: Integer "123" has no trailing zeros, we return integer "123".

	Constraints:
	1 <= num.length <= 1000
	num consists of only digits.
	num doesn't have any leading zeros.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"512301", "51230100"},
				{"123", "123"},
		});
	}

	public String removeTrailingZeros(String num) {
		int last = num.length() - 1;
		while (last >= 0 && num.charAt(last) == '0') last--;
		return last < 0 ? "" : last + 1 == num.length() ? num : num.substring(0, last + 1);
	}
}
