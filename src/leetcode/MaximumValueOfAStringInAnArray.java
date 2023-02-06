package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumValueOfAStringInAnArray {
	/*
	The value of an alphanumeric string can be defined as:
	The numeric representation of the string in base 10, if it comprises digits only.
	The length of the string, otherwise.
	Given an array strs of alphanumeric strings, return the maximum value of any string in strs.

	Example 1:
	Input: strs = ["alic3","bob","3","4","00000"]
	Output: 5
	Explanation:
	- "alic3" consists of both letters and digits, so its value is its length, i.e. 5.
	- "bob" consists only of letters, so its value is also its length, i.e. 3.
	- "3" consists only of digits, so its value is its numeric equivalent, i.e. 3.
	- "4" also consists only of digits, so its value is 4.
	- "00000" consists only of digits, so its value is 0.
	Hence, the maximum value is 5, of "alic3".

	Example 2:
	Input: strs = ["1","01","001","0001"]
	Output: 1
	Explanation:
	Each string in the array has value 1. Hence, we return 1.

	Constraints:
	1 <= strs.length <= 100
	1 <= strs[i].length <= 9
	strs[i] consists of only lowercase English letters and digits.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5, new String[]{"alic3", "bob", "3", "4", "00000"}},
				{1, new String[]{"1", "01", "001", "0001"}},
		});
	}

	public int maximumValue(String[] strs) {
		int result = 0;
		for (String str : strs) result = Integer.max(result, getValue(str));
		return result;
	}

	int getValue(String s) {
		int result = 0;
		for (int i = 0, length = s.length(), c; i < length; )
			if ((c = s.charAt(i++)) < '0' || c > '9') return length;
			else result = result * 10 + (c - '0');
		return result;
	}
}
