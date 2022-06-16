package leetcode;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ComplexNumberMultiplication {

  /*
  Given two strings representing two complex numbers.  
  You need to return a string representing their multiplication. Note i^2 = -1 according to the definition.
  
  Example 1:  
  Input: "1+1i", "1+1i"
  Output: "0+2i"
  Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
  
  Example 2:  
  Input: "1+-1i", "1+-1i"
  Output: "0+-2i"
  Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
  
  Note:  
    The input strings will not have extra blank.
    The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
  */

  @Test
  public void test() {
    assertEquals("0+2i", complexNumberMultiply("1+1i", "1+1i"));
    assertEquals("0+-2i", complexNumberMultiply("1+-1i", "1+-1i"));
  }

  public String complexNumberMultiply(String a, String b) {
    int[] coef = Arrays.stream((a + b).split("[+i]")).mapToInt(Integer::parseInt).toArray();
    return (coef[0] * coef[2] - coef[1] * coef[3]) + "+" + (coef[1] * coef[2] + coef[0] * coef[3]) + "i";
  }

}
