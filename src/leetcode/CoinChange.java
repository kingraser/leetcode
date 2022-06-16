package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class CoinChange {

  /*
  You are given coins of different denominations and a total amount of money amount. 
  Write a function to compute the fewest number of coins that you need to make up that amount. 
  If that amount of money cannot be made up by any combination of the coins, return -1.
  
  Example 1:
  coins = [1, 2, 5], amount = 11
  return 3 (11 = 5 + 5 + 1)
  
  Example 2:
  coins = [2], amount = 3
  return -1.
  
  Note:
  You may assume that you have an infinite number of each kind of coin. 
  */

  @Test
  public void test() {
    assertEquals(3, coinChange(new int[] { 1, 2, 5 }, 11));
    assertEquals(-1, coinChange(new int[] { 2 }, 3));
  }

  public int coinChange(int[] coins, int amount) {
    long[] result = new long[amount + 1];
    Arrays.fill(result, 1, result.length, Integer.MAX_VALUE);
    for (amount = 1; amount < result.length; amount++)
      for (int i : coins) if (amount - i >= 0) result[amount] = Math.min(result[amount], result[amount - i] + 1);
    return Integer.MAX_VALUE == result[result.length - 1] ? -1 : (int) result[result.length - 1];
  }

}
