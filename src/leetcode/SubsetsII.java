/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class SubsetsII {
  /*
  Given a collection of integers that might contain duplicates, nums, return all possible subsets.    
  Note:    
  Elements in a subset must be in non-descending order.
  The solution set must not contain duplicate subsets.
  
  For example,
  If nums = [1,2,2], a solution is:
  
  [
    [2],
    [1],
    [1,2,2],
    [2,2],
    [1,2],
    []
  ]    
  */

  @Test
  public void test() {
    Set<List<Integer>> expected = new HashSet<>(Arrays.asList(Arrays.asList(2), Arrays.asList(1),
        Arrays.asList(1, 2, 2), Arrays.asList(2, 2), Arrays.asList(1, 2), Arrays.asList()));
    assertEquals(expected, new HashSet<>(subsetsWithDup(new int[] { 1, 2, 2 })));
  }

  //dfs
  public List<List<Integer>> subsetsWithDup(int[] A) {
    Arrays.sort(A);
    List<List<Integer>> result = new ArrayList<>();
    dfs(A, 0, new ArrayDeque<>(), result);
    return result;
  }

  private void dfs(int[] A, int start, Deque<Integer> path, List<List<Integer>> result) {
    result.add(new ArrayList<>(path));
    for (int i = start; i < A.length; i++) {
      if (i > start && A[i] == A[i - 1]) continue;
      path.addLast(A[i]);
      dfs(A, i + 1, path, result);
      path.pollLast();
    }
  }
}
