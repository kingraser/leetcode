package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShortestPalindrome {

  /*
  Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
  Find and return the shortest palindrome you can find by performing this transformation.
  
  For example:
  
  Given "aacecaaa", return "aaacecaaa".    
  Given "abcd", return "dcbabcd".
  */

  /*
  Find the longest palindrome substring starts with s[0] as s(0, i + 1).
  Return s(i + 1).reverse + s
  */

  public String shortestPalindrome(String s) {
    int idx;
    for (idx = s.length() - 1; idx > 0 && isNotPalindrome(s, idx); idx--);
    return new StringBuilder(s.substring(idx + 1)).reverse().append(s).toString();
  }

  private boolean isNotPalindrome(String s, int end) {
    for (int i = 0; i < end;)
      if (s.charAt(i++) != s.charAt(end--)) return true;
    return false;
  }

  @Test
  public void test() {
    assertEquals("aaacecaaa", shortestPalindrome("aacecaaa"));
    assertEquals("dcbabcd", shortestPalindrome("abcd"));
  }

}
