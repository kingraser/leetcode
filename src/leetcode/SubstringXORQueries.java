package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class SubstringXORQueries {

	/*
	You are given a binary string s, and a 2D integer array queries where queries[i] = [firsti, secondi].
	For the ith query, find the shortest substring of s whose decimal value, val, yields secondi when bitwise XORed with firsti. In other words, val ^ firsti == secondi.
	The answer to the ith query is the endpoints (0-indexed) of the substring [lefti, righti] or [-1, -1] if no such substring exists. If there are multiple answers, choose the one with the minimum lefti.
	Return an array ans where ans[i] = [lefti, righti] is the answer to the ith query.
	A substring is a contiguous non-empty sequence of characters within a string.

	Example 1:
	Input: s = "101101", queries = [[0,5],[1,2]]
	Output: [[0,2],[2,3]]
	Explanation: For the first query the substring in range [0,2] is "101" which has a decimal value of 5, and 5 ^ 0 = 5, hence the answer to the first query is [0,2]. In the second query, the substring in range [2,3] is "11", and has a decimal value of 3, and 3 ^ 1 = 2. So, [2,3] is returned for the second query.

	Example 2:
	Input: s = "0101", queries = [[12,8]]
	Output: [[-1,-1]]
	Explanation: In this example there is no substring that answers the query, hence [-1,-1] is returned.

	Example 3:
	Input: s = "1", queries = [[4,5]]
	Output: [[0,0]]
	Explanation: For this example, the substring in range [0,0] has a decimal value of 1, and 1 ^ 4 = 5. So, the answer is [0,0].

	Constraints:
	1 <= s.length <= 10^4
	s[i] is either '0' or '1'.
	1 <= queries.length <= 10^5
	0 <= firsti, secondi <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.getArray("[[0,2],[2,3]]"), "101101", TestUtil.getArray("[[0,5],[1,2]]")},
				{TestUtil.getArray("[[-1,-1]]"), "0101", TestUtil.getArray("[[12,8]]")},
				{TestUtil.getArray("[[0,0]]"), "1", TestUtil.getArray("[[4,5]]")},
		});
	}

	public int[][] substringXorQueries(String s, int[][] queries) {
		int[][] result = new int[queries.length][2];
		Map<Integer, Integer> map = new HashMap<>(s.length() << 6);
		char[] chars = s.toCharArray();
		for (int i = 0, flag; i < chars.length; i++)
			if (chars[i] == '1') {
				map.putIfAbsent(1, (flag = (i << 14)) | i);
				for (int num = 1, j = i + 1, limit = Math.min(chars.length, i + 30); j < limit; j++)
					map.putIfAbsent(num = ((num << 1) | (chars[j] - '0')), flag | j);
			} else map.putIfAbsent(0, (i << 14) | i);
		Integer value;
		for (int i = 0, mask = (1 << 14) - 1; i < queries.length; i++)
			if ((value = map.get(queries[i][0] ^ queries[i][1])) == null) {
				result[i][0] = -1;
				result[i][1] = -1;
			} else {
				result[i][1] = value & mask;
				result[i][0] = value >> 14;
			}
		return result;
	}
}
