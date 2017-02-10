package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

import org.junit.Test;

public class MissingRanges {

  /*
  Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
  For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList("2", "4->49", "51->74", "76->99"),
        findMissingRanges(new int[] { 0, 1, 3, 50, 75 }, 0, 99));
  }

  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    Arrays.sort(nums);
    int start = Arrays.binarySearch(nums, lower), end = Arrays.binarySearch(nums, upper);
    if (start < 0) start = ~start;
    if (end < 0) end = ~end;
    List<String> result = new ArrayList<>();
    for (int left, right = start; right < end;) {
      if()
    }
    return result;
  }

}
