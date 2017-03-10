package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountofRangeSum {

  /*
  Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
  Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
  
  Note: A naive algorithm of O(n^2) is trivial. You MUST do better than that.
  
  Example:
  Given nums = [-2, 5, -1], lower = -2, upper = 2,
  Return 3.
  The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2. 
  */

  @Test
  public void test() {
    assertEquals(3, countRangeSum(new int[] { -2, 5, -1 }, -2, 2));
  }

  public int countRangeSum(int[] nums, int lower, int upper) {
    long[] sums = new long[nums.length + 1];
    for (int i = 0; i < nums.length; i++)
      sums[i + 1] = sums[i] + nums[i];
    return countWhileMergeSort(sums, 0, sums.length, lower, upper);
  }

  private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
    if (end - start <= 1) return 0;
    int mid = (start + end) >> 1,
        count = countWhileMergeSort(sums, start, mid, lower, upper) + countWhileMergeSort(sums, mid, end, lower, upper);
    int rightUpper = mid, rightLower = mid, rightIdx = mid;
    long[] cache = new long[end - start];
    for (int leftIdx = start, cacheIdx = 0; leftIdx < mid; ++leftIdx, ++cacheIdx) {
      while (rightUpper < end && sums[rightUpper] - sums[leftIdx] <= upper)
        rightUpper++;
      while (rightLower < end && sums[rightLower] - sums[leftIdx] < lower)
        rightLower++;
      while (rightIdx < end && sums[rightIdx] < sums[leftIdx])
        cache[cacheIdx++] = sums[rightIdx++];
      cache[cacheIdx] = sums[leftIdx];
      count += rightUpper - rightLower;
    }
    System.arraycopy(cache, 0, sums, start, rightIdx - start);
    return count;
  }

}
