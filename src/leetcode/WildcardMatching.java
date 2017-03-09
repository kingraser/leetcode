package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WildcardMatching {

  /*
  Implement wildcard pattern matching with support for '?' and '*'.
  
  '?' Matches any single character.
  '*' Matches any sequence of characters (including the empty sequence).
  
  The matching should cover the entire input string (not partial).
  
  The function prototype should be:
  bool isMatch(const char *s, const char *p)
  
  Some examples:
  isMatch("aa","a") → false
  isMatch("aa","aa") → true
  isMatch("aaa","aa") → false
  isMatch("aa", "*") → true
  isMatch("aa", "a*") → true
  isMatch("ab", "?*") → true
  isMatch("aab", "c*a*b") → false    
  */

  @Test
  public void test() {
    assertFalse(isMatch("aa", "a"));
    assertTrue(isMatch("aa", "aa"));
    assertFalse(isMatch("aaa", "aa"));
    assertTrue(isMatch("aa", "*"));
    assertTrue(isMatch("aa", "a*"));
    assertTrue(isMatch("ab", "?*"));
    assertFalse(isMatch("aab", "c*a*b"));
  }

  public boolean isMatch(String s, String pattern) {
    int sIdx = 0, pIdx = 0, matchIdx = 0, starIdx = -1;
    while (sIdx < s.length())
      if (pIdx < pattern.length()
          && (pattern.charAt(pIdx) == '?' || (s.charAt(sIdx) != '*' && s.charAt(sIdx) == pattern.charAt(pIdx)))) { // advancing both idx
        sIdx++;
        pIdx++;
      } else if (pIdx < pattern.length() && pattern.charAt(pIdx) == '*') { // * found, only advancing pattern idx
        starIdx = pIdx++;
        matchIdx = sIdx;
      } else if (starIdx != -1) { // last pattern idx was *, advancing string idx
        pIdx = starIdx + 1;
        sIdx = ++matchIdx;
      } else return false; // current pattern idx is not star, last pattern idx was not *, so characters do not match
    for (; pIdx < pattern.length() && pattern.charAt(pIdx) == '*'; pIdx++); // check for remaining characters in pattern
    return pIdx == pattern.length();
  }
}
