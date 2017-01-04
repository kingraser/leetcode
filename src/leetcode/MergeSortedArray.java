/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class MergeSortedArray {

  /*    
  Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
  
  Note:
  You may assume that nums1 has enough space (size that is greater or equal to m + n) 
  to hold additional elements from nums2. 
  The number of elements initialized in nums1 and nums2 are m and n respectively.
  */

  @Test
  public void test() {
    int[] A = new int[] { 1, 3, 5, 0, 0, 0 }, B = new int[] { 2, 4, 6 };
    merge(A, 3, B, 3);
    assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6 }, A);
  }

  public void merge(int A[], int m, int B[], int n) {
    int idx1 = m - 1, idx2 = n - 1, idx = m + n - 1;
    for (; idx1 >= 0 && idx2 >= 0; A[idx--] = A[idx1] >= B[idx2] ? A[idx1--] : B[idx2--]);
    for (; idx2 >= 0; A[idx--] = B[idx2--]);
  }
}
