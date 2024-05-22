package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class MaximumIceCreamBars {
/*
It is a sweltering summer day, and a boy wants to buy some ice cream bars.
At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price of the ith ice cream bar in coins. The boy initially has coins coins to spend, and he wants to buy as many ice cream bars as possible.
Note: The boy can buy the ice cream bars in any order.
Return the maximum number of ice cream bars the boy can buy with coins coins.
You must solve the problem by counting sort.

Example 1:
Input: costs = [1,3,2,4,1], coins = 7
Output: 4
Explanation: The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.

Example 2:
Input: costs = [10,6,8,7,7,8], coins = 5
Output: 0
Explanation: The boy cannot afford any of the ice cream bars.

Example 3:
Input: costs = [1,6,3,1,2,5], coins = 20
Output: 6
Explanation: The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.

Constraints:
    costs.length == n
    1 <= n <= 10^5
    1 <= costs[i] <= 10^5
    1 <= coins <= 10^8
*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{4, new int[]{1, 3, 2, 4, 1}, 7},
				{0, new int[]{10, 6, 8, 7, 7, 8}, 5},
				{6, new int[]{1, 6, 3, 1, 2, 5}, 20},
		});
	}

	public int maxIceCream(int[] costs, int coins) {
		int result = 0, maxCost = 0;
		for (int cost : costs) if (cost > maxCost) maxCost = cost;
		int[] costFrequency = new int[maxCost + 1];
		for (int cost : costs) costFrequency[cost]++;
		for (int cost = 1, count; cost <= maxCost && coins >= cost; cost++) {
			if (costFrequency[cost] == 0) continue;
			result += count = Integer.min(costFrequency[cost], coins / cost);
			coins -= count * cost;
		}
		return result;
	}
}
