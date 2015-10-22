/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月22日<p>
//-------------------------------------------------------
public class BasicCalculator {

    /*
    Implement a basic calculator to evaluate a simple expression string.    
    The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .    
    You may assume that the given expression is always valid.    
    Some examples:    
    "1 + 1" = 2
    " 2-1 + 2 " = 3
    "(1+(4+5+2)-3)+(6+8)" = 23
    */

    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>(s.length());
        int result = 0, sign = 1;
        stack.push(1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            else if (c == '(') {
                stack.push(stack.peekFirst() * sign);
                sign = 1;
            } else if (s.charAt(i) == ')') stack.pop();
            else if (s.charAt(i) == '+') sign = 1;
            else if (s.charAt(i) == '-') sign = -1;
            else {
                int temp = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1)))
                    temp = temp * 10 + s.charAt(++i) - '0';
                result += sign * stack.peekFirst() * temp;
            }
        }
        return result;
    }
}
