/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.LinkedList;
import java.util.Queue;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class ImplementStackusingQueues {

    Queue<Integer> stack = new LinkedList<>();

    int top;

    public void push(int x) {
        top = x;
        stack.add(x);
    }

    public void pop() {
        for (int i = 0; i < stack.size() - 2; i++)
            stack.add(stack.poll());
        top = stack.poll();
        stack.add(top);
        stack.poll();
    }

    public int top() {
        return top;
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
