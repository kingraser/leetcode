package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SearchforaRange {

  /*
  Given a sorted array of integers, find the starting and ending position of a given target value.    
  Your algorithm's runtime complexity must be in the order of O(log n).    
  If the target is not found in the array, return [-1, -1].    
  For example,
  Given [5, 7, 7, 8, 8, 10] and target value 8,
  return [3, 4]. 
  */

  @Test
  public void test() {
    int[] a = { 5, 7, 7, 8, 8, 10 };
    assertArrayEquals(new int[] { 3, 4 }, searchRange(a, 8));
  }

  public int[] searchRange(int[] nums, int target) {
    int start = firstGreaterEqual(nums, target);
    if (start == nums.length || nums[start] != target) return new int[] { -1, -1 };
    return new int[] { start, firstGreaterEqual(nums, target + 1) - 1 };
  }

  public int firstGreaterEqual(int[] A, int target) {
    int left = 0, right = A.length, mid;
    while (left < right)
      if (A[mid = (left + right) >> 1] < target) left = mid + 1;
      else right = mid;
    return left;
  }

}
