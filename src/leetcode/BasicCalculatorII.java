package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

import org.junit.Test;

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

    public static final Map<Character, BiFunction<Integer, Integer, Integer>> OPERATOR_MAP = Map.of(
            '+', Integer::sum,
            '-', (a, b) -> b - a,
            '*', (a, b) -> b * a,
            '/', (a, b) -> b / a);

    @Test
    public void test() {
        assertEquals(27, calculate("100000000/1/2/3/4/5/6/7/8/9/10"));
        assertEquals(7, calculate("3+2*2"));
        assertEquals(1, calculate(" 3/2 "));
        assertEquals(5, calculate(" 3+5 / 2 "));
    }

    public int calculate(String s) {
        int num = 0;
        Stack<Character> operators = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            if ('0' <= c && c <= '9') num = num * 10 + c - '0';
            else {
                numStack.push(num);
                num = 0;
                while (!hasHigherPriority(c, operators)) calculate(numStack, operators);
                operators.push(c);
            }
        }
        numStack.push(num);
        while (numStack.size() != 1) calculate(numStack, operators);
        return numStack.peek();
    }

    private void calculate(Stack<Integer> numStack, Stack<Character> operators) {
        numStack.push(OPERATOR_MAP
                .get(operators.pop())
                .apply(numStack.pop(), numStack.pop()));
    }

    private boolean hasHigherPriority(char c, Stack<Character> operators) {
        return operators.isEmpty()
                || ((c == '*' || c == '/') && (operators.peek() == '+' || operators.peek() == '-'));
    }
}
