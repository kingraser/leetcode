package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class TimeNeededToRearrangeABinaryString {
/*
You are given a binary string s. In one second, all occurrences of "01" are simultaneously replaced with "10". This process repeats until no occurrences of "01" exist.
Return the number of seconds needed to complete this process.

Example 1:
Input: s = "0110101"
Output: 4
Explanation:
After one second, s becomes "1011010".
After another second, s becomes "1101100".
After the third second, s becomes "1110100".
After the fourth second, s becomes "1111000".
No occurrence of "01" exists any longer, and the process needed 4 seconds to complete,
so we return 4.

Example 2:
Input: s = "11100"
Output: 0
Explanation:
No occurrence of "01" exists in s, and the processes needed 0 seconds to complete,
so we return 0.

Constraints:
1 <= s.length <= 1000
s[i] is either '0' or '1'.
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, "001011"},
				{4, "0110101"},
				{0, "11100"}
		});
	}

	public int secondsToRemoveOccurrences(String s) {
		int waitingTime = 0, zeroCount = s.charAt(0) == '0' ? 1 : 0, length = s.length(), lastOne = length - 1;
		while (lastOne >= 0 && s.charAt(lastOne) == '0') lastOne--;
		if (lastOne < 0) return 0;
		for (int i = 1; i <= lastOne; i++)
			if (s.charAt(i) == '1') {
				if (s.charAt(i - 1) == '1' && zeroCount > 0) waitingTime++;
			} else {
				zeroCount++;
				if (s.charAt(i - 1) == '0' && waitingTime > 0) waitingTime--;
			}
		return zeroCount + waitingTime;
	}
}
