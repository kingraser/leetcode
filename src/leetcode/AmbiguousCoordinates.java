package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class AmbiguousCoordinates {
	/*
	We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)". Then, we removed all commas, decimal points, and spaces and ended up with the string s.
	For example, "(1, 3)" becomes s = "(13)" and "(2, 0.5)" becomes s = "(205)".
	Return a list of strings representing all possibilities for what our original coordinates could have been.
	Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with fewer digits. Also, a decimal point within a number never occurs without at least one digit occurring before it, so we never started with numbers like ".1".
	The final answer list can be returned in any order. All coordinates in the final answer have exactly one space between them (occurring after the comma.)

	Example 1:
	Input: s = "(123)"
	Output: ["(1, 2.3)","(1, 23)","(1.2, 3)","(12, 3)"]

	Example 2:
	Input: s = "(0123)"
	Output: ["(0, 1.23)","(0, 12.3)","(0, 123)","(0.1, 2.3)","(0.1, 23)","(0.12, 3)"]
	Explanation: 0.0, 00, 0001 or 00.01 are not allowed.

	Example 3:
	Input: s = "(00011)"
	Output: ["(0, 0.011)","(0.001, 1)"]

	Constraints:
	4 <= s.length <= 12
	s[0] == '(' and s[s.length - 1] == ')'.
	The rest of s are digits.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{List.of("(1, 23)", "(1, 2.3)", "(12, 3)", "(1.2, 3)"), "(123)"},
				{List.of("(0, 123)", "(0, 1.23)", "(0, 12.3)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"), "(0123)"},
				{List.of("(0, 0.011)", "(0.001, 1)"), "(00011)"},
		});
	}

	public List<String> ambiguousCoordinates(String s) {
		List<String> result = new ArrayList<>();
		for (int i = 1, length = s.length(); i < length - 2; i++) {
			List<String> first = getNums(s.substring(1, i + 1));
			if (first.isEmpty()) continue;
			List<String> second = getNums(s.substring(i + 1, length - 1));
			if (second.isEmpty()) continue;
			for (String a : first) for (String b : second) result.add("(" + a + ", " + b + ")");
		}
		return result;
	}

	List<String> getNums(String s) {
		List<String> result = new ArrayList<>();
		if (s.length() > 1 && s.charAt(0) == '0') {
			if (s.charAt(s.length() - 1) != '0') result.add("0." + s.substring(1));
			return result;
		}
		result.add(s);
		if (s.length() == 1 || s.charAt(s.length() - 1) == '0') return result;
		for (int i = 1; i < s.length(); i++) result.add(s.substring(0, i) + '.' + s.substring(i));
		return result;
	}
}
