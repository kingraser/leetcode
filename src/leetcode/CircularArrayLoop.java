package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CircularArrayLoop {

  /*
  You are given an array of positive and negative integers. 
  If a number n at an index is positive, then move forward n steps. 
  Conversely, if it's negative (-n), move backward n steps. 
  Assume the first element of the array is forward next to the last element, 
  and the last element is backward next to the first element. 
  Determine if there is a loop in this array. 
  A loop starts and ends at a particular index with more than 1 element along the loop. 
  The loop must be "forward" or "backward'.
  
  Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.  
  Example 2: Given the array [-1, 2], there is no loop.
  
  Note: The given array is guaranteed to contain no element "0".
  
  Can you do it in O(n) time complexity and O(1) space complexity?  
  */

  @Test
  public void test() {
    assertFalse(circularArrayLoop(new int[] { -2, 1, -1, -2, -2 }));
    assertTrue(circularArrayLoop(new int[] { 2, -1, 1, 2, 2 }));
    assertFalse(circularArrayLoop(new int[] { -1, 2 }));
  }

  private int next(int now, int[] A) {
    return ((now + A[now]) % A.length + A.length) % A.length;
  }

  private boolean same(int flag, int b) {
    return b == 0 ? false : (flag ^ (b >>> 31)) == 0;
  }

  public boolean circularArrayLoop(int[] nums) {
    if (nums == null || nums.length < 2) return false;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) continue;
      for (int flag = nums[i] >>> 31, slow = i, fast = next(slow, nums); same(flag, nums[slow])
          && same(flag, nums[fast]); slow = next(slow, nums), fast = next(next(fast, nums), nums))
        if (slow == fast) if (slow == next(slow, nums)) break;
        else return true;
      for (int flag = nums[i] >>> 31, j = i, next; same(flag, nums[j]); next = next(j, nums), nums[j] = 0, j = next);
    }
    return false;
  }

}
