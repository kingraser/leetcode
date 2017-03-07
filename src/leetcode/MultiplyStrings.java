package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class MultiplyStrings {

  /*
  Given two numbers represented as strings, return multiplication of the numbers as a string.    
  Note: The numbers can be arbitrarily large and are non-negative.
  */

  @Test
  public void test() {
    assertEquals("998001", multiply("999", "999"));
    assertEquals("56088", multiply("123", "456"));
    assertEquals("0", multiply("999", "0"));
  }

  public String multiply(String S1, String S2) {
    int l1 = S1.length(), l2 = S2.length(), result[] = new int[l1 + l2], start;
    for (int idx1 = l1 - 1; idx1 >= 0; idx1--)
      for (int idx2 = l2 - 1, carry = 0, idx; idx2 >= 0 || carry > 0; idx2--, carry /= 10)
        result[idx = idx1 + idx2 + 1] = (carry += result[idx] + getDigit(S1, idx1) * getDigit(S2, idx2)) % 10;
    return new String(convertToCharArray(result), start = getFirstNotZeroIdx(result), result.length - start);
  }

  private int[] convertToCharArray(int[] array) {
    return Arrays.stream(array).map(i -> '0' + i).toArray();
  }

  private int getDigit(String s, int idx) {
    return idx < 0 ? 0 : s.charAt(idx) - '0';
  }

  private int getFirstNotZeroIdx(int[] A) {
    for (int i = 0; i < A.length - 1; i++)
      if (A[i] != 0) return i;
    return A.length - 1;
  }

}
