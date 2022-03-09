package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import static leetcode.AddStrings.addStrings;

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
    TestUtil.testEquals(new Object[][]{
            {"100", "11", "1"},
            {"110", "11", "11"}
    });
  }

  public String addBinary(String a, String b) {
    return addStrings(a, b, 2);
  }
}
