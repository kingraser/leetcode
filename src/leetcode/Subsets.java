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

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class Subsets {
  /*
  Given a set of distinct integers, nums, return all possible subsets.
  
  Note:
  
  Elements in a subset must be in non-descending order.
  The solution set must not contain duplicate subsets.
  
  For example,
  If nums = [1,2,3], a solution is:
  
  [
    [3],
    [1],
    [2],
    [1,2,3],
    [1,3],
    [2,3],
    [1,2],
    []
  ]    
  */

  //二进制法
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>(1 << nums.length);
    Arrays.sort(nums);
    for (int i = 0, size = 1 << nums.length; i < size; i++) {
      List<Integer> list = new ArrayList<>(nums.length);
      for (int j = 0, l = i; l > 0; l >>= 1, j++)
        if ((l & 1) == 1) list.add(nums[j]);
      result.add(list);
    }
    return result;
  }

  public List<List<Integer>> subsetsII(int[] nums) {
    List<List<Integer>> result = new ArrayList<>(1 << nums.length);
    dfs(result, new ArrayDeque<>(), 0, nums);
    return result;
  }

  private void dfs(List<List<Integer>> result, Deque<Integer> deque, int start, int[] nums) {
    result.add(new ArrayList<>(deque));
    for (int i = start; i < nums.length; i++) {
      deque.addLast(nums[i]);
      dfs(result, deque, i + 1, nums);
      deque.pollLast();
    }
  }

  @Test
  public void test() {
    Set<List<Integer>> expected = new HashSet<>(Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3),
        Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(2, 3), Arrays.asList(1, 2, 3), Arrays.asList()));
    assertEquals(expected, new HashSet<>(subsets(new int[] { 1, 2, 3 })));
    assertEquals(expected, new HashSet<>(subsetsII(new int[] { 1, 2, 3 })));
  }
}
