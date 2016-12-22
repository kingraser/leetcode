/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class FindPeakElement {

  /*
  A peak element is an element that is greater than its neighbors.
  
  Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
  
  The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
  
  You may imagine that num[-1] = num[n] = -∞.
  
  For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
  
  Note:
  
  Your solution should be in logarithmic complexity.  
  */

  @Test
  public void test() {
    assertEquals(2, findPeakElement(new int[] { 1, 2, 3, 1 }));
  }

  public int findPeakElement(int[] num) {
    int l = 0, r = num.length - 1, mid;
    while (l < r)
      if (num[mid = (l + r) >> 1] < num[mid + 1]) l = mid + 1;
      else r = mid;
    return l;
  }
}
