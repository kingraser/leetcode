/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class LongestValidParentheses {

    /*
    Given a string containing just the characters ’(’ and ’)’, 
    find the length of the longest valid (well-formed) parentheses substring.
    For ”(()”, the longest valid parentheses substring is ”()”, which has length = 2.
    Another example is ”)()())”, where the longest valid parentheses substring is ”()()”, 
    which has length = 4. 
    1stack O(n) time O(n) space
    2dp O(n) time O(n) space One pass
    3 O(n) time O(1) space Two pass
    */

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();//only add left
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

    public int longestValidParenthesesII(String s) {
        int[] f = new int[s.length()];
        int ret = 0;
        for (int i = s.length() - 2; i >= 0; --i) {
            int match = i + f[i + 1] + 1;
            if (s.charAt(i) == '(' && match < s.length() && s.charAt(match) == ')') {// case: "((...))"
                f[i] = f[i + 1] + 2;
                if (match + 1 < s.length()) f[i] += f[match + 1];// if a valid sequence exist afterwards "((...))()"
            }
            ret = Math.max(ret, f[i]);
        }
        return ret;
    }

    public int longestValidParenthesesIII(String s) {
        int answer = 0, depth = 0, start = -1, i = 0;
        for (; i < s.length(); i++)
            if (s.charAt(i) == '(') depth++;
            else if (--depth < 0) {
                start = i;
                depth = 0;
            } else if (depth == 0) answer = Math.max(answer, i - start);
        for (depth = 0, start = s.length(), i = s.length() - 1; i >= 0; --i)
            if (s.charAt(i) == ')') depth++;
            else if (--depth < 0) {
                start = i;
                depth = 0;
            } else if (depth == 0) answer = Math.max(answer, start - i);
        return answer;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, longestValidParenthesesIII("(()"));
        Assert.assertEquals(4, longestValidParenthesesIII(")()())"));
    }
}
