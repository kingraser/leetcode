package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RepeatedSubstringPattern {
  /*
  Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
  
  Example 1:
  
  Input: "abab"
  
  Output: True
  
  Explanation: It's the substring "ab" twice.
  
  Example 2:
  
  Input: "aba"
  
  Output: False
  
  Example 3:
  
  Input: "abcabcabcabc"
  
  Output: True
  
  Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
  */

  @Test
  public void test() {
    assertTrue(repeatedSubstringPattern("abab"));
    assertTrue(repeatedSubstringPattern("abcabcabcabc"));
    assertFalse(repeatedSubstringPattern("aba"));
  }

  public boolean repeatedSubstringPattern(String str) {
    char[] s = str.toCharArray();
    for (int i = 1, len = s.length >> 1; i <= len; i++)
      if (isRepeated(s, i)) return true;
    return false;
  }

  private boolean isRepeated(char[] s, int len) {
    if (0 != s.length % len) return false;
    for (int next = len; next < s.length;)
      for (int i = 0; i < len;)
        if (s[i++] != s[next++]) return false;
    return true;
  }
}
