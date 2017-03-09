package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseBits {

  /*
  Reverse bits of a given 32 bits unsigned integer.
  For example, 
  given input 43261596 (represented in binary as 00000010100101000001111010011100), 
  return 964176192 (represented in binary as 00111001011110000010100101000000). 
  */

  @Test
  public void test() {
    assertEquals(964176192, reverseBits(43261596));
  }

  // cheat with built-in function
  public int reverseBitsZero(int n) {
    return Integer.reverse(n);
  }

  public int reverseBits(int n) {
    int result = 0;
    for (int i = 0; i < 32; i++) {
      result |= n & 1;
      n >>>= 1;
      if (i < 31) result <<= 1;
    }
    return result;
  }

}
