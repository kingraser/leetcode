/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月19日<p>
//-------------------------------------------------------
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
        int[] expected = new int[] { 7, 7, 7, 7, 7 };
        Assert.assertTrue(equals(expected, maxSlidingWindowII(new int[] { -7, -8, 7, 5, 7, 1, 6, 0 }, 4)));
    }

    private boolean equals(int[] a, int[] b) {
        if (a == null && b == null) return true;
        if (a == null || b == null || a.length != b.length) return false;
        for (int i = 0; i < a.length; i++)
            if (a[i] != b[i]) return false;
        return true;
    }

    //dqueue
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k < 1) return nums;
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!q.isEmpty() && q.peek() < i - k + 1)
                q.poll();
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i])
                q.pollLast();
            q.offer(i);
            if (i >= k - 1) result[i - k + 1] = nums[q.peek()];
        }
        return result;
    }

    //heap
    public int[] maxSlidingWindowII(int[] nums, int k) {
        if (k < 1) return nums;
        int[] result = new int[nums.length - k + 1];
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return -(Integer.valueOf(nums[o1]).compareTo(nums[o2]));
            }
        });
        for (int i = 0; i < k; heap.add(i++));
        result[0] = nums[heap.peek()];
        for (int i = k; i < nums.length; i++) {
            heap.remove(i - k);
            heap.add(i);
            result[i - k + 1] = nums[heap.peek()];
        }
        return result;
    }
}
