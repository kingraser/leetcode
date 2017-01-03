/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年4月25日;
//-------------------------------------------------------
public class ReverseVowelsofaString {
  /*
  Write a function that takes a string as input and reverse only the vowels of a string.
  
  Example 1:
  Given s = "hello", return "holle".
  
  Example 2:
  Given s = "leetcode", return "leotcede". 
  */

  @Test
  public void test() {
    assertEquals("uoiea", reverseVowels("aeiou"));
    assertEquals("holle", reverseVowels("hello"));
    assertEquals("leotcede", reverseVowels("leetcode"));
  }

  private static final char[] vowels = new char[] { 'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U' };

  private static final boolean[] consonants = new boolean[128];

  static {
    Arrays.fill(consonants, true);
    for (char vowel : vowels)
      consonants[vowel] = false;
  }

  public String reverseVowels(String s) {
    char[] array = s.toCharArray();
    for (int l = 0, r = s.length() - 1; l < r;) {
      for (; l < r && consonants[array[l]]; l++);
      for (; l < r && consonants[array[r]]; r--);
      swap(array, l++, r--);
    }
    return new String(array);
  }

  private void swap(char[] array, int l, int r) {
    char c = array[l];
    array[l] = array[r];
    array[r] = c;
  }

}
