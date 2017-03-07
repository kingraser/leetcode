package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class SearchInsertPosition {

  /*
  Given a sorted array and a target value, return the index if the target is found. 
  If not, return the index where it would be if it were inserted in order.  
  You may assume no duplicates in the array.
  
  Here are few examples.
  [1,3,5,6], 5 → 2
  [1,3,5,6], 2 → 1
  [1,3,5,6], 7 → 4
  [1,3,5,6], 0 → 0 
  */

  @Test
  public void test() {
    int[] A = new int[] { 1, 3, 5, 6 };
    assertEquals(2, searchInsert(A, 5));
    assertEquals(1, searchInsert(A, 2));
    assertEquals(4, searchInsert(A, 7));
    assertEquals(0, searchInsert(A, 0));
    
    assertEquals(2, searchInsertZero(A, 5));
    assertEquals(1, searchInsertZero(A, 2));
    assertEquals(4, searchInsertZero(A, 7));
    assertEquals(0, searchInsertZero(A, 0));
  }

  public int searchInsertZero(int[] A, int target) {
    int idx = Arrays.binarySearch(A, target);
    return idx >= 0 ? idx : ~idx;
  }

  public int searchInsert(int[] A, int target) {
    int left = 0;
    for (int right = A.length, mid; left < right;)
      if (target > A[mid = (left + right) >> 1]) left = mid + 1;
      else right = mid;
    return left;
  }

}
