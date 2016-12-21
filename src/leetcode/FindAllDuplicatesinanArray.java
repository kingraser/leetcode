package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FindAllDuplicatesinanArray {

  /*
  Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
  
  Find all the elements that appear twice in this array.
  
  Could you do it without extra space and in O(n) runtime?
  
  Example:
  
  Input:
  [4,3,2,7,8,2,3,1]
  
  Output:
  [2,3]
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(2, 3), findDuplicates(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 }));
  }

  public List<Integer> findDuplicates(int[] nums) {
    List<Integer> result = new ArrayList<>(nums.length);
    for (int i : nums)
      if (nums[Math.abs(i) - 1] < 0) result.add(Math.abs(i));
      else nums[Math.abs(i) - 1] = -nums[Math.abs(i) - 1];
    return result;
  }
}
