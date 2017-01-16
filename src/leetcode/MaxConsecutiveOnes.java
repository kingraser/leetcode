package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaxConsecutiveOnes {

  /*
  Given a binary array, find the maximum number of consecutive 1s in this array.
  
  Example 1:
  
  Input: [1,1,0,1,1,1]
  Output: 3
  Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
  
  Note:
  
    The input array will only contain 0 and 1.
    The length of input array is a positive integer and will not exceed 10,000 
  */

  @Test
  public void test() {
    assertEquals(3, findMaxConsecutiveOnes(new int[] { 1, 1, 0, 1, 1, 1 }));
    assertEquals(2, findMaxConsecutiveOnes(new int[] { 1, 0, 1, 1, 0, 1 }));
  }

  public int findMaxConsecutiveOnes(int[] nums) {
    int result = 0, count = 0;
    for (int i : nums)
      if (i != 1) {
        result = Math.max(result, count);
        count = 0;
      } else count++;
    return Math.max(result, count);
  }
}
