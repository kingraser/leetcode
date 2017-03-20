package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class BestTimetoBuyandSellStockII {

  /*
  Say you have an array for which the i-th element is the price of a given stock on day i.
  Design an algorithm to find the maximum profit. You may complete as many transactions as you like 
  (ie, buy one and sell one share of the stock multiple times). 
  However, you may not engage in multiple transactions at the same time 
  (ie, you must sell the stock before you buy again).
  */

  @Test
  public void test() {
    assertEquals(6, maxProfit(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
  }

  public static int maxProfit(List<Integer> prices) {
    return IntStream.range(1, prices.size()).map(i -> prices.get(i) - prices.get(i - 1)).filter(i -> i > 0).sum();
  }

}
