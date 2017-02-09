package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class IncreasingSubsequences {

  /*
  Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .
  
  Example:
  
  Input: [4, 6, 7, 7]
  Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
  
  Note:
  
    The length of the given array will not exceed 15.
    The range of integer in the given array is [-100,100].
    The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
  */

  @Test
  public void test() {
    assertEquals(Stream
        .of(Arrays.asList(4, 6), Arrays.asList(4, 6, 7), Arrays.asList(4, 6, 7, 7), Arrays.asList(4, 7),
            Arrays.asList(4, 7, 7), Arrays.asList(6, 7), Arrays.asList(6, 7, 7), Arrays.asList(7, 7))
        .collect(Collectors.toSet()), new HashSet<>(findSubsequences(new int[] { 4, 6, 7, 7 })));
  }

  public List<List<Integer>> findSubsequences(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(result, new ArrayDeque<>(), 0, nums);
    return result;
  }

  public void dfs(List<List<Integer>> result, Deque<Integer> deque, int start, int[] nums) {
    if (deque.size() > 1) result.add(new ArrayList<>(deque));
    Set<Integer> set = new HashSet<>();
    for (int i = start; i < nums.length; i++)
      if ((deque.isEmpty() || deque.peekLast() <= nums[i]) && set.add(nums[i])) {
        deque.addLast(nums[i]);
        dfs(result, deque, i + 1, nums);
        deque.removeLast();
      }
  }
}