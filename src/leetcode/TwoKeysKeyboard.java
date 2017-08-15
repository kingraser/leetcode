package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TwoKeysKeyboard {

  /*
  Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:  
    Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
    Paste: You can paste the characters which are copied last time.  
  Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. 
  Output the minimum number of steps to get n 'A'.
  
  Example 1:  
  Input: 3
  Output: 3
  Explanation:
  Intitally, we have one character 'A'.
  In step 1, we use Copy All operation.
  In step 2, we use Paste operation to get 'AA'.
  In step 3, we use Paste operation to get 'AAA'.
  
  Note:  
    The n will be in the range [1, 1000].  
  */

  @Test
  public void test() {
    assertEquals(3, minSteps(3));
  }

  public int minSteps(int n) {
    int result = 0;
    for (int divisor = 2; divisor <= n; divisor++)
      for (; n % divisor == 0; result += divisor, n /= divisor);
    return result;
  }

}
