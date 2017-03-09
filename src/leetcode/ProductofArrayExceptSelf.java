package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ProductofArrayExceptSelf {

  /*
  Given an array of n integers where n > 1, nums, 
  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].    
  Solve it without division and in O(n).
  For example, given [1,2,3,4], return [24,12,8,6]. 
  */

  public int[] productExceptSelf(int[] nums) {
    int[] res = new int[nums.length];
    res[0] = 1;
    for (int i = 1; i < nums.length; i++)
      res[i] = res[i - 1] * nums[i - 1];
    for (int i = nums.length - 1, right = 1; i >= 0; i--) {
      res[i] *= right;
      right *= nums[i];
    }
    return res;
  }

  @Test
  public void test() {
    assertArrayEquals(new int[] { 24, 12, 8, 6 }, productExceptSelf(new int[] { 1, 2, 3, 4 }));
  }

}
