/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class IsomorphicStrings {

  /*
  For example,
  Given "egg", "add", return true.    
  Given "foo", "bar", return false.    
  Given "paper", "title", return true.
  */

  @Test
  public void test() {
    assertTrue(isIsomorphic("egg", "add"));
    assertTrue(isIsomorphic("paper", "title"));
    assertFalse(isIsomorphic("foo", "bar"));
  }

  private int i;

  public boolean isIsomorphic(String s, String t) {
    if (s.length() != t.length()) return false;
    Map<Character, Character> map = new HashMap<>();
    for (i = 0; i < s.length(); i++)
      if (map.computeIfAbsent(s.charAt(i), k -> t.charAt(i)) != t.charAt(i)) return false;
    return true;
  }
}
