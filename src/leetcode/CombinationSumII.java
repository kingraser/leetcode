package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

public class CombinationSumII {

  /*
  Given a collection of candidate numbers (C) and a target number (T), 
  find all unique combinations in C where the candidate numbers sums to T.
  Each number in C may only be used once in the combination.
  
  Note:
  
  All numbers (including target) will be positive integers.
  Elements in a combination (a1, a2, … , ak) must be in non-descending order. 
  (ie, a1 ≤ a2 ≤ … ≤ ak).
  The solution set must not contain duplicate combinations.
  
  For example, given candidate set 10,1,2,7,6,1,5 and target 8,
  A solution set is:
  [1, 7]
  [1, 2, 5]
  [2, 6]
  [1, 1, 6]    
  */

  @Test
  public void test() {
    assertEquals(
        Arrays.asList(Arrays.asList(1, 1, 6), Arrays.asList(1, 2, 5), Arrays.asList(1, 7), Arrays.asList(2, 6)),
        combinationSum2(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8));
  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> result = new ArrayList<>();
    dfs(candidates, target, 0, new ArrayDeque<>(), result);
    return result;
  }

  private void dfs(int[] n, int gap, int start, Deque<Integer> deque, List<List<Integer>> result) {
    if (gap == 0) result.add(new ArrayList<>(deque));
    else for (int i = start; i < n.length && gap >= n[i]; i++) {
      if (i > start && n[i] == n[i - 1]) continue;
      deque.addLast(n[i]);
      dfs(n, gap - n[i], i + 1, deque, result);
      deque.pollLast();
    }
  }

}
