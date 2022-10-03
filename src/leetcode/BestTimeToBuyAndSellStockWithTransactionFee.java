package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {
	/*
	You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
	Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
	Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

	Example 1:
	Input: prices = [1,3,2,8,4,9], fee = 2
	Output: 8
	Explanation: The maximum profit can be achieved by:
	- Buying at prices[0] = 1
	- Selling at prices[3] = 8
	- Buying at prices[4] = 4
	- Selling at prices[5] = 9
	The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

	Example 2:
	Input: prices = [1,3,7,5,10,3], fee = 3
	Output: 6

	Constraints:
	1 <= prices.length <= 5 * 10^4
	1 <= prices[i] < 5 * 10^4
	0 <= fee < 5 * 10^4
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{8, new int[]{1, 3, 2, 8, 4, 9}, 2},
				{6, new int[]{1, 3, 7, 5, 10, 3}, 3}
		});
	}

	public int maxProfit(int[] prices, int fee) {
		int length = prices.length, last = length - 1, buy[] = new int[length], sell[] = new int[length];
		buy[0] = -prices[0];
		for (int i = 1; i < length; i++) {
			buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]); // keep the same as day i-1, or buy from sell status at day i-1
			sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee); // keep the same as day i-1, or sell from buy status at day i-1
		}
		return sell[last];
	}
}
