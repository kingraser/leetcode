package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestPalindromicSubstring {

  /*
  Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
  
  Example:  
  Input: "babad"  
  Output: "bab"  
  Note: "aba" is also a valid answer.
  
  Example:  
  Input: "cbbd"  
  Output: "bb"  
  */

  @Test
  public void test() {
    assertEquals("bab", longestPalindrome("babad"));
    assertEquals("bb", longestPalindrome("cbbd"));

    assertEquals("", longestPalindrome(""));
    assertEquals("b", longestPalindrome("b"));
  }

  private int start, len;

  public String longestPalindrome(String s) {
    start = 0;
    len = Math.min(1, s.length());
    for (int i = 1; i < s.length(); i++) {
      extendPalindrome(s, i, i);
      extendPalindrome(s, i - 1, i);
    }
    return s.substring(start, start + len);
  }

  private void extendPalindrome(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }
    if (len < right - left - 1) {
      start = left + 1;
      len = right - left - 1;
    }
  }
}
