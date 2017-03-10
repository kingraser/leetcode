package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NthDigit {

  /*  
  Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
  Note: n is positive and will fit within the range of a 32-bit signed integer (n < 231).
  
  Example 1:  
  Input: 3
  Output: 3
  
  Example 2:  
  Input: 11
  Output: 0
  
  Explanation:
  The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.  
  */

  @Test
  public void test() {
    assertEquals(3, findNthDigit(3));
    assertEquals(0, findNthDigit(11));
  }

  static int[] powOfTen = new int[] { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000 };

  public int findNthDigit(int n) {
    int len = 1, count = 9, start = 1;
    for (; n > (long) len * count; n -= (count * len++), count *= 10, start *= 10);
    return getNDigit(start + --n / len, len - n % len);
  }

  /**
   * @param n
   * @param k
   * @return the k digit from the right of number n
   */
  public int getNDigit(int n, int k) {
    return (n % powOfTen[k]) / powOfTen[--k];
  }

}
