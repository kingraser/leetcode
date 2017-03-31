package leetcode;

import static leetcode.AddStrings.addStrings;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddBinary {

  /*
  Given two binary strings, return their sum (also a binary string). 
  For example,
  a = "11"
  b = "1"
  Return "100".
  */

  @Test
  public void test() {
    assertEquals("100", addBinary("11", "1"));
    assertEquals("110", addBinary("11", "11"));
  }

  public String addBinary(String a, String b) {
    return addStrings(a, b, 2);
  }
}
