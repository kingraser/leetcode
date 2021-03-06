package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

  /*
  Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
  
  Example 1:
  Input:nums = [1,1,1], k = 2
  Output: 2
  
  Note:
      The length of the array is in range [1, 20,000].
      The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
  */

  @Test
  public void test() {
    assertEquals(2, subarraySum(new int[] { 1, 1, 1 }, 2));
  }

  public int subarraySum(int[] nums, int k) {
    int result = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    for (int i = 0, sum = 0; i < nums.length;) {
      result += map.getOrDefault((sum += nums[i++]) - k, 0);
      map.merge(sum, 1, Integer::sum);
    }
    return result;
  }

}
