package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class PlatesBetweenCandles {
	/*
	There is a long table with a line of plates and candles arranged on top of it. You are given a 0-indexed string s consisting of characters '*' and '|' only, where a '*' represents a plate and a '|' represents a candle.
	You are also given a 0-indexed 2D integer array queries where queries[i] = [left_i, right_i] denotes the substring s[left_i...right_i] (inclusive). For each query, you need to find the number of plates between candles that are in the substring. A plate is considered between candles if there is at least one candle to its left and at least one candle to its right in the substring.
	For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|". The number of plates between candles in this substring is 2, as each of the two plates has at least one candle in the substring to its left and right.
	Return an integer array answer where answer[i] is the answer to the ith query.

	Example 1:
	Input: s = "**|**|***|", queries = [[2,5],[5,9]]
	Output: [2,3]
	Explanation:
	- queries[0] has two plates between candles.
	- queries[1] has three plates between candles.

	Example 2:
	Input: s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
	Output: [9,0,0,0,0]
	Explanation:
	- queries[0] has nine plates between candles.
	- The other queries have zero plates between candles.

	Constraints:
	3 <= s.length <= 10^5
	s consists of '*' and '|' characters.
	1 <= queries.length <= 10^5
	queries[i].length == 2
	0 <= left_i <= right_i < s.length
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new int[]{2, 3}, "**|**|***|", TestUtil.getArray("[[2,5],[5,9]]")},
				{new int[]{9, 0, 0, 0, 0}, "***|**|*****|**||**|*", TestUtil.getArray("[[1,17],[4,5],[14,17],[5,11],[15,16]]")},
		});
	}

	public int[] platesBetweenCandles(String s, int[][] queries) {
		int length = s.length(), nearestRightCandle[] = new int[length], nearestLeftCandle[] = new int[length], candleCount[] = new int[length], ans[] = new int[queries.length], ansIdx = 0, leftCandle, rightCandle;
		char[] chars = s.toCharArray();
		for (int i = 0, candle = -1; i < length; nearestLeftCandle[i++] = candle) if (chars[i] == '|') candle = i;
		for (int i = length - 1, candle = -1; i >= 0; nearestRightCandle[i--] = candle) if (chars[i] == '|') candle = i;
		for (int i = 0, count = 0; i < length; candleCount[i++] = count) if (s.charAt(i) == '|') count++;
		for (int[] query : queries)
			ans[ansIdx++] = (leftCandle = nearestRightCandle[query[0]]) != -1 && (rightCandle = nearestLeftCandle[query[1]]) != -1 && rightCandle > leftCandle ? rightCandle - leftCandle - (candleCount[rightCandle] - candleCount[leftCandle]) : 0;
		return ans;
	}
}
