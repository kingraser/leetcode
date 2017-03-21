package leetcode;

import static leetcode.BasicCalculatorII.OPERATOR_MAP;
import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class EvaluateReversePolishNotation {
  /*
  Evaluate the value of an arithmetic expression in Reverse Polish Notation.    
  Valid operators are +, -, *, /. Each operand may be an integer or another expression.    
  Some examples:    
    ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
    ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6    
  */

  @Test
  public void test() {
    assertEquals(9, evalRPN(new String[] { "2", "1", "+", "3", "*" }));
    assertEquals(6, evalRPN(new String[] { "4", "13", "5", "/", "+" }));
  }

  public int evalRPN(String[] tokens) {
    Stack<Integer> integers = new Stack<>();
    for (String s : tokens)
      if (OPERATOR_MAP.containsKey(s.charAt(0)))
        integers.push(OPERATOR_MAP.get(s.charAt(0)).apply(integers.pop(), integers.pop()));
      else integers.push(Integer.parseInt(s));
    return integers.pop();
  }
}
