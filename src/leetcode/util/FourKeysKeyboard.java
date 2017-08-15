package leetcode.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FourKeysKeyboard {

  /*
  Imagine you have a special keyboard with the following keys:  
    Key 1: (A): Prints one 'A' on screen.
    Key 2: (Ctrl-A): Select the whole screen.  
    Key 3: (Ctrl-C): Copy selection to buffer.  
    Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.  
  Now, you can only press the keyboard for N times (with the above four keys), 
  find out the maximum numbers of 'A' you can print on screen.
  
  Example 1:  
  Input: N = 3
  Output: 3
  Explanation: 
  We can at most get 3 A's on screen by pressing following key sequence:
  A, A, A
  
  Example 2:  
  Input: N = 7
  Output: 9
  Explanation: 
  We can at most get 9 A's on screen by pressing following key sequence:
  A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
  
  Note:  
    1 <= N <= 50
    Answers will be in the range of 32-bit signed integer.  
  */

  @Test
  public void test() {
    assertEquals(9, maxA(7));
    assertEquals(3, maxA(3));
  }

  public int maxA(int n) {
    int[] dp = new int[n + 1];
    for (int i = 0, j; i <= n; i++)
      for (dp[i] = i, j = Math.max(1, i - 6); j <= i - 3; j++)
        dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
    return dp[n];
  }
}
