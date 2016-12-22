/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class GenerateParentheses {

  /*
  For example, given n = 3, a solution set is:
  "((()))", "(()())", "(())()", "()(())", "()()()"
  */

  @Test
  public void test() {
    List<String> expected = Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()");
    assertEquals(new HashSet<>(expected), new HashSet<>(generateParenthesis(3)));
  }

  public List<String> generateParenthesis(int n) {
    List<String> list = new ArrayList<String>();
    dfs(list, new char[n << 1], n, n);
    return list;
  }

  public void dfs(List<String> list, char[] A, int left, int right) {
    if (left == 0 && right == 0 && A.length > 0) {
      list.add(new String(A));
      return;
    }
    if (left > 0) {
      A[A.length - left - right] = '(';
      dfs(list, A, left - 1, right);
    }
    if (left < right) {
      A[A.length - left - right] = ')';
      dfs(list, A, left, right - 1);
    }
  }
}
