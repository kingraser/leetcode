package leetcode;

import static leetcode.util.ArrayUtil.reverse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseWordsinaStringIII {

  /*
  Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
  
  Example 1:  
  Input: "Let's take LeetCode contest"
  Output: "s'teL ekat edoCteeL tsetnoc"
  
  Note: In the string, each word is separated by single space and there will not be any extra space in the string. 
  */

  @Test
  public void test() {
    assertEquals("s'teL ekat edoCteeL tsetnoc", reverseWords("Let's take LeetCode contest"));
  }

  public static String reverseWords(String s) {
    return new String(reverseWords(s.toCharArray()));
  }

  public static char[] reverseWords(char[] s) {
    for (int left = 0, right; left < s.length;)
      if (s[left] != ' ') {
        for (right = left + 1; right < s.length && s[right] != ' '; right++);
        reverse(s, left, right - 1);
        left = right + 1;
      } else left++;
    return s;
  }
}
