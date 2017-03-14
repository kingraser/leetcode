package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TwoSumIIInputarrayissorted {

  /*
  Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.  
  The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
  You may assume that each input would have exactly one solution.
  
  Input: numbers={2, 7, 11, 15}, target=9
  Output: index1=1, index2=2 
  */

  @Test
  public void test() {
    assertArrayEquals(new int[] { 1, 2 }, twoSum(new int[] { 2, 7, 11, 15 }, 9));
  }

  public int[] twoSum(int[] numbers, int target) {
    for (int left = 0, right = numbers.length - 1, sum;;)
      if ((sum = numbers[left] + numbers[right]) == target) return new int[] { ++left, ++right };
      else if (sum > target) right--;
      else left++;
  }
}
