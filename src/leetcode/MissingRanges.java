package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MissingRanges {

  /*
  Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
  For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList("-2147483648->2147483647"),
        findMissingRanges(new int[0], Integer.MIN_VALUE, Integer.MAX_VALUE));
    assertEquals(Arrays.asList("-2147483647->2147483647"),
        findMissingRanges(new int[] { Integer.MIN_VALUE }, Integer.MIN_VALUE, Integer.MAX_VALUE));
    assertEquals(Arrays.asList("2", "4->49", "51->74", "76->99"),
        findMissingRanges(new int[] { 0, 1, 3, 50, 75 }, 0, 99));
  }

  int start, end;
  long lower, upper;

  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    List<String> result = new ArrayList<>();
    init(nums, lower, upper);
    for (; start < nums.length && nums[start] < lower; start++);
    for (; end >= 0 && nums[end] > upper; end--);
    for (int idx = start - 1; idx <= end;)
      add(result, get(nums, idx), get(nums, ++idx));
    return result;
  }

  void init(int[] nums, int lower, int upper) {
    this.start = 0;
    this.end = nums.length - 1;
    this.lower = lower;
    this.upper = upper;
  }

  long get(int[] A, int idx) {
    if (idx < start) return --lower;
    if (idx > end) return ++upper;
    return A[idx];
  }

  void add(List<String> result, long left, long right) {
    if (right - left < 2) return;
    if (right - left == 2) result.add(Long.toString(++left));
    else result.add(++left + "->" + --right);
  }

}
