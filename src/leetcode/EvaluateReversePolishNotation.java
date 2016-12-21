/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
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

  Map<String, BiFunction<Integer, Integer, Integer>> operators = ImmutableMap.of("+", (a, b) -> b + a, "-",
      (a, b) -> b - a, "*", (a, b) -> b * a, "/", (a, b) -> b / a);

  public int evalRPN(String[] tokens) {
    Stack<Integer> integers = new Stack<>();
    for (String s : tokens)
      if (operators.containsKey(s)) integers.push(operators.get(s).apply(integers.pop(), integers.pop()));
      else integers.push(Integer.parseInt(s));
    return integers.pop();
  }
}
