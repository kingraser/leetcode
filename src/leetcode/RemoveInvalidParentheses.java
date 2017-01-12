package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月2日;
//-------------------------------------------------------
public class RemoveInvalidParentheses {

  /*
  Remove the minimum number of invalid parentheses in order to make the input string valid. 
  Return all possible results.  
  Note: The input string may contain letters other than the parentheses ( and ).
  
  Examples:
  
  "()())()" -> ["()()()", "(())()"]
  "(a)())()" -> ["(a)()()", "(a())()"]
  ")(" -> [""]
  */
  @Test
  public void test() {
    assertEquals(Arrays.asList("()()()", "(())()"), removeInvalidParentheses("()())()"));
    assertEquals(Arrays.asList("(a)()()", "(a())()"), removeInvalidParentheses("(a)())()"));
    assertEquals(Arrays.asList(""), removeInvalidParentheses(")("));
  }

  public List<String> removeInvalidParentheses(String s) {
    Set<String> result = new HashSet<>();
    char[] A = s.toCharArray();
    int left = 0, right = 0;
    for (char c : A)
      if (c == '(') left++;
      else if (c == ')') {
        if (left > 0) left--;
        else right++;
      }
    dfs(result, A, 0, left, right, 0, new StringBuilder());
    return new ArrayList<String>(result);
  }

  public void dfs(Set<String> res, char[] s, int i, int left, int right, int open, StringBuilder sb) {
    if (left < 0 || right < 0 || open < 0) return;
    if (i == s.length) {
      if (left == 0 && right == 0 && open == 0) res.add(sb.toString());
      return;
    }
    int len = sb.length();
    if (s[i] == '(') {
      dfs(res, s, i + 1, left - 1, right, open, sb);
      dfs(res, s, i + 1, left, right, open + 1, sb.append(s[i]));
    } else if (s[i] == ')') {
      dfs(res, s, i + 1, left, right - 1, open, sb);
      dfs(res, s, i + 1, left, right, open - 1, sb.append(s[i]));
    } else dfs(res, s, i + 1, left, right, open, sb.append(s[i]));
    sb.setLength(len);
  }

}
