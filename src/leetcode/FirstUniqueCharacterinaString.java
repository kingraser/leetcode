/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年8月23日;
//-------------------------------------------------------
public class FirstUniqueCharacterinaString {

  /*
  Given a string, find the first non-repeating character in it and return it's index. 
  If it doesn't exist, return -1.
  
  Examples:
  
  s = "leetcode"
  return 0.
  
  s = "loveleetcode",
  return 2.
  
  Note: You may assume the string contain only lowercase letters. 
  */

  @Test
  public void test() {
    assertEquals(0, firstUniqChar("leetcode"));
    assertEquals(2, firstUniqChar("loveleetcode"));
  }

  public int firstUniqChar(String s) {
    int[] counts = new int[128];
    s.chars().forEach(c -> counts[c]++);
    for (int i = 0; i < s.length(); i++)
      if (counts[s.charAt(i)] == 1) return i;
    return -1;
  }
}
