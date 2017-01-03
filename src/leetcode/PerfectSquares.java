/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class PerfectSquares {

  /*
  Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
  
  For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9. 
  */

  /*
  1 Math
  Lagrange's four-square theorem
  An integer can be formed to 4 square sum;  
   
  Thus a integer
  is a square if and only if its prime factors occur even times.
  is two square sum if and only if it has prime factor a where a%4 ==3
  is three square sum if and only if is not 4^a(8b+7) (a,b is integer)
                  
  2DP
  Let dp[y * y] = 1,y * y <= n
  Then dp[x + y * y] = min(dp[x + y * y], dp[x] + 1)
  */

  public int numSquares(int n) {
    for (; (n & 3) == 0; n >>= 2);
    if (n % 8 == 7) return 4;//is 4^a(8b+7) format
    for (int a = 0, b = (int) Math.sqrt(n - a * a); a * a <= n; a++, b = (int) Math.sqrt(n - a * a))
      if (a * a + b * b == n) return a == 0 ? 1 : 2;
    return 3;
  }

  public int numSquaresII(int n) {
    int[] dp = new int[n + 1];
    Arrays.fill(dp, 1, n + 1, 4);//as the theorem above
    for (int i = 0; i <= n; i++)
      for (int j = 1; i + j * j <= n; dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1), j++);
    return dp[n];
  }

  @Test
  public void test() {
    assertEquals(4, numSquares(60));
    assertEquals(2, numSquaresII(10));
  }

}
