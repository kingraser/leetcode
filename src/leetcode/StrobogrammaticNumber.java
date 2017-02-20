package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StrobogrammaticNumber {

  /*
  A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).  
  Write a function to determine if a number is strobogrammatic. The number is represented as a string.  
  For example, the numbers "69", "88", and "818" are all strobogrammatic.
  */

  private static final char[] MAP = new char[128];
  static {
    MAP['0'] = '0';
    MAP['1'] = '1';
    MAP['8'] = '8';
    MAP['6'] = '9';
    MAP['9'] = '6';
  }

  @Test
  public void test() {
    assertTrue(isStrobogrammatic("69"));
    assertTrue(isStrobogrammatic("88"));
    assertTrue(isStrobogrammatic("818"));
    assertFalse(isStrobogrammatic("6"));
  }

  public boolean isStrobogrammatic(String num) {
    for (int i = 0, len = num.length(), half = len >> 1, last = len - 1; i <= half; i++)
      if (MAP[num.charAt(i)] != num.charAt(last - i)) return false;
    return true;
  }

}
