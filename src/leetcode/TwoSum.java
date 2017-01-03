/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class TwoSum {

  /*
  Given an array of integers, return indices of the two numbers such that they add up to a specific target.
  You may assume that each input would have exactly one solution.
  
  Example:  
  Given nums = [2, 7, 11, 15], target = 9,
  
  Because nums[0] + nums[1] = 2 + 7 = 9,
  return [0, 1].  
  */

  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0, j;; map.put(nums[i], i++))
      if (map.containsKey(j = target - nums[i])) return new int[] { map.get(j), i };
  }

  public int[] twoSumII(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; map.put(nums[i], i++));
    Arrays.sort(nums);
    for (int l = 0, r = nums.length - 1, v;;)
      if ((v = nums[l] + nums[r]) == target) return new int[] { map.get(nums[l]), map.get(nums[r]) };
      else if (v > target) r--;
      else l++;
  }

  @Test
  public void test() {
    assertArrayEquals(new int[] { 0, 1 }, twoSum(new int[] { 2, 7, 11, 15 }, 9));
    assertArrayEquals(new int[] { 0, 1 }, twoSumII(new int[] { 2, 7, 11, 15 }, 9));
  }

}
