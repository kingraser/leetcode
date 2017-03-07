package leetcode;

import static leetcode.util.ArrayUtil.reverse;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class RotateArray {

  /*    
  Rotate an array of n elements to the right by k steps.  
  For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. 
  */

  public void rotate(int[] nums, int k) {
    k %= nums.length;
    reverse(nums, 0, nums.length - 1 - k);
    reverse(nums, nums.length - k, nums.length - 1);
    reverse(nums, 0, nums.length - 1);
  }

  @Test
  public void test() {
    int[] input = new int[] { 1, 2, 3, 4, 5, 6, 7 };
    rotate(input, 3);
    assertArrayEquals(new int[] { 5, 6, 7, 1, 2, 3, 4 }, input);
  }

}
