package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

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

  @Test
  public void test() {
    assertEquals(
        Arrays.asList(Arrays.asList(), Arrays.asList(1), Arrays.asList(1, 2), Arrays.asList(1, 2, 3),
            Arrays.asList(1, 3), Arrays.asList(2), Arrays.asList(2, 3), Arrays.asList(3)),
        subsets(new int[] { 1, 2, 3 }));
  }

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
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

}
