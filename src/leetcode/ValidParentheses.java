/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Stack;

import com.google.common.collect.ImmutableMap;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class ValidParentheses {

  public boolean isVachalid(String s) {
    ImmutableMap<Character, Character> map = ImmutableMap.of('}', '{', ')', '(', ']', '[');
    if ((s.length() & 1) == 1) return false;//odd
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray())
      if (map.containsKey(c) && (stack.isEmpty() || stack.pop() != map.get(c))) return false;
      else stack.push(c);
    return stack.isEmpty();
  }
}
