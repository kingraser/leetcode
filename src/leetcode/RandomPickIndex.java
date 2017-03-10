package leetcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class RandomPickIndex {

  /*
  Given an array of integers with possible duplicates, randomly output the index of a given target number. 
  You can assume that the given target number must exist in the array.
  Note: The array size can be very large. Solution that uses too much extra space will not pass the judge.
  
  Example:  
  int[] nums = new int[] {1,2,3,3,3};
  Solution solution = new Solution(nums);
  // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
  solution.pick(3);  
  // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
  solution.pick(1);  
  */

  @Test
  public void test() {
    Solution solution = new Solution(new int[] { 1, 2, 3, 3, 3 });
    assertEquals(0, solution.pick(1));
    Set<Integer> set = new HashSet<>();
    int count = 0;
    for (; set.size() < 3 && count < 100; count++)
      set.add(solution.pick(3));
    System.out.println(count);
    assertTrue(count < 99);
  }

  class Solution {
    int[] nums;
    Random random = new Random();

    public Solution(int[] nums) {
      this.nums = nums;
    }

    public int pick(int target) {
      int result = -1;
      for (int i = 0, count = 0; i < nums.length; i++)
        if (nums[i] != target) continue;
        else if (random.nextInt(++count) == 0) result = i;
      return result;
    }
  }

}
