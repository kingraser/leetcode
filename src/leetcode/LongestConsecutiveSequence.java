package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class LongestConsecutiveSequence {

  /*
  Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
  
  For example,
  Given [100, 4, 200, 1, 3, 2],
  The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
  
  Your algorithm should run in O(n) complexity. 
  */

  @Test
  public void test() {
    assertEquals(4, longestConsecutive(new int[] { 100, 4, 200, 1, 3, 2 }));
  }

  public int longestConsecutive(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
    int max = 1;
    for (int e : nums) {
      int count = 1;
      for (int left = e - 1; set.remove(left--); count++);
      for (int right = e + 1; set.remove(right++); count++);
      max = Math.max(count, max);
    }
    return max;
  }

}
