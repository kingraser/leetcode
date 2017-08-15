package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import leetcode.util.MathUtil;

public class FractionAdditionandSubtraction {

  /*
  Given a string representing an expression of fraction addition and subtraction, 
  you need to return the calculation result in string format. 
  The final result should be irreducible fraction. 
  If your final result is an integer, say 2, 
  you need to change it to the format of fraction that has denominator 1. 
  So in this case, 2 should be converted to 2/1.
  
  Example 1:  
  Input:"-1/2+1/2"
  Output: "0/1"
  
  Example 2:  
  Input:"-1/2+1/2+1/3"
  Output: "1/3"
  
  Example 3:  
  Input:"1/3-1/2"
  Output: "-1/6"
  
  Example 4:  
  Input:"5/3+1/3"
  Output: "2/1"
  
  Note:  
    The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
    Each fraction (input and output) has format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
    The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1,10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
    The number of given fractions will be in the range [1,10].
    The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int. 
  */

  @Test
  public void test() {
    assertEquals("0/1", fractionAddition("-1/2+1/2"));
    assertEquals("1/3", fractionAddition("-1/2+1/2+1/3"));
    assertEquals("-1/6", fractionAddition("1/3-1/2"));
    assertEquals("2/1", fractionAddition("5/3+1/3"));
  }

  public String fractionAddition(String expression) {
    return calculate(getMap(expression));
  }

  private String calculate(Map<Integer, Integer> map) {
    int denominator = map.entrySet().stream().filter(e -> e.getValue() != 0).map(e -> e.getKey()).reduce(1,
        MathUtil::lowestCommonMultiple),
        numerator = map.entrySet().stream().filter(e -> e.getValue() != 0)
            .map(e -> e.getValue() * denominator / e.getKey()).reduce(0, Integer::sum),
        gcd = Math.abs(MathUtil.gcd(denominator, numerator));
    return numerator / gcd + "/" + denominator / gcd;
  }

  private Map<Integer, Integer> getMap(String expression) {
    Map<Integer, Integer> result = new HashMap<>();
    int numerator = 0, denominator = 0, isDenominator = 0, isPositive = 1;
    for (char c : expression.toCharArray())
      if (c == '+' || c == '-') {
        if (denominator != 0) result.merge(denominator, isPositive > 0 ? numerator : -numerator, Integer::sum);
        isDenominator = numerator = denominator = 0;
        isPositive = c == '+' ? 1 : -1;
      } else if (c == '/') isDenominator = 1;
      else if (isDenominator == 0) numerator = numerator * 10 + c - '0';
      else denominator = denominator * 10 + c - '0';
    result.merge(denominator, isPositive > 0 ? numerator : -numerator, Integer::sum);
    return result;
  }

}
