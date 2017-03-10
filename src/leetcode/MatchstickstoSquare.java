package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class MatchstickstoSquare {

  /*
  Remember the story of Little Match Girl? 
  By now, you know exactly what matchsticks the little match girl has, 
  please find out a way you can make one square by using up all those matchsticks. 
  You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.  
  Your input will be several matchsticks the girl has, represented with their stick length. 
  Your output will either be true or false, 
  to represent whether you could make one square using all the matchsticks the little match girl has.
  
  Example 1:  
  Input: [1,1,2,2,2]
  Output: true  
  Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
  
  Example 2:  
  Input: [3,3,3,3,4]
  Output: false  
  Explanation: You cannot find a way to form a square with all the matchsticks.
  
  Note:  
    The length sum of the given matchsticks is in the range of 0 to 10^9.
    The length of the given matchstick array will not exceed 15. 
  */

  @Test
  public void test() {
    assertTrue(makesquare(new int[] { 1, 1, 2, 2, 2 }));
    assertFalse(makesquare(new int[] { 3, 3, 3, 3, 4 }));
    assertTrue(makesquare(new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 8 }));
  }

  public boolean makesquare(int[] nums) {
    if (nums == null || nums.length < 4) return false;
    int sum = sum(nums);
    if (sum % 4 != 0) return false;
    Arrays.sort(nums);
    return dfs(nums, new int[4], nums.length - 1, sum / 4);
  }

  private boolean dfs(int[] nums, int[] sums, int idx, int quarter) {
    if (idx == -1) return sums[0] == quarter && sums[1] == quarter && sums[2] == quarter;
    for (int i = 0; i < 4; i++) {
      if (sums[i] + nums[idx] > quarter) continue;
      sums[i] += nums[idx];
      if (dfs(nums, sums, idx - 1, quarter)) return true;
      sums[i] -= nums[idx];
    }
    return false;
  }

  private int sum(int[] nums) {
    return Arrays.stream(nums).sum();
  }
}
