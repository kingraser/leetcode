package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

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

  public static final Map<Character, BiFunction<Integer, Integer, Integer>> OPERATOR_MAP = ImmutableMap.of('+',
      (a, b) -> b + a, '-', (a, b) -> b - a, '*', (a, b) -> b * a, '/', (a, b) -> b / a);

  @Test
  public void test() {
    assertEquals(27, calculate("100000000/1/2/3/4/5/6/7/8/9/10"));
    assertEquals(7, calculate("3+2*2"));
    assertEquals(1, calculate(" 3/2 "));
    assertEquals(5, calculate(" 3+5 / 2 "));
  }

  public int calculate(String s) {
    int num = 0;
    Stack<Integer> operators = new Stack<>(), nums = new Stack<>();
    for (char c : s.toCharArray())
      if (c == ' ') continue;
      else if ('0' <= c && c <= '9') num = num * 10 + c - '0';
      else {
        for (nums.push(num), num = 0; !hasHigherPriority(c, operators); calculate(nums, operators));
        operators.push((int) c);
      }
    for (nums.push(num); nums.size() != 1; calculate(nums, operators));
    return nums.peek();
  }

  private void calculate(Stack<Integer> nums, Stack<Integer> operators) {
    nums.push(OPERATOR_MAP.get((char) (operators.pop().intValue())).apply(nums.pop(), nums.pop()));
  }

  private boolean hasHigherPriority(char c, Stack<Integer> operators) {
    return operators.isEmpty() || ((c == '*' || c == '/') && (operators.peek() == '+' || operators.peek() == '-'));
  }
}
