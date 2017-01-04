/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.Stack;

import org.junit.Test;

import leetcode.common.NestedInteger;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年8月22日;
//-------------------------------------------------------
public class MiniParser {
  /*
  Given a nested list of integers represented as a string, implement a parser to deserialize it.
  
  Each element is either an integer, or a list -- whose elements may also be integers or other lists.
  
  Note: You may assume that the string is well-formed:
  
    String is non-empty.
    String does not contain white spaces.
    String contains only digits 0-9, [, - ,, ].
  
  Example 1:
  
  Given s = "324",
  
  You should return a NestedInteger object which contains a single integer 324.
  
  Example 2:
  
  Given s = "[123,[456,[789]]]",
  
  Return a NestedInteger object containing a nested list with 2 elements:
  
  1. An integer containing value 123.
  2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789.
  */

  public NestedInteger deserialize(String s) {
    Stack<NestedInteger> stack = new Stack<>();
    int top, num = 0;
    for (char c : s.toCharArray())
      if (c == '[') stack.push(new NestedInteger());
      else if (c == '-') stack.push(new NestedInteger(Integer.MIN_VALUE));
      else if (c >= '0' && c <= '9') {
        if (stack.isEmpty() || !stack.peek().isInteger()) stack.push(new NestedInteger(c - '0'));
        else stack.peek().setInteger(Objects.equals(Integer.MIN_VALUE, top = stack.peek().getInteger()) ? '0' - c
            : top * 10 + (top < 0 ? '0' - c : c - '0'));
      } else if (c == ',' && (num > 0 || stack.peek().isInteger())) {
        push(stack);
        num = 0;
      } else if (c == ']') {
        if (stack.peek().isInteger()) num = 1;
        push(stack);
      }
    return stack.pop();
  }

  private void push(Stack<NestedInteger> stack) {
    if (stack.size() < 2) return;
    NestedInteger i = stack.pop();
    stack.peek().add(i);
  }

  @Test
  public void test() {
    String s = "[123,[456,[789]]]";
    assertEquals(s, deserialize(s).toString());
    s = "[[],[[[[]],-4],[[[]],[0],[[]]]]]";
    assertEquals(s, deserialize(s).toString());
    s = "[[[[55]]],[[31]],[99],[],75]";
    assertEquals(s, deserialize(s).toString());
  }
}
