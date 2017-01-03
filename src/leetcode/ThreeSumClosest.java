/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class ThreeSumClosest {

  /*
  3Sum Closest
          给一int数组及target,求三下标使和最接近target.有且只有一组解
  map法不适用了,转为2sum+水位法
  */

  public int threeSumClosest(int[] nums, int target) {
    Integer result = null;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++)
      for (int l = i + 1, r = nums.length - 1, sum; l < r;) {
        if ((sum = nums[i] + nums[l] + nums[r]) == target) return target;
        if (result == null || Math.abs(result - target) > Math.abs(sum - target)) result = sum;
        if (sum < target) l++;
        else r--;
      }
    return result;
  }

  @Test
  public void test() {
    assertEquals(2, threeSumClosest(new int[] { -1, 2, 1, -4 }, 1));
  }

}
