/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2015年12月29日;
//-------------------------------------------------------
public class MaximumProductofWordLengths {

  /*
  Given a string array words, 
  find the maximum value of length(word[i]) * length(word[j]) 
  where the two words do not share common letters. 
  You may assume that each word will contain only lower case letters. 
  If no such two words exist, return 0.
  
  Example 1:
  
  Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
  Return 16
  The two words can be "abcw", "xtfn".
  
  Example 2:
  
  Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
  Return 4
  The two words can be "ab", "cd".
  
  Example 3:
  
  Given ["a", "aa", "aaa", "aaaa"]
  Return 0
  No such pair of words. 
  */

  @Test
  public void test() {
    assertEquals(16, maxProduct(new String[] { "abcw", "baz", "foo", "bar", "xtfn", "abcdef" }));
    assertEquals(4, maxProduct(new String[] { "a", "ab", "abc", "d", "cd", "bcd", "abcd" }));
    assertEquals(0, maxProduct(new String[] { "a", "aa", "aaa", "aaaa" }));
  }

  public int maxProduct(String[] words) {
    int[] mask = new int[words.length];
    for (int i = 0; i < words.length; i++)
      for (int j = 0; j < words[i].length(); j++)
        mask[i] |= 1 << (words[i].charAt(j) - 'a');
    int max = 0;
    for (int i = 0; i < words.length; i++)
      for (int j = i + 1; j < words.length; j++)
        if ((mask[i] & mask[j]) == 0) max = Math.max(words[i].length() * words[j].length(), max);
    return max;
  }

}
