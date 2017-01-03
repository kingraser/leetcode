/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class LongestSubstringWithoutRepeatingCharacters {

  /*
  Given a string, find the length of the longest substring without repeating characters. 
  Examples:  
  Given "abcabcbb", the answer is "abc", which the length is 3.
  Given "bbbbb", the answer is "b", with the length of 1.  
  Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
  */

  public int lengthOfLongestSubstring(String s) {
    int max = 0, len = 0;
    int[] map = new int[128];
    for (int i = 0, first = 0, c; i < s.length(); map[c] = ++i)
      if (map[c = s.charAt(i)] != 0) {
        if (max < len) max = len;
        if (first < map[c]) first = map[c];
        len = i - first + 1;
      } else len++;
    return max < len ? len : max;
  }

  @Test
  public void test() {
    assertEquals(2, lengthOfLongestSubstring("abba"));
    assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
    assertEquals(1, lengthOfLongestSubstring("bbbbb"));
  }

}
