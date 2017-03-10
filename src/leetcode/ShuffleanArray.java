package leetcode;

import static leetcode.util.ArrayUtil.swap;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

import org.junit.Test;

public class ShuffleanArray {

  /*
  Shuffle a set of numbers without duplicates.
  
  Example:
  
  // Init an array with set 1, 2, and 3.
  int[] nums = {1,2,3};
  Solution solution = new Solution(nums);
  
  // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
  solution.shuffle();
  
  // Resets the array back to its original configuration [1,2,3].
  solution.reset();
  
  // Returns the random shuffling of array [1,2,3].
  solution.shuffle(); 
  */

  @Test
  public void test() {
    int[] origin = new int[] { 1, 2, 3 };
    Shuffler shuffler = new Shuffler(origin);
    assertArrayEquals(origin, shuffler.reset());
    int count = 0;
    while (!Objects.deepEquals(origin, shuffler.shuffle()))
      count++;
    assertTrue(count < 10000);
  }

  public class Shuffler {
    private int[] nums, shuffles;
    private Random random = new Random();

    public Shuffler(int[] nums) {
      this.shuffles = Arrays.copyOf(nums, nums.length);
      this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
      return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
      for (int i = 0; i < shuffles.length; swap(shuffles, i, random.nextInt(++i)));
      return shuffles;
    }

  }
}
