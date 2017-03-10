package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LargestPalindromeProduct {

  /*
  Find the largest palindrome made from the product of two n-digit numbers.  
  Since the result could be very large, you should return the largest palindrome mod 1337.  
  
  Example:  
  Input: 2  
  Output: 987  
  Explanation: 99 x 91 = 9009, 9009 % 1337 = 987
  
  Note: The range of n is [1,8].
  */
  @Test
  public void test() {
    assertEquals(475, largestPalindrome(8));
  }

  public int largestPalindrome(int n) {
    if (n == 1) return 9;
    for (long max = (long) Math.pow(10, n) - 1, min = max / 10, half = max * max / (long) Math.pow(10, n);; half--)
      for (long i = max, palindrom = Long.parseLong(half + new StringBuilder(half + "").reverse().toString()); i > min
          && i * max >= palindrom && palindrom > min * i; i--)
        if (palindrom % i == 0) return (int) (palindrom % 1337);
  }
}
