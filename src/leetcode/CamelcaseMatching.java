package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class CamelcaseMatching {
	/*
	Given an array of strings queries and a string pattern, return a boolean array answer where answer[i] is true if queries[i] matches pattern, and false otherwise.
	A query word queries[i] matches pattern if you can insert lowercase English letters pattern so that it equals the query. You may insert each character at any position and you may not insert any characters.

	Example 1:
	Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
	Output: [true,false,true,true,false]
	Explanation: "FooBar" can be generated like this "F" + "oo" + "B" + "ar".
	"FootBall" can be generated like this "F" + "oot" + "B" + "all".
	"FrameBuffer" can be generated like this "F" + "rame" + "B" + "uffer".

	Example 2:
	Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
	Output: [true,false,true,false,false]
	Explanation: "FooBar" can be generated like this "Fo" + "o" + "Ba" + "r".
	"FootBall" can be generated like this "Fo" + "ot" + "Ba" + "ll".

	Example 3:
	Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
	Output: [false,true,false,false,false]
	Explanation: "FooBarTest" can be generated like this "Fo" + "o" + "Ba" + "r" + "T" + "est".

	Constraints:
	1 <= pattern.length, queries.length <= 100
	1 <= queries[i].length <= 100
	queries[i] and pattern consist of English letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{List.of(true, false, true, true, false), new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"}, "FB"},
				{List.of(true, false, true, false, false), new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"}, "FoBa"},
				{List.of(false, true, false, false, false), new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"}, "FoBaT"},
		});
	}

	public List<Boolean> camelMatch(String[] queries, String pattern) {
		List<Boolean> result = new ArrayList<>(queries.length);
		char[] chars = pattern.toCharArray();
		for (String query : queries) result.add(camelMatch(query, chars));
		return result;
	}

	private Boolean camelMatch(String s, char[] pattern) {
		int j = 0;
		for (int i = 0, length = s.length(), c; i < length; i++)
			if (j == pattern.length) {
				if ((c = s.charAt(i)) >= 'A' && c <= 'Z') return false;
			} else if ((c = s.charAt(i)) == pattern[j]) j++;
			else if (c >= 'A' && c <= 'Z') return false;
		return j == pattern.length;
	}
}
