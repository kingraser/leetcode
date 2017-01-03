/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class SearchinRotatedSortedArray {

  /*
  Suppose a sorted array is rotated at some pivot unknown to you beforehand.    
  (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).    
  You are given a target value to search. If found in the array return its index, otherwise return -1.    
  You may assume no duplicate exists in the array.   
  */

  public int search(int A[], int target) {
    int first = 0, last = A.length;
    while (first != last) {
      int mid = first + (last - first) / 2;
      if (A[mid] == target) return mid;
      if (A[first] <= A[mid]) {
        if (A[first] <= target && target < A[mid]) last = mid;
        else first = mid + 1;
      } else {
        if (A[mid] < target && target <= A[last - 1]) first = mid + 1;
        else last = mid;
      }
    }
    return -1;
  }

  @Test
  public void test() {
    assertEquals(3, search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 7));
    assertEquals(5, search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 1));
    assertEquals(1, search(new int[] { 1, 3 }, 3));
    assertEquals(1, search(new int[] { 3, 1 }, 1));
    assertEquals(0, search(new int[] { 0 }, 0));
  }

}
