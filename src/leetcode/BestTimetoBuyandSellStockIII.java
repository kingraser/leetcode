package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class BestTimetoBuyandSellStockIII {

  /* 
  Say you have an array for which the i-th element is the price of a given stock on day i.
  Design an algorithm to find the maximum profit. You may complete at most two transactions.
  Note: You may not engage in multiple transactions at the same time 
  (ie, you must sell the stock before you buy again).
  
  let f(i) represents max profit in [0, i] (0 ≤ i ≤ n − 1)
      s(i) represents max profit in [i, n − 1] (0 ≤ i ≤ n − 1)
  The answer is max {f(i) + g(i)} (0 ≤ i ≤ n − 1)
  */

  @Test
  public void test() {
    assertEquals(6, maxProfit(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
  }

  public int maxProfit(List<Integer> prices) {
    if (prices.size() < 2) return 0;
    int[] first = new int[prices.size()], second = new int[prices.size()];
    for (int idx = 1, min = prices.get(0); idx < prices.size(); min = Math.min(min, prices.get(idx++)))
      first[idx] = Math.max(first[idx - 1], prices.get(idx) - min);
    for (int idx = prices.size() - 2, max = prices.get(idx + 1); idx >= 0; max = Math.max(max, prices.get(idx--)))
      second[idx] = Math.max(second[idx + 1], max - prices.get(idx));
    return IntStream.range(0, prices.size()).map(i -> first[i] + second[i]).max().getAsInt();
  }

}
