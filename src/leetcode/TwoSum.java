package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Test;

public class TwoSum {

  /*
  Given an array of integers, return indices of the two numbers such that they add up to a specific target.
  You may assume that each input would have exactly one solution.
  
  Example:  
  Given nums = [2, 7, 11, 15], target = 9,
  
  Because nums[0] + nums[1] = 2 + 7 = 9,
  return [0, 1].  
  */

  @Test
  public void test() {
    assertArrayEquals(new int[] { 0, 1 }, twoSum(new int[] { 2, 7, 11, 15 }, 9));
    assertArrayEquals(new int[] { 0, 1 }, twoSumII(new int[] { 2, 7, 11, 15 }, 9));
  }

  public static int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    IntStream.range(0, nums.length).forEach(i -> map.put(nums[i], i)); // record the relationship of value and index
    Arrays.sort(nums);
    for (int left = 0, right = nums.length - 1, value;;)
      if ((value = nums[left] + nums[right]) == target) return new int[] { map.get(nums[left]), map.get(nums[right]) };
      else if (value > target) right--;
      else left++;
  }

  public int[] twoSumII(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int idx = 0, difference;; map.put(nums[idx], idx++))
      if (map.containsKey(difference = target - nums[idx])) return new int[] { map.get(difference), idx };
  }

}
