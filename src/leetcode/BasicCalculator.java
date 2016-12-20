/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月22日<p>
//-------------------------------------------------------
public class BasicCalculator {

  /*
  Implement a basic calculator to evaluate a simple expression string.    
  The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .    
  You may assume that the given expression is always valid.    
  Some examples:    
  "1 + 1" = 2
  " 2-1 + 2 " = 3
  "(1+(4+5+2)-3)+(6+8)" = 23
  */

  @Test
  public void test() {
    assertEquals(2, calculate("1 + 1"));
    assertEquals(3, calculate(" 2-1 + 2 "));
    assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
    assertEquals(-4, calculate("1-(5)"));
    assertEquals(3, calculate("2-(5-6)"));
  }

  public int calculate(String s) {
    Stack<Integer> stack = new Stack<>();
    int result = 0, temp = 0, sign = 1;//1 for +, -1 for -
    for (char c : s.toCharArray())
      if ('0' <= c && c <= '9') temp = temp * 10 + c - '0';
      else {
        result += temp * sign;
        temp = 0;
        if (c == '+') sign = 1;
        else if (c == '-') sign = -1;
        else if (c == '(') {
          stack.push(result);
          stack.push(sign);
          result = 0;
          sign = 1;
        } else if (c == ')') result = result * stack.pop() + stack.pop();
      }
    return result += temp * sign;
  }
}
