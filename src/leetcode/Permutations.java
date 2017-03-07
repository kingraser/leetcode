package leetcode;

import static leetcode.util.ArrayUtil.swap;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class Permutations {

  /*
  Given a collection of numbers, return all possible permutations.
  
  For example,
  [1,2,3] have the following permutations:
  [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1]. 
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2), Arrays.asList(2, 1, 3),
        Arrays.asList(2, 3, 1), Arrays.asList(3, 2, 1), Arrays.asList(3, 1, 2)), permute(new int[] { 1, 2, 3 }));
  }

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(result, nums, 0);
    return result;
  }

  void dfs(List<List<Integer>> result, int[] nums, int start) {
    if (start == nums.length - 1) result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    else for (int i = start; i < nums.length; i++) {
      swap(nums, start, i);
      dfs(result, nums, start + 1);
      swap(nums, i, start);
    }
  }
}
