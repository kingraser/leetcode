package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Base7 {

  /*
  Given an integer, return its base 7 string representation.
  
  Example 1:
  
  Input: 100
  Output: "202"
  
  Example 2:
  
  Input: -7
  Output: "-10"
  
  Note: The input will be in range of [-1e7, 1e7]. 
  */

  @Test
  public void test() {
    assertEquals("202", convertTo7(100));
    assertEquals("-10", convertTo7(-7));
  }

  public String convertTo7(int num) {
    return Integer.toString(num, 7);
  }

}
