/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.Random;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年8月30日;
//-------------------------------------------------------
public class ShuffleanArray {
  /*
  Shuffle a set of numbers without duplicates.
  
  Example:
  
  // Init an array with set 1, 2, and 3.
  int[] nums = {1,2,3};
  Solution solution = new Solution(nums);
  
  // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
  solution.shuffle();
  
  // Resets the array back to its original configuration [1,2,3].
  solution.reset();
  
  // Returns the random shuffling of array [1,2,3].
  solution.shuffle(); 
  */

  public class Solution {

    private int[] nums, shuffles;

    private Random random = new Random();

    public Solution(int[] nums) {
      this.shuffles = Arrays.copyOf(nums, nums.length);
      this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
      return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
      for (int i = 0; i < shuffles.length; swap(shuffles, i, random.nextInt(++i)));
      return shuffles;
    }

  }

  public static final void swap(int[] a, int i, int j) {
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
  }
}
