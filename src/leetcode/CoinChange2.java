package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class CoinChange2 {

  /*
  You are given coins of different denominations and a total amount of money. 
  Write a function to compute the number of combinations that make up that amount. 
  You may assume that you have infinite number of each kind of coin.
  
  Note: You can assume that  
    0 <= amount <= 5000
    1 <= coin <= 5000
    the number of coins is less than 500
    the answer is guaranteed to fit into signed 32-bit integer 
  
  Example 1:  
  Input: amount = 5, coins = [1, 2, 5]
  Output: 4
  Explanation: there are four ways to make up the amount:
  5=5
  5=2+2+1
  5=2+1+1+1
  5=1+1+1+1+1
  
  Example 2:  
  Input: amount = 3, coins = [2]
  Output: 0
  Explanation: the amount of 3 cannot be made up just with coins of 2.
  
  Example 3:  
  Input: amount = 10, coins = [10] 
  Output: 1  
  */

  @Test
  public void test() {
    assertEquals(4, change(5, new int[] { 1, 2, 5 }));
    assertEquals(0, change(3, new int[] { 2 }));
    assertEquals(1, change(10, new int[] { 10 }));
  }

  public int change(int amount, int[] coins) {
    int[] count = new int[amount + 1];
    count[0] = 1;
    Arrays.sort(coins);
    for (int coin : coins)
      for (int start = coin; start < count.length; start++)
        count[start] += count[start - coin];
    return count[amount];
  }
}
