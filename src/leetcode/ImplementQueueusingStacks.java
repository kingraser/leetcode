/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Stack;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class ImplementQueueusingStacks {

  private Stack<Integer> in = new Stack<Integer>(), out = new Stack<Integer>();

  // Push element x to the back of queue.
  public void push(int x) {
    in.push(x);
  }

  // Removes the element from in front of queue.
  public void pop() {
    peek();
    out.pop();
  }

  // Get the front element.
  public int peek() {
    if (out.isEmpty()) for (; !in.isEmpty(); out.push(in.pop()));
    return out.peek();
  }

  // Return whether the queue is empty.
  public boolean empty() {
    return in.isEmpty() && out.isEmpty();
  }
}
