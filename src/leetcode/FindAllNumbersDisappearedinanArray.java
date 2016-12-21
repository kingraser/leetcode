package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FindAllNumbersDisappearedinanArray {

  /*
  Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.  
  Find all the elements of [1, n] inclusive that do not appear in this array.  
  Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
  
  Example:
  
  Input:
  [4,3,2,7,8,2,3,1]
  
  Output:
  [5,6]  
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(5, 6), findDisappearedNumbers(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 }));
  }

  public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0, index; i < nums.length; nums[index = Math.abs(nums[i++]) - 1] = -Math.abs(nums[index]));
    for (int i = 0; i < nums.length; i++)
      if (nums[i] > 0) list.add(i + 1);
    return list;
  }
}
