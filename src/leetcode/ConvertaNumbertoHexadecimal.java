package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConvertaNumbertoHexadecimal {

  @Test
  public void test() {
    assertEquals("1a", toHex(26));
    assertEquals("ffffffff", toHex(-1));

    assertEquals("1a", toHexZero(26));
    assertEquals("ffffffff", toHexZero(-1));
  }

  private String toHexZero(int n) {
    return Integer.toHexString(n);
  }

  private String toHex(int n) {
    char[] chars = new char[8];
    int idx = 7;
    for (int j; n != 0; n >>>= 4)
      chars[idx--] = (char) ((j = n & 15) < 10 ? '0' + j : 'a' + j - 10);
    return idx == 7 ? "0" : new String(chars, ++idx, 8 - idx);
  }

}
