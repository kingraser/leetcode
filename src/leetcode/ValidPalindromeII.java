package leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidPalindromeII {

  /*
  Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
  
  Example 1:  
  Input: "aba"
  Output: True
  
  Example 2:  
  Input: "abca"
  Output: True
  Explanation: You could delete the character 'c'.
  
  Note:  
    The string will only contain lowercase characters a-z. The maximum length of the string is 50000.  
  */

  @Test
  public void test() {
    assertTrue(validPalindrome("aba"));
    assertTrue(validPalindrome("abca"));

  }

  public boolean validPalindrome(String s) {
    for (int left = 0, right = s.length() - 1; left < right; left++, right--)
      if (s.charAt(left) != s.charAt(right)) return isPalindrome(s, left++, right - 1) || isPalindrome(s, left, right);
    return true;
  }

  private boolean isPalindrome(String s, int left, int right) {
    for (; left < right && s.charAt(left) == s.charAt(right); left++, right--);
    return left >= right;
  }
}
