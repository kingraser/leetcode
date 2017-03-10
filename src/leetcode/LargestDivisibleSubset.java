package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

public class LargestDivisibleSubset {

  /*
  Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.   
  If there are multiple solutions, return any subset is fine.
  
  Example 1:    
  nums: [1,2,3]    
  Result: [1,2] (of course, [1,3] will also be ok)
  
  Example 2:    
  nums: [1,2,4,8]    
  Result: [1,2,4,8]
  */

  @Test
  public void test() {
    List<Integer> actual = Arrays.asList(1, 2);
    assertEquals(new HashSet<>(actual), new HashSet<>(largestDivisibleSubset(new int[] { 1, 2, 3 })));
    actual = Arrays.asList(1, 2, 4, 8);
    assertEquals(new HashSet<>(actual), new HashSet<>(largestDivisibleSubset(new int[] { 1, 2, 4, 8 })));
    actual = Arrays.asList(1, 2, 4, 8, 72);
    assertEquals(new HashSet<>(actual), new HashSet<>(largestDivisibleSubset(new int[] { 1, 2, 4, 8, 9, 72 })));
  }

  public List<Integer> largestDivisibleSubset(int[] nums) {
    List<Integer> result = new ArrayList<>();
    if (nums == null || nums.length == 0) return result;
    Arrays.sort(nums);
    int[] count = new int[nums.length], parents = new int[nums.length];
    int maxIdx = 0;
    for (int i = 0, j; i < nums.length; maxIdx = count[i] > count[maxIdx] ? i : maxIdx, i++)
      for (j = 0, count[i] = 1, parents[i] = -1; j < i; j++)
        if (nums[i] % nums[j] == 0 && count[j] + 1 > count[i]) {
          count[i] = count[j] + 1;
          parents[i] = j;
        }
    for (; maxIdx != -1; maxIdx = parents[maxIdx])
      result.add(nums[maxIdx]);
    return result;
  }

}
