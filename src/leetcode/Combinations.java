/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class Combinations {
  /*
  Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
  
  For example,
  If n = 4 and k = 2, a solution is:
  
  [
    [2,4],
    [3,4],
    [2,3],
    [1,2],
    [1,3],
    [1,4],
  ]
  */

  /*
  1
  For example,[1,4] can be represented as 1001,[2,3] can be represented as [0110],1 represents element chosen.
  When n=4,10000(five bits)can cover all cases.
  1.Use O(1) Integer.bitCount to get 1 bit count,omit not k ones.
  2.get 1 bits
          
  2
  dfs get all cases,get k cases.
  */

  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0, size = (1 << n); i < size; i++) {
      if (Integer.bitCount(i) != k) continue;
      List<Integer> list = new ArrayList<>(k);
      for (int j = 1, l = i; l > 0; l >>= 1, j++)
        if ((l & 1) == 1) list.add(j);
      result.add(list);
    }
    return result;
  }

  public List<List<Integer>> combineII(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    if (k >= 0 && k <= n) dfs(result, new ArrayDeque<>(), n, k, 1);
    return result;
  }

  private void dfs(List<List<Integer>> result, Deque<Integer> deque, int n, int k, int start) {
    if (deque.size() == k) result.add(new ArrayList<>(deque));
    else for (int i = start; n - i + 1 >= k - deque.size(); i++) {
      deque.addLast(i);
      dfs(result, deque, n, k, i + 1);
      deque.pollLast();
    }
  }

  @Test
  public void test() {
    Set<List<Integer>> expected = Stream.of(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(1, 4),
        Arrays.asList(2, 3), Arrays.asList(2, 4), Arrays.asList(3, 4)).collect(Collectors.toSet());
    assertEquals(expected, new HashSet<>(combine(4, 2)));
    assertEquals(expected, new HashSet<>(combineII(4, 2)));
  }
}
