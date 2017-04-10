package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.util.ArrayUtil;

public class ReverseWordsinaString {
  /*
  Given an input string, reverse the string word by word.
  
  For example,
  Given s = "the sky is blue",
  return "blue is sky the". 
  */

  @Test
  public void test() {
    assertEquals("", reverseWords("  "));
    assertEquals("blue is sky the", reverseWords("the sky is blue"));
    assertEquals("1", reverseWords("1"));
    assertEquals("a", reverseWords(" a "));
  }

  public static String reverseWords(String s) {
    String[] result = s.trim().split(" +");
    ArrayUtil.reverse(result);
    return String.join(" ", result);
  }

}
