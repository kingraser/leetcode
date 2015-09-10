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
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++)
            if (!map.containsKey(s.charAt(i))) stack.add(s.charAt(i));
            else if (stack.isEmpty() || stack.pop() != map.get(s.charAt(i))) return false;
        return stack.isEmpty();
    }
}
