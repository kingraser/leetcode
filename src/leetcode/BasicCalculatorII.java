/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年3月1日;
//-------------------------------------------------------
public class BasicCalculatorII {
    /*
    implement a basic calculator to evaluate a simple expression string.
    The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
    The integer division should truncate toward zero.    
    You may assume that the given expression is always valid.
    
    Some examples:
    
    "3+2*2" = 7
    " 3/2 " = 1
    " 3+5 / 2 " = 5
    */

    @Test
    public void test() {
        Assert.assertEquals(27, calculate("100000000/1/2/3/4/5/6/7/8/9/10"));
        Assert.assertEquals(7, calculate("3+2*2"));
        Assert.assertEquals(1, calculate(" 3/2 "));
        Assert.assertEquals(5, calculate(" 3+5 / 2 "));
    }

    public int calculate(String s) {
        int num = 0;
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (char c : s.toCharArray())
            if (c == ' ') continue;
            else if (Character.isDigit(c)) num = num * 10 + Character.digit(c, 10);
            else {
                numbers.push(num);
                num = 0;
                while (!hasHigherPriority(c, operators))
                    calculate(numbers, operators);
                operators.push(c);
            }
        numbers.push(num);
        while (numbers.size() != 1)
            calculate(numbers, operators);
        return numbers.peek();
    }

    private void calculate(Stack<Integer> numbers, Stack<Character> operators) {
        char operator = operators.pop();
        int a = numbers.pop(), b = numbers.pop(), c = 0;
        if (operator == '+') c = b + a;
        else if (operator == '-') c = b - a;
        else if (operator == '*') c = b * a;
        else c = b / a;
        numbers.push(c);
    }

    private boolean hasHigherPriority(char c, Stack<Character> operators) {
        return (operators.isEmpty()
                || ((c == '*' || c == '/') && (operators.peek() == '+' || operators.peek() == '-')));
    }
}
