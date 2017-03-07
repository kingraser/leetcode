package leetcode;

import static leetcode.FourSum.kSum;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ThreeSum {

  /*
  Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
  Find all unique triplets in the array which gives the sum of zero.
  Note: The solution set must not contain duplicate triplets.
  
  For example, given array S = [-1, 0, 1, 2, -1, -4],  
  A solution set is:
  [
  [-1, 0, 1],
  [-1, -1, 2]
  ]  
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1)),
        threeSum(new int[] { -1, 0, 1, 2, -1, -4 }));
  }

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    return kSum(nums, 0, 3, 0);
  }

}
