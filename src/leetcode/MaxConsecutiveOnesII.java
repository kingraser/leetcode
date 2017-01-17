package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaxConsecutiveOnesII {

  /*
  Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
  
  Example 1:
  
  Input: [1,0,1,1,0]
  Output: 4
  Explanation: Flip the first zero will get the the maximum number of consecutive 1s. After flipping, the maximum number of consecutive 1s is 4.
  */

  @Test
  public void test() {
    assertEquals(4, findMaxConsecutiveOnes(new int[] { 1, 0, 1, 1, 0 }));
  }

  public int findMaxConsecutiveOnes(int[] nums) {
    int max = 0, zero = 0, k = 1; // flip at most k zero
    for (int l = 0, r = 0; r < nums.length; r++) {
      if (nums[r] == 0) zero++;
      while (zero > k)
        if (nums[l++] == 0) zero--;
      max = Math.max(max, r - l + 1);
    }
    return max;
  }

}
