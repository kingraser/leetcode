package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class MinimumMovestoEqualArrayElementsII {

  /*
  Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
  
  You may assume the array's length is at most 10,000.
  
  Example:
  
  Input:
  [1,2,3]
  
  Output:
  2
  
  Explanation:
  Only two moves are needed (remember each move increments or decrements one element):
  
  [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
  */

  public int minMoves2(int[] nums) {
    Arrays.sort(nums);
    int middle = nums[nums.length >> 1];
    return Arrays.stream(nums).map(i -> Math.abs(i - middle)).sum();
  }

  @Test
  public void test() {
    assertEquals(2, minMoves2(new int[] { 1, 2, 3 }));
  }
}
