package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Wit
 */
public class MinimumLinesToRepresentALineChart {
	/*
	You are given a 2D integer array stockPrices where stockPrices[i] = [day_i, price_i] indicates the price of the stock on day dayi is pricei. A line chart is created from the array by plotting the points on an XY plane with the X-axis representing the day and the Y-axis representing the price and connecting adjacent points. One such example is shown below:
	Return the minimum number of lines needed to represent the line chart.

	Example 1:
	Input: stockPrices = [[1,7],[2,6],[3,5],[4,4],[5,4],[6,3],[7,2],[8,1]]
	Output: 3
	Explanation:
	The diagram above represents the input, with the X-axis representing the day and Y-axis representing the price.
	The following 3 lines can be drawn to represent the line chart:
	- Line 1 (in red) from (1,7) to (4,4) passing through (1,7), (2,6), (3,5), and (4,4).
	- Line 2 (in blue) from (4,4) to (5,4).
	- Line 3 (in green) from (5,4) to (8,1) passing through (5,4), (6,3), (7,2), and (8,1).
	It can be shown that it is not possible to represent the line chart using less than 3 lines.

	Example 2:
	Input: stockPrices = [[3,4],[1,2],[7,8],[2,3]]
	Output: 1
	Explanation:
	As shown in the diagram above, the line chart can be represented with a single line.

	Constraints:
	1 <= stockPrices.length <= 10^5
	stockPrices[i].length == 2
	1 <= day_i, price_i <= 10^9
	All day_i are distinct.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, TestUtil.getArray("[[1,7],[2,6],[3,5],[4,4],[5,4],[6,3],[7,2],[8,1]]")},
				{1, TestUtil.getArray("[[3,4],[1,2],[7,8],[2,3]]")},
		});
	}

	public int minimumLines(int[][] stockPrices) {
		if (stockPrices.length == 1) return 0;
		Arrays.sort(stockPrices, Comparator.comparingInt(price -> price[0]));
		int result = 1;
		for (int i = 2; i < stockPrices.length; i++) {
			long x1 = stockPrices[i][0], y1 = stockPrices[i][1], x2 = stockPrices[i - 1][0], y2 = stockPrices[i - 1][1], x3 = stockPrices[i - 2][0], y3 = stockPrices[i - 2][1];
			if ((y3 - y2) * (x2 - x1) != (y2 - y1) * (x3 - x2)) result++;
		}
		return result;
	}
}
