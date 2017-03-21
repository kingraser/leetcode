package leetcode;

import static leetcode.BasicCalculatorII.OPERATOR_MAP;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DifferentWaystoAddParentheses {

  /*
  Given a string of numbers and operators, 
  return all possible results from computing all the different possible ways to group numbers and operators. 
  The valid operators are +, - and *.
  
  Example 1    
  Input: "2-1-1".
  
  ((2-1)-1) = 0
  (2-(1-1)) = 2
  
  Output: [0, 2]    
  Example 2    
  Input: "2*3-4*5"
  
  (2*(3-(4*5))) = -34
  ((2*3)-(4*5)) = -14
  ((2*(3-4))*5) = -10
  (2*((3-4)*5)) = -10
  (((2*3)-4)*5) = 10
  
  Output: [-34, -14, -10, -10, 10]
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(-34, -10, -14, -10, 10), diffWaysToCompute("2*3-4*5"));
  }

  public List<Integer> diffWaysToCompute(String input) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      if (Character.isDigit(input.charAt(i))) continue;
      result.addAll(merge(diffWaysToCompute(input.substring(0, i)),
          diffWaysToCompute(input.substring(i + 1, input.length())), input.charAt(i)));
    }
    if (result.isEmpty()) result.add(Integer.parseInt(input));
    return result;
  }

  private List<Integer> merge(List<Integer> a, List<Integer> b, char c) {
    List<Integer> result = new ArrayList<>();
    a.forEach(i -> b.forEach(j -> result.add(OPERATOR_MAP.get(c).apply(j, i))));
    return result;
  }
}
