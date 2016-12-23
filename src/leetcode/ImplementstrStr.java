/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class ImplementstrStr {

  public int strStr(String l, String s) {
    if (s != null && l != null && l.length() > 0) A: for (int i = 0; i <= l.length() - s.length(); i++) {
      for (int j = 0; j < s.length(); j++)
        if (l.charAt(i + j) != s.charAt(j)) continue A;
      return i;
    }
    return -1;
  }

  @Test
  public void test() {
    assertEquals(-1, strStr("mississippi", "sippia"));
    assertEquals(6, strStr("mississippia", "sippia"));
  }
}
