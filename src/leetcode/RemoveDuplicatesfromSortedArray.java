package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RemoveDuplicatesfromSortedArray {

  /*
  Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
  Do not allocate extra space for another array, you must do this in place with constant memory.
  For example,
  Given input array nums = [1,1,2],
  Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
  It doesn't matter what you leave beyond the new length. 
  */

  @Test
  public void test() {
    assertEquals(2, removeDuplicates(new int[] { 1, 1, 2 }));
  }

  public int removeDuplicates(int[] nums) {
    return RemoveDuplicatesfromSortedArrayII.removeDuplicates(nums, 1);
  }
}
