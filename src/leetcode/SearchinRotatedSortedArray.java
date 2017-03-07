package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SearchinRotatedSortedArray {

  /*
  Suppose a sorted array is rotated at some pivot unknown to you beforehand.    
  (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).    
  You are given a target value to search. If found in the array return its index, otherwise return -1.    
  You may assume no duplicate exists in the array.   
  */

  @Test
  public void test() {
    assertEquals(3, search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 7));
    assertEquals(5, search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 1));
    assertEquals(1, search(new int[] { 1, 3 }, 3));
    assertEquals(1, search(new int[] { 3, 1 }, 1));
    assertEquals(0, search(new int[] { 0 }, 0));
  }

  public int search(int A[], int target) {
    for (int left = 0, right = A.length - 1, mid; left <= right;)
      if (A[mid = (left + right) >> 1] == target) return mid;
      else if (A[mid] < A[right]) { // right half sorted
        if (target > A[mid] && target <= A[right]) left = mid + 1;
        else right = mid - 1;
      } else { // left half sorted
        if (target >= A[left] && target < A[mid]) right = mid - 1;
        else left = mid + 1;
      }
    return -1;
  }

}
