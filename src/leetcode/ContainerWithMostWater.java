/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月9日<p>
//-------------------------------------------------------
public class ContainerWithMostWater {
  /*
  Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
  n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
  Find two lines, which together with x-axis forms a container, such that the container contains the most water.
  
  Note: You may not slant the container. 
  */

  @Test
  public void test() {
    assertEquals(9, maxArea(new int[] { 1, 3, 4, 6, 5, 2 }));
  }

  public int maxArea(int[] height) {
    int max = Integer.MIN_VALUE;
    for (int l = 0, r = height.length - 1, lastL = max, lastR = max; l < r;)
      if (height[l] < lastL) l++;
      else if (height[r] < lastR) r--;
      else {
        max = Math.max(max, Math.min(lastL = height[l], lastR = height[r]) * (r - l));
        if (lastL < lastR) l++;
        else r--;
      }
    return max;
  }

}
