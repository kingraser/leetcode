package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

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
    assertEquals(Arrays.asList(Arrays.asList(), Arrays.asList(1), Arrays.asList(1, 2), Arrays.asList(1, 2, 2),
        Arrays.asList(2), Arrays.asList(2, 2)), subsetsWithDup(new int[] { 1, 2, 2 }));
  }

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
