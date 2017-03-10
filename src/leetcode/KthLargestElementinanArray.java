package leetcode;

import static leetcode.util.ArrayUtil.swap;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.Test;

public class KthLargestElementinanArray {

  /*
  Find the kth largest element in an unsorted array. 
  Note that it is the kth largest element in the sorted order, not the kth distinct element.    
  For example,
  Given [3,2,1,5,6,4] and k = 2, return 5.
  Note:
  You may assume k is always valid, 1 ≤ k ≤ array's length.
  */

  @Test
  public void test() {
    assertEquals(3, findKthLargestZero(new int[] { 5, 3, 2, 4, 7, 1, 6 }, 5));
    assertEquals(3, findKthLargest(new int[] { 5, 3, 2, 4, 7, 1, 6 }, 5));
    assertEquals(5, findKthLargest(new int[] { 5, 3, 2, 4, 7, 1, 6 }, 3));
  }

  //heap
  public int findKthLargestZero(int[] nums, int k) {
    PriorityQueue<Integer> queue = new PriorityQueue<>(nums.length, (i1, i2) -> i2 - i1);
    Arrays.stream(nums).forEach(queue::add);
    for (; --k > 0; queue.poll());
    return queue.poll();
  }

  //quick sort
  public static int findKthLargest(int[] nums, int k) {
    return findK(nums, --k, 0, nums.length - 1);
  }

  /**
   * @param nums
   * @param k(search-index)
   * @param start(inclusive)
   * @param end(inclusive)
   * @return the no k+1 element
   */
  private static int findK(int[] nums, int k, int start, int end) {
    if (start >= end) return nums[start];
    int m = partition(nums, start, end);
    return m == k ? nums[m] : m < k ? findK(nums, k, m + 1, end) : findK(nums, k, start, m - 1);
  }

  /**
   * @param nums
   * @param from(inclusive)
   * @param to(inclusive)
   * @return index k where element k is smaller than elements in [from,k) and is
   *         larger than elements in (k,end]
   */
  private static int partition(int[] nums, int from, int to) {
    int element = nums[from], idx = from;
    for (int i = from + 1; i <= to; i++)
      if (nums[i] > element) swap(nums, ++idx, i);
    swap(nums, from, idx);
    return idx;
  }
}
