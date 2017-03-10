package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidPerfectSquare {

  /*
  Given a positive integer num, write a function which returns True if num is a perfect square else False.
  
  Note: Do not use any built-in library function such as sqrt.
  
  Example 1:
  
  Input: 16
  Returns: True
  
  Example 2:
  
  Input: 14
  Returns: False  
  */

  @Test
  public void test() {
    assertTrue(isPerfectSquare(16));
    assertFalse(isPerfectSquare(14));
  }

  public boolean isPerfectSquare(int num) {
    long right = 1;
    for (; right * right < num; right <<= 1);
    return binarySearch(num, right >> 1, right) != -1;
  }

  private long binarySearch(long num, long left, long right) {
    if (left > right) return -1;
    long mid = (left + right) >> 1, square = mid * mid;
    if (square == num) return mid;
    else if (square > num) return binarySearch(num, left, mid - 1);
    return binarySearch(num, mid + 1, right);
  }

}
