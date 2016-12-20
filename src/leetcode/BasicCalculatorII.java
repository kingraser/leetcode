/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年3月1日;
//-------------------------------------------------------
public class BasicCalculatorII {
  /*
  implement a basic calculator to evaluate a simple expression string.
  The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
  The integer division should truncate toward zero.    
  You may assume that the given expression is always valid.
  
  Some examples:
  
  "3+2*2" = 7
  " 3/2 " = 1
  " 3+5 / 2 " = 5
  */

  private static final Map<Character, BiFunction<Integer, Integer, Integer>> OPERATOR_MAP = ImmutableMap.of('+',
      (a, b) -> b + a, '-', (a, b) -> b - a, '*', (a, b) -> b * a, '/', (a, b) -> b / a);

  @Test
  public void test() {
    assertEquals(27, calculate("100000000/1/2/3/4/5/6/7/8/9/10"));
    assertEquals(7, calculate("3+2*2"));
    assertEquals(1, calculate(" 3/2 "));
    assertEquals(5, calculate(" 3+5 / 2 "));
  }

  public int calculate(String s) {
    int temp = 0;
    Stack<Character> operators = new Stack<>();
    Stack<Integer> nums = new Stack<>();
    for (char c : s.toCharArray())
      if (c == ' ') continue;
      else if ('0' <= c && c <= '9') temp = temp * 10 + c - '0';
      else {
        nums.push(temp);
        temp = 0;
        while (!hasHigherPriority(c, operators))
          calculate(nums, operators);
        operators.push(c);
      }
    nums.push(temp);
    while (nums.size() != 1)
      calculate(nums, operators);
    return nums.peek();
  }

  private void calculate(Stack<Integer> nums, Stack<Character> operators) {
    nums.push(OPERATOR_MAP.get(operators.pop()).apply(nums.pop(), nums.pop()));
  }

  private boolean hasHigherPriority(char c, Stack<Character> operators) {
    return operators.isEmpty() || ((c == '*' || c == '/') && (operators.peek() == '+' || operators.peek() == '-'));
  }
}
