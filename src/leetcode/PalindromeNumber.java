package leetcode;

import static leetcode.ReverseInteger.reverse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PalindromeNumber {

  /*
  Determine whether an integer is a palindrome. Do this without extra space.
  */

  @Test
  public void test() {
    assertTrue(isPalindrome(123321));
  }

  public boolean isPalindrome(int x) {
    return x < 0 ? false : reverse(x) == x;
  }

}
