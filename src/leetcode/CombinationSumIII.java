package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

public class CombinationSumIII {

  /*
  Find all possible combinations of k numbers that add up to a number n, 
  given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
  Ensure that numbers within the set are sorted in ascending order.    
  Example 1:    
  Input: k = 3, n = 7
  Output:    
  [[1,2,4]] 
  Example 2:    
  Input: k = 3, n = 9
  Output:    
  [[1,2,6], [1,3,5], [2,3,4]]    
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList(1, 2, 4)), combinationSum3(3, 7));
    assertEquals(Arrays.asList(Arrays.asList(1, 2, 6), Arrays.asList(1, 3, 5), Arrays.asList(2, 3, 4)),
        combinationSum3(3, 9));
  }

  private static final int end = 9;

  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> res = new ArrayList<>();
    dfs(res, new ArrayDeque<>(), n, k, 1);
    return res;
  }

  private void dfs(List<List<Integer>> r, Deque<Integer> deque, int sum, int k, int start) {
    if (k == 0 && sum == 0) r.add(new ArrayList<>(deque));
    else while (isPossible(sum, k, start)) {
      deque.addLast(start);
      dfs(r, deque, sum - start, k - 1, ++start);
      deque.pollLast();
    }
  }

  //case1 left elements at least be k
  //case2 possible min <= target
  //case3 possible max >= target
  private boolean isPossible(int target, int k, int start) {
    return start + k - 1 <= end && (k - 1 + (start << 1)) * k <= (target <<= 1) && ((end << 1) + 1 - k) * k >= target;
  }

}
