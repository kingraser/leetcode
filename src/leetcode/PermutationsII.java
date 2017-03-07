package leetcode;

import static leetcode.util.ArrayUtil.swap;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class PermutationsII {

  /*
  Given a collection of numbers that might contain duplicates, 
  return all possible unique permutations.
  
  For example,
  [1,1,2] have the following unique permutations:
  [1,1,2], [1,2,1], and [2,1,1]. 
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList(1, 1, 2), Arrays.asList(1, 2, 1), Arrays.asList(2, 1, 1)),
        permuteUnique(new int[] { 1, 1, 2 }));

    assertEquals(
        Arrays.asList(Arrays.asList(2, 1, 2, 1), Arrays.asList(2, 1, 1, 2), Arrays.asList(2, 2, 1, 1),
            Arrays.asList(1, 2, 2, 1), Arrays.asList(1, 2, 1, 2), Arrays.asList(1, 1, 2, 2)),
        permuteUnique(new int[] { 2, 1, 2, 1 }));
  }

  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(result, nums, 0);
    return result;
  }

  private void dfs(List<List<Integer>> result, int[] nums, int idx) {
    if (idx == nums.length - 1) {
      result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
      return;
    }
    Set<Integer> set = new HashSet<>();
    for (int i = idx; i < nums.length; i++) {
      if (!set.add(nums[i])) continue;
      swap(nums, idx, i);
      dfs(result, nums, idx + 1);
      swap(nums, i, idx);
    }
  }

}
