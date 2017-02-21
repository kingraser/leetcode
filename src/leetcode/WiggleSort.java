package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import leetcode.util.ArrayUtil;

public class WiggleSort {

  /*
  Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
  For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
  */

  @Test
  public void test() {
    int[] actual = new int[] { 3, 5, 2, 1, 6, 4 };
    wiggleSort(actual);
    assertArrayEquals(new int[] { 3, 5, 1, 6, 2, 4 }, actual);
  }

  void wiggleSort(int[] nums) {
    if (nums.length <= 1) return;
    for (int i = 1; i < nums.length; i++)
      if (((i & 1) == 1 && nums[i] < nums[i - 1]) || ((i & 1) == 0 && nums[i] > nums[i - 1]))
        ArrayUtil.swap(nums, i, i - 1);
  }
}
