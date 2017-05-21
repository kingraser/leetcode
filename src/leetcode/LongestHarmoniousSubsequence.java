package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class LongestHarmoniousSubsequence {

  /*
  We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.
  
  Now, given an integer array, 
  you need to find the length of its longest harmonious subsequence among all its possible subsequences.
  
  Example 1:  
  Input: [1,3,2,2,5,2,3,7]
  Output: 5
  Explanation: The longest harmonious subsequence is [3,2,2,2,3].
  
  Note: The length of the input array will not exceed 20,000.  
  */

  @Test
  public void test() {
    assertEquals(5, findLHS(new int[] { 1, 3, 2, 2, 5, 2, 3, 7 }));
  }

  public int findLHS(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    Arrays.stream(nums).forEach(i -> map.merge(i, 1, Integer::sum));
    int result = 0, value;
    for (Entry<Integer, Integer> entry : map.entrySet())
      if ((value = map.getOrDefault(entry.getKey() + 1, 0)) != 0) result = Math.max(result, entry.getValue() + value);
    return result;
  }
}
