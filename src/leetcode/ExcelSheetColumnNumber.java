package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExcelSheetColumnNumber {

  /*
  A -> 1
  B -> 2
  C -> 3
  ...
  Z -> 26
  AA -> 27
  AB -> 28 
  */

  public int titleToNumber(String s) {
    int n = 0;
    for (int i = s.length() - 1, radix = 1; i >= 0; n += (s.charAt(i--) - '@') * radix, radix *= 26);
    return n;
  }

  @Test
  public void test() {
    assertEquals(28, titleToNumber("AB"));
  }

}
