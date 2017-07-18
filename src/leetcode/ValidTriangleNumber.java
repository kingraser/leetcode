package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ValidTriangleNumber {

  /*
  Given an array consists of non-negative integers, 
  your task is to count the number of triplets chosen from the array 
  that can make triangles if we take them as side lengths of a triangle.
  
  Example 1:  
  Input: [2,2,3,4]
  Output: 3
  Explanation:
  Valid combinations are: 
  2,3,4 (using the first 2)
  2,3,4 (using the second 2)
  2,2,3
  
  Note:  
    The length of the given array won't exceed 1000.
    The integers in the given array are in the range of [0, 1000].  
  */

  @Test
  public void test() {
    assertEquals(3, triangleNumber(new int[] { 2, 2, 3, 4 }));
  }

  public int triangleNumber(int[] nums) {
    int count = 0;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++)
      if (nums[i] > 0) for (int j = i + 1, k = j + 1; j < nums.length - 1; count += k - j++ - 1)
        for (; k < nums.length && nums[i] + nums[j] > nums[k]; k++);
    return count;
  }
}
