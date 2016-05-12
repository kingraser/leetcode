/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Set;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class EvaluateReversePolishNotation {
    /*
    Evaluate the value of an arithmetic expression in Reverse Polish Notation.    
    Valid operators are +, -, *, /. Each operand may be an integer or another expression.    
    Some examples:    
      ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
      ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6    
    */

    @Test
    public void test() {
        Assert.assertEquals(9, evalRPN(new String[] { "2", "1", "+", "3", "*" }));
        Assert.assertEquals(6, evalRPN(new String[] { "4", "13", "5", "/", "+" }));
    }

    public int evalRPN(String[] tokens) {
        Set<String> set = Sets.newHashSet("+", "-", "*", "/");
        Stack<Integer> integers = new Stack<>();
        for (String s : tokens)
            if (set.contains(s)) {
                int right = integers.pop(), left = integers.pop();
                if (s.equals("+")) left += right;
                else if (s.equals("-")) left -= right;
                else if (s.equals("*")) left *= right;
                else left /= right;
                integers.push(left);
            } else integers.push(Integer.parseInt(s));
        return integers.pop();
    }
}
