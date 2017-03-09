package leetcode;

import static leetcode.KthLargestElementinanArray.findKthLargest;
import static leetcode.util.ArrayUtil.swap;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class WiggleSortII {
  /*
  Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
  
  Example:
  (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
  (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
  
  Note:
  You may assume all input has valid answer.
  
  Follow Up:
  Can you do it in O(n) time and/or in-place with O(1) extra space? 
  */

  private static boolean isWiggleSorted(int[] A) {
    for (int i = 1; i < A.length; i += 2)
      if (Math.max(A[i - 1], i + 1 == A.length ? Integer.MIN_VALUE : A[i + 1]) >= A[i]) return false;
    return true;
  }

  @Test
  public void test() {
    int[] nums = new int[] { 1, 5, 1, 1, 6, 4 };
    wiggleSort(nums);
    assertTrue(isWiggleSorted(nums));
  }

  @Test
  public void testII() {
    int[] nums = new int[] { 1, 5, 1, 1, 6, 4 };
    wiggleSortII(nums);
    assertTrue(isWiggleSorted(nums));
  }

  public static void wiggleSort(int[] nums) {
    int[] sortedNums = Arrays.stream(nums).sorted().toArray();
    for (int i = 0, k = nums.length - 1, j = k >> 1; i < nums.length; i++)
      nums[i] = (i & 1) == 0 ? sortedNums[j--] : sortedNums[k--];
  }

  public void wiggleSortII(int[] nums) {
    for (int n = nums.length, mid = findKthLargest(nums, n >> 1), left = 0, i = 0, right = n - 1, newi; i <= right;)
      if (nums[newi = map(i, n)] > mid) swap(nums, map(left++, n), map(i++, n));
      else if (nums[newi] < mid) swap(nums, map(right--, n), map(i, n));
      else i++;
  }

  private int map(int index, int n) {
    return (1 + (index << 1)) % (n | 1);
  }
}
