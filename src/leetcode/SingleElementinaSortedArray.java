package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SingleElementinaSortedArray {

  /*
  Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. 
  Find this single element that appears only once.
  
  Example 1:  
  Input: [1,1,2,3,3,4,4,8,8]
  Output: 2
  
  Example 2:  
  Input: [3,3,7,7,10,11,11]
  Output: 10
  
  Note: Your solution should run in O(log n) time and O(1) space. 
  */

  @Test
  public void test() {
    assertEquals(2, singleNonDuplicate(new int[] { 1, 1, 2 }));
    assertEquals(2, singleNonDuplicate(new int[] { 1, 1, 2, 3, 3, 4, 4, 8, 8 }));
    assertEquals(10, singleNonDuplicate(new int[] { 3, 3, 7, 7, 10, 11, 11 }));
  }

  public int singleNonDuplicate(int[] nums) {
    int left = 0, right = nums.length >> 1, middle;
    while (left < right)
      if (nums[(middle = (left + right) >> 1) << 1] != nums[(middle << 1) + 1]) right = middle;
      else left = middle + 1;
    return nums[left << 1];
  }
}
