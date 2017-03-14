package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

public class FactorCombinations {

  /*
  Numbers can be regarded as product of its factors. For example,  
  8 = 2 x 2 x 2;
  = 2 x 4.  
  Write a function that takes an integer n and return all possible combinations of its factors.
  
  Note:   
    Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
    You may assume that n is always positive.
    Factors should be greater than 1 and less than n.
    
  Examples: 
  input: 1
  output: 
  []
  
  input: 37
  output:   
  []
  
  input: 12
  output:  
  [
  [2, 6],
  [2, 2, 3],
  [3, 4]
  ]
  
  input: 32
  output:  
  [
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
  ]
  */

  @Test
  public void test() {
    assertEquals(new ArrayList<>(), getFactors(1));
    assertEquals(new ArrayList<>(), getFactors(37));
    assertEquals(Arrays.asList(Arrays.asList(2, 2, 3), Arrays.asList(2, 6), Arrays.asList(3, 4)), getFactors(12));
    assertEquals(Arrays.asList(Arrays.asList(2, 2, 2, 2, 2), Arrays.asList(2, 2, 2, 4), Arrays.asList(2, 2, 8),
        Arrays.asList(2, 4, 4), Arrays.asList(2, 16), Arrays.asList(4, 8)), getFactors(32));
  }

  public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(result, new ArrayDeque<>(), n, 2);
    return result;
  }

  public void dfs(List<List<Integer>> result, Deque<Integer> item, int n, int start) {
    if (n <= 1) {
      if (item.size() > 1) result.add(new ArrayList<>(item));
      return;
    }
    for (int i = start; i <= n; i++)
      if (n % i == 0) {
        item.addLast(i);
        dfs(result, item, n / i, i);
        item.pollLast();
      }
  }
}