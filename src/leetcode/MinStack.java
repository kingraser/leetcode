package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.Stack;
import java.util.stream.Stream;

import org.junit.Test;

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
    MyStack stack = new MyStack();
    Stream.of(3, 2, 1).forEach(i -> stack.push(i));
    assertEquals(1, stack.getMin());
    assertEquals(1, stack.top());
    stack.pop();
    assertEquals(2, stack.getMin());
    assertEquals(2, stack.top());
    stack.pop();
    assertEquals(3, stack.getMin());
    assertEquals(3, stack.top());
    stack.push(1);
    assertEquals(1, stack.getMin());
    assertEquals(1, stack.top());
  }

  class MyStack {
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
}
