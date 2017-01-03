/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年1月12日;
//-------------------------------------------------------
public class RemoveDuplicateLetters {

  /*
  Given a string which contains only lowercase letters, 
  remove duplicate letters so that every letter appear once and only once. 
  You must make sure your result is the smallest in lexicographical order among all possible results.
  
  Example:    
  Given "bcabc"
  Return "abc"
  
  Given "cbacdcbc"
  Return "acdb" 
  */

  public String removeDuplicateLetters(String s) {
    char[] string = s.toCharArray();
    int[] count = new int[128];
    boolean[] isSelected = new boolean[128];
    int idx = -1;
    for (char c : string)
      count[c]++;
    for (char c : string)
      if (isSelected[c]) count[c]--;
      else {
        for (char end; idx >= 0 && (end = string[idx]) >= c && count[end] > 0; idx--, isSelected[end] = false);
        string[++idx] = c;
        count[c]--;
        isSelected[c] = true;
      }
    return new String(string, 0, ++idx);
  }

  @Test
  public void test() {
    assertEquals("acdb", removeDuplicateLetters("cbacdcbc"));
  }
}
