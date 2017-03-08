package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

public class Combinations {

  /*
  Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.  
  For example, If n = 4 and k = 2, a solution is:
  
  [
    [2,4],
    [3,4],
    [2,3],
    [1,2],
    [1,3],
    [1,4],
  ]
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(1, 4), Arrays.asList(2, 3),
        Arrays.asList(2, 4), Arrays.asList(3, 4)), combine(4, 2));
  }

  public List<List<Integer>> combine(int n, int k) {
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

}
