/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class ImplementStackusingQueues {

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
