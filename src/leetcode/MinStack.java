/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.Stack;
import java.util.stream.Stream;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class MinStack {

  /*
  Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
  
  push(x) -- Push element x onto stack.
  pop() -- Removes the element on top of the stack.
  top() -- Get the top element.
  getMin() -- Retrieve the minimum element in the stack.    
  */

  @Test
  public void test() {
    Stream.of(3, 2, 1).forEach(i -> push(i));
    assertEquals(1, getMin());
    assertEquals(1, top());
    pop();
    assertEquals(2, getMin());
    assertEquals(2, top());
    pop();
    assertEquals(3, getMin());
    assertEquals(3, top());
    push(1);
    assertEquals(1, getMin());
    assertEquals(1, top());
  }

  Stack<Integer> stack = new Stack<>(), minStack = new Stack<>();

  public void push(int x) {
    if (minStack.isEmpty() || x <= minStack.peek()) minStack.push(x);
    stack.push(x);
  }

  public void pop() {
    if (Objects.equals(stack.pop(), minStack.peek())) minStack.pop();
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minStack.peek();
  }
}
