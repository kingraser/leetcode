package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindtheDuplicateNumber {

  /*
  Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
  prove that at least one duplicate number must exist. Assume that there is only one duplicate number, 
  find the duplicate one.
  
  Note:    
  You must not modify the array (assume the array is read only).
  You must use only constant, O(1) extra space.
  Your runtime complexity should be less than O(n^2).
  There is only one duplicate number in the array, but it could be repeated more than once.
  */

  @Test
  public void test() {
    assertEquals(1, findDuplicate(new int[] { 1, 2, 3, 4, 5, 6, 7, 1 }));
  }

  public int findDuplicate(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      if (nums[0] == nums[nums[0]]) return nums[0];
      int temp = nums[nums[0]];
      nums[nums[0]] = nums[0];
      nums[0] = temp;
    }
    return 0;
  }
}
