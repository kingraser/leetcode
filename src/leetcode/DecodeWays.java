package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DecodeWays {

  /*
  A message containing letters from A-Z is being encoded to numbers using the following mapping:
  
  'A' -> 1
  'B' -> 2
  ...
  'Z' -> 26
  
  Given an encoded message containing digits, determine the total number of ways to decode it.  
  For example, Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).  
  The number of ways decoding "12" is 2.   
  */

  /*
  Similar to Climbing Stairs   
  */

  @Test
  public void test() {
    assertEquals(2, numDecodings("12"));
    assertEquals(2, numDecodings("1023"));
    assertEquals(0, numDecodings("10023"));
  }

  public int numDecodings(String s) {
    if (s == null || s.length() == 0) return 0;
    int previous = 0, current = 1, temp;
    for (int i = 0; i < s.length() && current > 0; i++) {
      if (s.charAt(i) == '0') current = 0;
      if (i < 1 || !(s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6'))) previous = 0;
      temp = current;
      current += previous;
      previous = temp;
    }
    return current;
  }
}
