/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月9日<p>
//-------------------------------------------------------
public class LongestCommonPrefix {

  @Test
  public void test() {
    assertEquals("ab", longestCommonPrefix(new String[] { "abc", "abcd", "ab", "abde" }));
  }

  public String longestCommonPrefix(String[] strs) {
    if (null == strs || strs.length == 0) return "";
    StringBuilder sb = new StringBuilder();
    A: for (int i = 0; i < strs[0].length(); sb.append(strs[0].charAt(i++)))
      for (int j = 1; j < strs.length; j++)
        if (i == strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) break A;
    return sb.toString();
  }

}
