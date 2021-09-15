package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.stream.IntStream;

public class BestTimetoBuyandSellStockIV {

  /*    
  Say you have an array for which the ith element is the price of a given stock on day i.    
  Design an algorithm to find the maximum profit. You may complete at most k transactions.
  
  Note:
  You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
  */

  /*
  Let t(i, j) is the max profit using i transactions by time j (0 <= i <= K, 0 <= j <= T)
  t(i, j) = max(t(i, j - 1), max(t(i - 1, k) + prices[j] - prices[k])) i <= k < j  
          = max(t(i, j - 1), prices[j] + max(t(i - 1, k) - prices[k]))
  */

    @Test
    public void test() {
        TestUtil.testEquals(IntStream.range(1, 7)
                .mapToObj(i -> new Object[]{6, i, IntStream.range(1, 8).toArray()})
                .toArray(Object[][]::new));
    }

    public int maxProfit(int k, int[] prices) {
        int[][] t = new int[k + 1][prices.length];
        for (int i = 1; i <= k; i++)
            for (int j = 1, max = -prices[0]; j < prices.length; max = Math.max(max, t[i - 1][j] - prices[j++]))
                t[i][j] = Math.max(t[i][j - 1], max + prices[j]);
        return t[k][prices.length - 1];
    }

}
