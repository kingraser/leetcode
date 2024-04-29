package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Objects;
import java.util.Stack;

import org.junit.Test;

public class ValidParentheses {

  /*
  Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
  The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
  */

  @Test
  public void test() {
    assertTrue(isValid("()"));
    assertFalse(isValid("(}"));
  }

  private static final Map<Character, Character> MAP = Map.of('}', '{', ')', '(', ']', '[');

  public boolean isValid(String s) {
    if (s == null || (s.length() & 1) == 1) return false; // odd
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray())
      if (MAP.containsKey(c)) {
        if (stack.isEmpty() || !Objects.equals(stack.pop(), MAP.get(c))) return false;
      } else stack.push(c);
    return stack.isEmpty();
  }
}
