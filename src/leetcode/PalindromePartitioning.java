/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class PalindromePartitioning {
  /*
  Given a string s, partition s such that every substring of the partition is a palindrome.    
  Return all possible palindrome partitioning of s.    
  For example, given s = "aab",
  Return    
    [
      ["aa","b"],
      ["a","a","b"]
    ]
  */

  /*
          在每一步都可以判断中间结果是否为合法结果,用回溯法。
          一个长度为 n 的字符串,有 n − 1 个地方可以砍断,每个地方可断可不断,因此复杂度为O(2^n−1)
  */

  @Test
  public void test() {
    List<List<String>> expected = Arrays.asList(Arrays.asList("a", "a", "b"), Arrays.asList("aa", "b"));
    assertEquals(new HashSet<>(expected), new HashSet<>(partition("aab")));
  }

  public List<List<String>> partition(String s) {
    return partition(s, getPalindromeMap(s.toCharArray()));
  }

  public List<List<String>> partition(String s, boolean[][] map) {
    if (s.length() == 0) return Arrays.asList(new ArrayList<>());
    List<List<String>> result = new ArrayList<>();
    for (int i = s.length() - 1; i >= 0; i--)
      if (isPalindrome(i, s.length() - 1, map)) for (List<String> l : partition(s.substring(0, i))) {
        l.add(s.substring(i));
        result.add(l);
      }
    return result;
  }

  private boolean isPalindrome(int i, int j, boolean[][] map) {
    return i < j ? map[i][j] : true;
  }

  private boolean[][] getPalindromeMap(char[] s) {
    boolean[][] map = new boolean[s.length][s.length];
    for (int i = 0; i < s.length; i++)
      for (int j = 1; i + j < s.length; j++)
        map[i][i + j] = s[i] == s[i + j] && isPalindrome(i + 1, i + j - 1, map);
    return map;
  }
}
