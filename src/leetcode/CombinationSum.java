package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

public class CombinationSum {

  /*
  Given a set of candidate numbers (C) and a target number (T), 
  find all unique combinations in C where the candidate numbers sums to T.
  The same repeated number may be chosen from C unlimited number of times.
  
  Note:  
      All numbers (including target) will be positive integers.
      Elements in a combination (a1, a2, … , ak) must be in non-descending order. 
      (ie, a1 ≤ a2 ≤ … ≤ ak).
      The solution set must not contain duplicate combinations.
  
  For example, given candidate set 2,3,6,7 and target 7,
  A solution set is:
  [7]
  [2, 2, 3]    
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList(2, 2, 3), Arrays.asList(7)), combinationSum(new int[] { 2, 3, 6, 7 }, 7));
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> result = new ArrayList<>();
    dfs(candidates, target, 0, new ArrayDeque<>(target / candidates[0]), result);
    return result;
  }

  private void dfs(int[] n, int gap, int start, Deque<Integer> deque, List<List<Integer>> result) {
    if (gap == 0) result.add(new ArrayList<>(deque));
    else for (int i = start; i < n.length && gap >= n[i]; i++) {
      deque.addLast(n[i]);
      dfs(n, gap - n[i], i, deque, result);
      deque.pollLast();
    }
  }

}
