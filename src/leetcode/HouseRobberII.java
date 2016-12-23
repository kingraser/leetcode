package leetcode;
/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */

import java.util.Arrays;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月18日<p>
//-------------------------------------------------------
public class HouseRobberII {

  /*
  Note: This is an extension of House Robber.    
  After robbing those houses on that street, 
  the thief has found himself a new place for his thievery 
  so that he will not get too much attention. 
  This time, all houses at this place are arranged in a circle. 
  That means the first house is the neighbor of the last one. 
  Meanwhile, the security system for these houses remain the same as for those in the previous street.
  
  Given a list of non-negative integers representing the amount of money of each house, 
  determine the maximum amount of money you can rob tonight without alerting the police.
  */

  //circle
  //2 cases:1 rob first to the last but on,2 rob second to last,find the max

  public int rob(int[] nums) {
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    if (nums.length == 2) return Math.max(nums[0], nums[1]);

    int[] a = Arrays.copyOf(nums, nums.length);
    a[2] += a[0];//case1
    for (int i = 3; i < a.length - 1; i++)
      a[i] += Math.max(a[i - 2], a[i - 3]);
    nums[nums.length - 3] += nums[nums.length - 1];//case2
    for (int i = nums.length - 4; i > 0; i--)
      nums[i] += Math.max(nums[i + 2], nums[i + 3]);
    return Math.max(Math.max(a[a.length - 3], a[a.length - 2]), Math.max(nums[1], nums[2]));
  }
}
