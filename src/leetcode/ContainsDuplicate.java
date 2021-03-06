package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ContainsDuplicate {

  /*
  Given an array of integers, find if the array contains any duplicates. 
  Your function should return true if any value appears at least twice in the array, 
  and it should return false if every element is distinct. 
  */

  public boolean containsDuplicate(int[] nums) {
    return nums.length != Arrays.stream(nums).distinct().count();
  }

  @Test
  public void test() {
    assertTrue(containsDuplicate(new int[] { 1, 2, 3, 1 }));
    assertFalse(containsDuplicate(new int[] { 1, 2, 3 }));
  }

}
