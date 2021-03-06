package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.stream.IntStream;

import org.junit.Test;

public class AdditiveNumber {

  /*
  Additive number is a string whose digits can form additive sequence.    
  A valid additive sequence should contain at least three numbers. 
  Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
  
  For example:
  "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
  
  1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
  
  "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
  
  1 + 99 = 100, 99 + 100 = 199
  
  Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.    
  Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
  */

  @Test
  public void test() {
    assertTrue(isAdditiveNumber("112358"));
    assertTrue(isAdditiveNumber("199100199"));
    assertFalse(isAdditiveNumber("1203"));
    assertFalse(isAdditiveNumber("1023"));
  }

  public boolean isAdditiveNumber(String num) {
    return num.charAt(0) == '0' ? isValid(BigInteger.ZERO, 1, num)
        : IntStream.range(1, (num.length() + 1) >> 1)
            .anyMatch(len -> isValid(new BigInteger(num.substring(0, len)), len, num));
  }

  private boolean isValid(BigInteger a, int start, String num) {
    if (num.charAt(start) == '0') return isValid(a, BigInteger.ZERO, start + 1, num);
    for (int len = 1; num.length() - start - len >= Math.max(start, len); len++)
      if (isValid(a, new BigInteger(num.substring(start, start + len)), start + len, num)) return true;
    return false;
  }

  private boolean isValid(BigInteger a, BigInteger b, int start, String num) {
    if (start == num.length()) return true;
    BigInteger aPlusb = a.add(b);
    String sum = aPlusb.toString();
    return num.startsWith(sum, start) && isValid(b, aPlusb, start + sum.length(), num);
  }
}
