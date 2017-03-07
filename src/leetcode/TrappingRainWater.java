package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TrappingRainWater {

  /*
  Given n non-negative integers representing an elevation map where the width of each bar is 1, 
  compute how much water it is able to trap after raining.
  
  For example,
  Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6. 
  */

  @Test
  public void test() {
    assertEquals(6, trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
  }

  public int trap(int[] height) {
    if (height == null || height.length < 3) return 0;
    int[] leftMostHeight = new int[height.length], rightMostHeight = new int[height.length];
    for (int i = 1, n = height.length; i < n; i++) {
      leftMostHeight[i] = Math.max(leftMostHeight[i - 1], height[i - 1]);
      rightMostHeight[n - 1 - i] = Math.max(rightMostHeight[n - i], height[n - i]);
    }
    int water = 0;
    for (int i = 0; i < height.length; i++) {
      int high = Math.min(leftMostHeight[i], rightMostHeight[i]);
      if (high > height[i]) water += high - height[i];
    }
    return water;
  }
}
