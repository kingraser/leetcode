package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class MaximumTastinessOfCandyBasket {
	/*
	You are given an array of positive integers price where price[i] denotes the price of the ith candy and a positive integer k.
	The store sells baskets of k distinct candies. The tastiness of a candy basket is the smallest absolute difference of the prices of any two candies in the basket.
	Return the maximum tastiness of a candy basket.

	Example 1:
	Input: price = [13,5,1,8,21,2], k = 3
	Output: 8
	Explanation: Choose the candies with the prices [13,5,21].
	The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8.
	It can be proven that 8 is the maximum tastiness that can be achieved.

	Example 2:
	Input: price = [1,3,1], k = 2
	Output: 2
	Explanation: Choose the candies with the prices [1,3].
	The tastiness of the candy basket is: min(|1 - 3|) = min(2) = 2.
	It can be proven that 2 is the maximum tastiness that can be achieved.

	Example 3:
	Input: price = [7,7,7,7], k = 2
	Output: 0
	Explanation: Choosing any two distinct candies from the candies we have will result in a tastiness of 0.

	Constraints:
	2 <= k <= price.length <= 10^5
	1 <= price[i] <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{2, new int[]{1, 3, 1}, 2},
				{0, new int[]{7, 7, 7, 7}, 2},
				{8, new int[]{13, 5, 1, 8, 21, 2}, 3},
		});
	}

	public int maximumTastiness(int[] price, int k) {
		Arrays.sort(price);
		int low = 0, high = 1000_000_000, mid;
		while (low < high)
			if (check((mid = (low + high) >> 1), price, k)) low = mid + 1;
			else high = mid;
		return low - 1;
	}

	boolean check(int x, int[] price, int k) {
		int last = price[0], count = 1;
		for (int i = 1; count < k && i < price.length; i++) {
			if (price[i] - last < x) continue;
			last = price[i];
			count++;
		}
		return count == k;
	}
}
