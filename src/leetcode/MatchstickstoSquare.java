package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MatchstickstoSquare {

  /*
  Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
  
  Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
  
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
    assertTrue(makesquareI(new int[] { 1, 1, 2, 2, 2 }));
    assertFalse(makesquareI(new int[] { 3, 3, 3, 3, 4 }));
    assertTrue(makesquareI(new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 8 }));

    assertTrue(makesquareII(new int[] { 1, 1, 2, 2, 2 }));
    assertFalse(makesquareII(new int[] { 3, 3, 3, 3, 4 }));
    assertTrue(makesquareII(new int[] { 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 8 }));
  }

  public boolean makesquareI(int[] nums) {
    if (nums == null || nums.length < 4) return false;
    int sum = Arrays.stream(nums).sum(), side = sum / 4;
    if (sum % 4 != 0) return false;
    Arrays.sort(nums);
    int[] sides = new int[4];
    for (int i = nums.length - 1, j = 0; i >= 0;)
      if (nums[i] + sides[j %= 4] > side) if (j++ == 3) return false;
      else continue;
      else sides[j++] += nums[i--];
    return Arrays.stream(sides).allMatch(i -> i == side);
  }

  public boolean makesquareII(int[] nums) {
    if (nums == null || nums.length < 4) return false;
    int sum = Arrays.stream(nums).sum(), side = sum / 4, all = (1 << nums.length) - 1;
    if (sum % 4 != 0) return false;
    List<Integer> history = new ArrayList<>(1 << nums.length);
    boolean[] halves = new boolean[1 << nums.length];
    for (int option = 1, temp, i, half; option <= all; history.add(option++)) {
      for (i = temp = 0; temp < side && i < 16; i++)
        if (((option >> i) & 1) != 0) temp += nums[i];
      if (temp != side) continue;
      for (int record : history) {
        if ((record & option) != 0) continue;
        halves[half = record | option] = true;
        if (halves[all ^ half]) return true;
      }
    }
    return false;
  }
}
