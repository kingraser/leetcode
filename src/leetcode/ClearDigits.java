package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ClearDigits {
/*
You are given a string s.
Your task is to remove all digits by doing this operation repeatedly:
    Delete the first digit and the closest non-digit character to its left.
Return the resulting string after removing all digits.

Example 1:
Input: s = "abc"
Output: "abc"
Explanation:
There is no digit in the string.

Example 2:
Input: s = "cb34"
Output: ""
Explanation:
First, we apply the operation on s[2], and s becomes "c4".
Then we apply the operation on s[1], and s becomes "".

Constraints:
    1 <= s.length <= 100
    s consists only of lowercase English letters and digits.
    The input is generated such that it is possible to delete all digits.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"abc", "abc"},
				{"", "cb34"},
		});
	}

	public String clearDigits(String s) {
		int length = s.length(), resultIndex = length, digitCount = 0;
		char result[] = new char[length], c;
		for (int i = length - 1; i >= 0; )
			if (isDigit(c = s.charAt(i--))) digitCount++;
			else if (digitCount > 0) digitCount--;
			else result[--resultIndex] = c;
		return new String(result, resultIndex, length - resultIndex);
	}

	boolean isDigit(char c) {return c >= '0' && c <= '9';}
}
