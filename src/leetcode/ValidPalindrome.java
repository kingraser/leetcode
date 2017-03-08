package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidPalindrome {

  /*
  Given a string, determine if it is a palindrome, 
  considering only alphanumeric characters and ignoring cases.
  
  For example,
  "A man, a plan, a canal: Panama" is a palindrome.
  "race a car" is not a palindrome. 
   */

  public boolean isPalindrome(String s) {
    for (int left = 0, right = (s = s.toLowerCase()).length() - 1; left < right;)
      if (!Character.isLetterOrDigit(s.charAt(left))) left++;
      else if (!Character.isLetterOrDigit(s.charAt(right))) right--;
      else if (s.charAt(left++) != s.charAt(right--)) return false;
    return true;
  }

  @Test
  public void test() {
    assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
    assertFalse(isPalindrome("race a car"));
  }

}
