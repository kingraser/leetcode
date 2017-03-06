package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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

  @Test
  public void test() {
    assertEquals(2d, findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2 }), 0);
    assertEquals(2.5d, findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 }), 0);

    assertEquals(2d, findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 1, 2, 3 }), 0);
    assertEquals(1d, findMedianSortedArrays(new int[] { 1 }, new int[] { 1 }), 0);
  }

  public double findMedianSortedArrays(int[] A, int[] B) {
    if (A.length > B.length) return findMedianSortedArrays(B, A); // make sure A is shorter than B
    int lA = A.length, lB = B.length, half = (lA + lB - 1) >> 1, left = 0, right = Math.min(half, lA);
    for (int mid; left < right;)
      if (A[mid = (left + right) >> 1] < B[half - mid]) left = mid + 1;
      else right = mid;
    double a = left == 0 ? B[half] : Math.max(A[left - 1], B[half - left]);
    if (((lA + lB) & 1) == 1) return a; // odd
    double b = left == lA ? B[half - lA + 1] : half - left + 1 < lB ? Math.min(A[left], B[half - left + 1]) : A[left];
    return (a + b) / 2;
  }

}
