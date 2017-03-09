package leetcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.Queue;

import org.junit.Test;

public class ImplementStackusingQueues {

  @Test
  public void test() {
    Stack stack = new Stack();
    assertTrue(stack.empty());
    stack.push(1);
    assertFalse(stack.empty());
    assertEquals(1, stack.top());
    stack.push(2);
    assertEquals(2, stack.top());
    stack.pop();
    assertEquals(1, stack.top());
    stack.pop();
    assertTrue(stack.empty());
  }

  class Stack {
    Queue<Integer> queue = new ArrayDeque<>();

    int top;

    public void push(int x) {
      queue.add(top = x);
    }

    public void pop() {
      for (int i = 0; i < queue.size() - 1; i++)
        queue.add((top = queue.poll()));
      queue.poll();
    }

    public int top() {
      return top;
    }

    public boolean empty() {
      return queue.isEmpty();
    }
  }
}
