package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HouseRobber {

  /*
  You are a professional robber planning to rob houses along a street. 
  Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them 
  is that adjacent houses have security system connected 
  and it will automatically contact the police if two adjacent houses were broken into on the same night.
  Given a list of non-negative integers representing the amount of money of each house, 
  determine the maximum amount of money you can rob tonight without alerting the police.
  */

  public int rob(int[] nums) {
    int current = 0, previous = 0;//current for rob current, previous for rob previous
    for (int i = 0; i < nums.length;) {
      int newCurrent = previous + nums[i++];
      previous = Math.max(previous, current);
      current = newCurrent;
    }
    return Math.max(current, previous);
  }

  @Test
  public void test() {
    int[] input = new int[] { 1, 1, 3, 4, 5, 7, 8, 9, 10, 11, 31, 32, 47 };
    assertEquals(105, rob(input));
  }
}
