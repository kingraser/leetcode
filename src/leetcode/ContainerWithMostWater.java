package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
    for (int left = 0, right = height.length - 1, lastL = max, lastR = max; left < right;)
      if (height[left] < lastL) left++;
      else if (height[right] < lastR) right--;
      else {
        max = Math.max(max, Math.min(lastL = height[left], lastR = height[right]) * (right - left));
        if (lastL < lastR) left++;
        else right--;
      }
    return max;
  }

}
