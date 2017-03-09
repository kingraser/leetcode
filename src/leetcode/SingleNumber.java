package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SingleNumber {

  //Given an array of integers, every element appears twice except for one. Find that single one.
  public static int singleNumber(int[] nums) {
    int res = 0;
    for (int num : nums)
      res ^= num;
    return res;
  }

  @Test
  public void test() {
    assertEquals(3, singleNumber(new int[] { 1, 1, 2, 3, 2 }));
  }

}
