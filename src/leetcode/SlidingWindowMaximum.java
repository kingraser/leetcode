package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

import java.util.PriorityQueue;

import org.junit.Test;

public class SlidingWindowMaximum {
  /*
  Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
  You can only see the k numbers in the window. Each time the sliding window moves right by one position.
  
  For example,
  Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
  
  Window position                Max
  ---------------               -----
  [1  3  -1] -3  5  3  6  7       3
   1 [3  -1  -3] 5  3  6  7       3
   1  3 [-1  -3  5] 3  6  7       5
   1  3  -1 [-3  5  3] 6  7       5
   1  3  -1  -3 [5  3  6] 7       6
   1  3  -1  -3  5 [3  6  7]      7
  
  Therefore, return the max sliding window as [3,3,5,5,6,7].
  
  Note:
  You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
  
  Follow up:
  Could you solve it in linear time?
  
  Hint:
  
  How about using a data structure such as deque (double-ended queue)?
  The queue size need not be the same as the window’s size.
  Remove redundant elements and the queue should store only elements that need to be considered.    
  */

  @Test
  public void test() {
    int[] expected = new int[] { 7, 7, 7, 7, 7 }, input = new int[] { -7, -8, 7, 5, 7, 1, 6, 0 };
    assertArrayEquals(expected, maxSlidingWindow(input, 4));
    assertArrayEquals(expected, maxSlidingWindowII(input, 4));

    expected = new int[] { 3, 3, 5, 5, 6, 7 };
    input = new int[] { 1, 3, -1, -3, 5, 3, 6, 7 };
    assertArrayEquals(expected, maxSlidingWindow(input, 3));
    assertArrayEquals(expected, maxSlidingWindowII(input, 3));
  }

  //dequeue
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (k < 1) return nums;
    int[] result = new int[nums.length - k + 1];
    Deque<Integer> q = new ArrayDeque<>(k);
    for (int i = 0, l = k - 1; i < nums.length; i++) {
      for (; !q.isEmpty() && q.peekLast() < nums[i]; q.pollLast());
      q.offer(nums[i]);
      if (i >= l) result[i - l] = q.peek() == nums[i - l] ? q.poll() : q.peek();
    }
    return result;
  }

  //heap
  public int[] maxSlidingWindowII(int[] nums, int k) {
    if (k < 1) return nums;
    int[] result = new int[nums.length - k + 1];
    PriorityQueue<Integer> heap = new PriorityQueue<>(k, Comparator.reverseOrder());
    for (int i = 0, l = k - 1, len = nums.length; i < len; i++) {
      if (i >= k) heap.remove(nums[i - k]);
      heap.offer(nums[i]);
      if (i >= l) result[i - l] = heap.peek();
    }
    return result;
  }
}
