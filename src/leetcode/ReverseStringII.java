package leetcode;

import static leetcode.util.ArrayUtil.reverse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseStringII {

  /*
  Given a string and an integer k, 
  you need to reverse the first k characters for every 2k characters counting from the start of the string. 
  If there are less than k characters left, reverse all of them. 
  If there are less than 2k but greater than or equal to k characters, 
  then reverse the first k characters and left the other as original.
  
  Example:
  Input: s = "abcdefg", k = 2
  Output: "bacdfeg"
  
  Restrictions:  
    The string consists of lower English letters only.
    Length of the given string and k will in the range [1, 10000]
  */

  @Test
  public void test() {
    assertEquals("bacdfeg", reverseStr("abcdefg", 2));
  }

  public String reverseStr(String s, int k) {
    char[] result = s.toCharArray();
    for (int start = 0, step1 = k - 1, step2 = k << 1; start < s.length(); start += step2)
      reverse(result, start, Math.min(start + step1, s.length() - 1));
    return new String(result);
  }

}
