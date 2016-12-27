/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class LargestRectangleinHistogram {
  /*
  Given n non-negative integers representing the histogram's bar height 
  where the width of each bar is 1, find the area of largest rectangle in the histogram.    
  
  
            6
          5 _
          _| |
         | | |  3
      2  | | |2 _
      _ 1| | |_| |
     | |_| | | | |
     | | | | | | |
  Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
            6
          5 _
          _| |
         |@|@|  3
      2  |@|@|2 _
      _ 1|@|@|_| |
     | |_|@|@| | |
     | | |@|@| | |
  The largest rectangle is shown in the shaded area, which has area = 10 unit.
  
  For example,
  Given height = [2,1,5,6,2,3],
  return 10. 
  */

  public static int largestRectangleArea(int[] height) {
    Stack<Integer> stack = new Stack<>();
    int max = 0;
    for (int i = 0, v; i < height.length + 1;)
      if (stack.empty() || height[stack.peek()] <= (v = i < height.length ? height[i] : 0)) stack.push(i++);
      else while (!stack.isEmpty() && height[stack.peek()] > v)
        max = Math.max(max, height[stack.pop()] * (stack.empty() ? i : i - stack.peek() - 1));
    return max;
  }

  @Test
  public void test() {
    assertEquals(10, largestRectangleArea(new int[] { 2, 1, 5, 6, 2, 3 }));
  }
}
