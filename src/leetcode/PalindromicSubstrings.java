package leetcode;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class PalindromicSubstrings {

  /*
  Given a string, your task is to count how many palindromic substrings in this string.  
  The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
  
  Example 1:  
  Input: "abc"
  Output: 3
  Explanation: Three palindromic strings: "a", "b", "c".
  
  Example 2:  
  Input: "aaa"
  Output: 6
  Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
  
  Note:  
    The input string length won't exceed 1000.  
  */

  @Test
  public void test() {
    assertEquals(3, countSubstrings("abc"));
    assertEquals(6, countSubstrings("aaa"));
  }

  public int countSubstrings(String s) {
    if (StringUtils.isEmpty(s)) return 0;
    int[] result = new int[1];
    for (int i = 0; i < s.length(); i++) {
      extendPalindrome(result, s, i, i);
      extendPalindrome(result, s, i, i + 1);
    }
    return result[0];
  }

  private void extendPalindrome(int[] result, String s, int left, int right) {
    for (; left >= 0 && right < s.length() && s.charAt(left--) == s.charAt(right++); result[0]++);
  }

}
