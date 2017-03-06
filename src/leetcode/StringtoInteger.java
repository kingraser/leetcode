package leetcode;

import static java.lang.Character.isDigit;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringtoInteger {

  /*
  implement atoi to convert a string to an integer.  
  Hint: Carefully consider all possible input cases. 
  If you want a challenge, please do not see below and ask yourself what are the possible input cases.  
  Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
  You are responsible to gather all the input requirements up front. 
  */

  @Test
  public void test() {
    assertEquals(2147483647, myAtoi("2147483648"));
  }

  public int myAtoi(String str) {
    if ((str = str.trim()).length() == 0 || !isValidFirstChar(str.charAt(0))) return 0;
    long result = 0;
    boolean isNegative = str.charAt(0) == '-';
    for (int i = !isDigit(str.charAt(0)) ? 1 : 0; i < str.length() && isDigit(str.charAt(i));)
      if (!isNegative && (result = result * 10 + str.charAt(i++) - '0') > Integer.MAX_VALUE) return Integer.MAX_VALUE;
      else if (isNegative && -result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
    return (int) (isNegative ? -result : result);
  }

  private boolean isValidFirstChar(char c) {
    return isDigit(c) || c == '+' || c == '-';
  }

}
