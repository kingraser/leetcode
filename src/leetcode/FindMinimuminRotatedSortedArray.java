package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindMinimuminRotatedSortedArray {

  /*
  Suppose a sorted array is rotated at some pivot unknown to you beforehand.    
  (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).    
  Find the minimum element.    
  You may assume no duplicate exists in the array.
  */

  @Test
  public void test() {
    assertEquals(0, findMin(new int[] { 4, 5, 6, 7, 0, 1, 2 }));
  }

  public int findMin(int[] A) {
    int start = 0, end = A.length - 1, mid;
    while (start < end && A[end] <= A[start])
      if (A[mid = (start + end) >> 1] < A[start]) end = mid;
      else start = mid + 1;
    return A[start];
  }
}
