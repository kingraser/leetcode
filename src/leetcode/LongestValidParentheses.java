package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class LongestValidParentheses {

  /*
  Given a string containing just the characters '(' and ')', 
  find the length of the longest valid (well-formed) parentheses substring.
  For "(()", the longest valid parentheses substring is "()", which has length = 2.
  Another example is ")()())", where the longest valid parentheses substring is "()()", 
  which has length = 4. 
  1stack O(n) time O(n) space
  2dp O(n) time O(n) space One pass
  3 O(n) time O(1) space Two pass
  */

  @Test
  public void test() {
    assertEquals(2, longestValidParentheses("(()"));
    assertEquals(4, longestValidParentheses(")()())"));
  }

  public int longestValidParentheses(String s) {
    Stack<Integer> stack = new Stack<Integer>(); // only add left
    int lastleft = 0, longest = 0;
    for (int i = 0; i < s.length(); i++)
      if (s.charAt(i) == '(') stack.push(i);
      else if (!stack.empty()) {
        stack.pop();
        if (!stack.empty()) longest = Math.max(longest, i - stack.peek());
        else longest = Math.max(longest, i - lastleft + 1);
      } else lastleft = i + 1;
    return longest;
  }

}
