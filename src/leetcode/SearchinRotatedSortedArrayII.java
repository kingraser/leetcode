package leetcode;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class SearchinRotatedSortedArrayII {

  /*
  Follow up for "Search in Rotated Sorted Array": What if duplicates are allowed?
  Would this affect the run-time complexity? How and why?
  Write a function to determine if a given target is in the array.
  */

  @Test
  public void test() {
    assertTrue(searchZero(new int[] { 1, 3, 1, 1, 1 }, 3));
    assertTrue(search(new int[] { 1, 3, 1, 1, 1 }, 3));
  }

  public boolean searchZero(int[] A, int target) {
    return Arrays.stream(A).anyMatch(i -> i == target);
  }

  public boolean search(int A[], int target) {
    for (int left = 0, right = A.length - 1, mid; left <= right;)
      if (A[mid = (left + right) >> 1] == target) return true;
      else if (A[mid] > A[right]) {
        if (A[mid] > target && A[left] <= target) right = mid;
        else left = mid + 1;
      } else if (A[mid] < A[right]) {
        if (A[mid] < target && A[right] >= target) left = mid + 1;
        else right = mid;
      } else right--;
    return false;
  }
}
