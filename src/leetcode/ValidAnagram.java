/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class ValidAnagram {

  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    int[] map = new int[128];
    s.chars().forEach(c -> map[c]++);
    return t.chars().noneMatch(c -> map[c]-- == 0);
  }

  @Test
  public void test() {
    assertTrue(isAnagram("anagram", "nagaram"));
    assertFalse(isAnagram("rat", "car"));
  }

}
