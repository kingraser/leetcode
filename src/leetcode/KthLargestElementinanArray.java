/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月18日<p>
//-------------------------------------------------------
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
        Assert.assertEquals(3, findKthLargestII(new int[] { 7, 6, 5, 4, 3, 2, 1 }, 5));
    }

    //heap
    public int findKthLargest(int[] nums, int k) {
        return findKth(nums, k, true);
    }

    private int findKth(int[] nums, int k, boolean isLarge) {
        if (k > (nums.length + 1) >> 1) return findKth(nums, nums.length - k + 1, !isLarge);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(nums.length, new Comparator<Integer>() {

            @Override
            public int compare(Integer i1, Integer i2) {
                int val;
                if (i1 == i2) val = 0;
                else if (i1 < i2) val = 1;
                else val = -1;
                return isLarge ? val : -val;
            }
        });
        for (int i : nums)
            queue.add(i);
        while (--k > 0)
            queue.poll();
        return queue.poll();
    }

    //quicksort
    public int findKthLargestII(int[] nums, int k) {
        return findK(nums, nums.length - k, 0, nums.length - 1);
    }

    private int findK(int[] nums, int k, int from, int to) {
        if (from >= to) return nums[from];
        int m = partition(nums, from, to);
        if (m == k) return nums[m];
        else if (m < k) return findK(nums, k, m + 1, to);
        else return findK(nums, k, from, m - 1);
    }

    private int partition(int[] nums, int from, int to) {
        int x = nums[from], m = from, n = from + 1;
        for (; n <= to; n++)
            if (nums[n] < x) swap(nums, ++m, n);
        swap(nums, from, m);
        return m;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
