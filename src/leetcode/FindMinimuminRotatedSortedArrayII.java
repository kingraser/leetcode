/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class FindMinimuminRotatedSortedArrayII {

  /*
  Follow up for "Find Minimum in Rotated Sorted Array":
  What if duplicates are allowed?    
  Would this affect the run-time complexity? How and why?    
  Suppose a sorted array is rotated at some pivot unknown to you beforehand.    
  (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
  Find the minimum element.    
  The array may contain duplicates.
  */

  @Test
  public void test() {
    assertEquals(0, findMin(new int[] { 4, 5, 6, 7, 0, 0, 1, 1, 1, 2 }));
  }

  public int findMin(int[] A) {
    int start = 0, end = A.length - 1, mid;
    while (start < end && A[end] <= A[start]) {
      if (A[end] == A[start]) start++;
      else if (A[mid = (start + end) >> 1] < A[start]) end = mid;
      else start = mid + 1;
    }
    return A[start];
  }
}
