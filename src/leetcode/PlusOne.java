package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class PlusOne {

  /*
  Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.  
  You may assume the integer do not contain any leading zero, except the number 0 itself.  
  The digits are stored such that the most significant digit is at the head of the list.
  */

  public int[] plusOne(int[] digits) {
    for (int l = digits.length - 1; l > -1;)
      if (digits[l] == 9) digits[l--] = 0;
      else {
        digits[l]++;
        return digits;
      }
    int[] res = new int[digits.length + 1];
    res[0] = 1;
    return res;
  }

  @Test
  public void test() {
    assertArrayEquals(new int[] { 1, 0, 0, 0 }, plusOne(new int[] { 9, 9, 9 }));
  }
}
