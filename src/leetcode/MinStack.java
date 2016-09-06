/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Objects;
import java.util.Stack;

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

    private Stack<Integer> stack = new Stack<Integer>();

    private Stack<Integer> minStack = new Stack<Integer>();

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
