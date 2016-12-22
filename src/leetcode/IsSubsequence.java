/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月5日;
//-------------------------------------------------------
public class IsSubsequence {
  /*
  Given a string s and a string t, check if s is subsequence of t.
  You may assume that there is only lower case English letters in both s and t. 
  t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
  A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
  
  Example 1:
  s = "abc", t = "ahbgdc"
  Return true.
  
  Example 2:
  s = "axc", t = "ahbgdc"
  Return false. 
  */

  @Test
  public void test() {
    assertTrue(isSubsequence("abc", "ahbgdc"));
    assertFalse(isSubsequence("axc", "ahbgdc"));
  }

  public boolean isSubsequence(String s, String t) {
    for (int sIdx = 0, tIdx = -1, len = s.length(); sIdx < len;)
      if ((tIdx = t.indexOf(s.charAt(sIdx++), ++tIdx)) == -1) return false;
    return true;
  }
}
