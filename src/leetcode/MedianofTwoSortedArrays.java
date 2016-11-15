/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class MedianofTwoSortedArrays {

  /*
  There are two sorted arrays nums1 and nums2 of size m and n respectively.  
  Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
  
  Example 1:
  
  nums1 = [1, 3]
  nums2 = [2]
  
  The median is 2.0
  
  Example 2:
  
  nums1 = [1, 2]
  nums2 = [3, 4]
  
  The median is (2 + 3)/2 = 2.5
  */

  public double findMedianSortedArrays(int[] A, int[] B) {
    if (A.length > B.length) return findMedianSortedArrays(B, A);//make sure A is shorter than B
    int half = (A.length + B.length - 1) >> 1, left = 0, right = Math.min(half, A.length);
    for (int mid; left < right;)
      if (A[mid = (left + right) >> 1] < B[half - mid]) left = mid + 1;
      else right = mid;
    double a = left == 0 ? B[half - left] : Math.max(A[left - 1], B[half - left]);
    if (((A.length + B.length) & 1) == 1) return a;//odd
    double b = left == A.length ? B[half - left + 1]
        : half - left + 1 < B.length ? Math.min(A[left], B[half - left + 1]) : A[left];
    return (a + b) / 2;
  }

  @Test
  public void test() {
    Assert.assertEquals(1d, findMedianSortedArrays(new int[] { 1 }, new int[] { 1 }), 0);
  }
}
