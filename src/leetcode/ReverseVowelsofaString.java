/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

  private static final Set<Character> VOWEL = Stream.of('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U')
      .collect(Collectors.toSet());

  public String reverseVowels(String s) {
    char[] array = s.toCharArray();
    for (int l = 0, r = s.length() - 1; l < r;) {
      for (; l < r && !isVowel(array[l]); l++);
      for (; l < r && !isVowel(array[r]); r--);
      swap(array, l++, r--);
    }
    return new String(array);
  }

  private boolean isVowel(char c) {
    return VOWEL.contains(c);
  }

  private void swap(char[] array, int left, int right) {
    if (left == right) return;
    char temp = array[left];
    array[left] = array[right];
    array[right] = temp;
  }
}
