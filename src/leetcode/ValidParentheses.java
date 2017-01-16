/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Objects;
import java.util.Stack;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class ValidParentheses {

  @Test
  public void test() {
    assertTrue(isValid("()"));
    assertFalse(isValid("(}"));
  }

  private static final Map<Character, Character> MAP = ImmutableMap.of('}', '{', ')', '(', ']', '[');

  public boolean isValid(String s) {
    if ((s.length() & 1) == 1) return false;//odd
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray())
      if (MAP.containsKey(c)) {
        if (stack.isEmpty() || !Objects.equals(stack.pop(), MAP.get(c))) return false;
      } else stack.push(c);
    return stack.isEmpty();
  }
}
