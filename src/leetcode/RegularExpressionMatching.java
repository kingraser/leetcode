package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegularExpressionMatching {

  /*
  Implement regular expression matching with support for '.' and '*'.
  
  '.' Matches any single character.
  '*' Matches zero or more of the preceding element.
  
  The matching should cover the entire input string (not partial).
  
  The function prototype should be:
  bool isMatch(const char *s, const char *p)
  
  Some examples:
  isMatch("aa","a") → false
  isMatch("aa","aa") → true
  isMatch("aaa","aa") → false
  isMatch("aa", "a*") → true
  isMatch("aa", ".*") → true
  isMatch("ab", ".*") → true
  isMatch("aab", "c*a*b") → true
  */

  @Test
  public void test() {
    assertFalse(isMatch("aa", "a"));
    assertTrue(isMatch("aa", "aa"));
    assertFalse(isMatch("aaa", "aa"));
    assertTrue(isMatch("aa", "a*"));
    assertTrue(isMatch("aa", ".*"));
    assertTrue(isMatch("ab", ".*"));
    assertTrue(isMatch("aab", "c*a*b"));
    assertTrue(isMatch("aab", "a*b"));
  }

  public boolean isMatchZero(String s, String p) {
    return s.matches(p);
  }

  // recursive
  public boolean isMatchII(String s, String p) {
    if (p.isEmpty()) return s.isEmpty();
    if (p.length() > 1 && p.charAt(1) == '*') return isMatch(s, p.substring(2))
        || (p.charAt(0) == '.' && !s.isEmpty() || s.charAt(0) == p.charAt(0)) && isMatch(s.substring(1), p);
    if (p.charAt(0) == '.') return !s.isEmpty() && isMatch(s.substring(1), p.substring(1));
    return s.charAt(0) == p.charAt(0) && isMatch(s.substring(1), p.substring(1));
  }

  // dp[i][j] represents whether s.subString(0, i) matches p.subString(0, j)
  public boolean isMatch(String s, String p) {
    boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
    dp[0][0] = true;
    for (int i = 0; i <= s.length(); i++)
      for (int j = 1; j <= p.length(); j++)
        if (p.charAt(j - 1) != '*') dp[i][j] = i > 0 && matches(s.charAt(i - 1), p.charAt(j - 1)) && dp[i - 1][j - 1];
        else
          dp[i][j] = j >= 2 && (dp[i][j - 2] || (i > 0 && matches(s.charAt(i - 1), p.charAt(j - 2)) && dp[i - 1][j]));
    return dp[s.length()][p.length()];
  }

  private boolean matches(char c, char p) {
    return p == '.' || c == p;
  }

}
