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
    if (s == null) return null;
    char[] array = s.toCharArray();
    reverseWords(array);
    return removeUnnecessarySpaces(array);
  }

  static void reverseWords(char[] array) {
    ArrayUtil.reverse(array);
    reverseSingleWordOneByOne(array);
  }

  static void reverseSingleWordOneByOne(char[] array) {
    for (int left, right = 0, end = ArrayUtil.findLastNot(array, ' '); right < end;)
      if ((left = ArrayUtil.findFirstNot(array, right, end, ' ')) == -1) return;
      else ArrayUtil.reverse(array, left,
          (right = ArrayUtil.findFirst(array, left, end, ' ')) == -1 ? right = end : right - 1);
  }

  static String removeUnnecessarySpaces(char[] array) {
    int start = ArrayUtil.findFirstNot(array, ' '), end = ArrayUtil.findLastNot(array, ' '), writeIdx = start;
    if (start == -1) return "";
    for (int readIdx = start; readIdx <= end;) {
      for (; readIdx <= end && array[readIdx] != ' '; array[writeIdx++] = array[readIdx++]);
      if (readIdx > end) break;
      array[writeIdx++] = ' ';
      for (; readIdx <= end && array[readIdx] == ' '; readIdx++);
    }
    return new String(array, start, writeIdx - start);
  }

}
