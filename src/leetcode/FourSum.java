package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class FourSum {

  /*
  Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
  Find all unique quadruplets in the array which gives the sum of target.  
  Note: The solution set must not contain duplicate quadruplets.
  
  For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
  
  A solution set is:
  [
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
  ]  
  */

  @Test
  public void test() {
    List<List<Integer>> expected = Arrays.asList(Arrays.asList(-2, -1, 1, 2), Arrays.asList(-2, 0, 0, 2),
        Arrays.asList(-1, 0, 0, 1));
    assertEquals(expected, fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, 0));

    expected = Arrays.asList(Arrays.asList(0, 0, 0, 0));
    assertEquals(expected, fourSum(new int[] { 0, 0, 0, 0, 0, 0 }, 0));
  }

  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    return kSum(nums, target, 4, 0);
  }

  /**
   * @param nums sorted array
   * @param target sum
   * @param k k-sum
   * @param start start index
   * @return list of k pairs
   */
  public static final List<List<Integer>> kSum(int[] nums, int target, int k, int start) {
    if (k == 2) return TwoSum(nums, target, start);
    List<List<Integer>> result = new ArrayList<>();
    for (int i = start; i < nums.length - k; i++)
      if (i > start && nums[i - 1] == nums[i]) continue;
      else for (List<Integer> list : kSum(nums, target - nums[i], k - 1, i + 1)) {
        list.add(0, nums[i]);
        result.add(list);
      }
    return result;
  }

  private static List<List<Integer>> TwoSum(int[] nums, int target, int start) {
    List<List<Integer>> result = new ArrayList<>();
    for (int left = start, right = nums.length - 1, value; left < right;)
      if (left > start && nums[left - 1] == nums[left]) left++;
      else if ((value = nums[left] + nums[right]) == target) result.add(Lists.newArrayList(nums[left++], nums[right]));
      else if (value < target) left++;
      else right--;
    return result;
  }

}
