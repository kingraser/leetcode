package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MajorityElementII {

  /*
  Given an integer array of size n, find all elements that appear more than floor(n/3) times. 
  The algorithm should run in linear time and in O(1) space.
  Hint: How many majority elements could it possibly have?
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 2), majorityElement(new int[] { 1, 1, 2, 2, 3 }));
  }

  public List<Integer> majorityElement(int[] nums) {
    List<Integer> result = new ArrayList<Integer>();
    int first = 0, second = 0, firstCount = 0, secondCount = 0;
    for (int i : nums)
      if (firstCount == 0) {
        first = i;
        firstCount++;
      } else if (i == first) firstCount++;
      else if (secondCount == 0) {
        second = i;
        secondCount++;
      } else if (i == second) secondCount++;
      else {
        firstCount--;
        secondCount--;
      }
    firstCount = secondCount = 0;
    for (Integer i : nums)
      if (i == first) firstCount++;
      else if (i == second) secondCount++;
    if (firstCount > nums.length / 3) result.add(first);
    if (secondCount > nums.length / 3) result.add(second);
    return result;
  }
}
