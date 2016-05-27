/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
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
        Assert.assertEquals(3, findKthLargest(new int[] { 5, 3, 2, 4, 7, 1, 6 }, 5));
        Assert.assertEquals(3, findKthLargestII(new int[] { 5, 3, 2, 4, 7, 1, 6 }, 5));
        Assert.assertEquals(5, findKthLargestII(new int[] { 5, 3, 2, 4, 7, 1, 6 }, 3));
    }

    //heap
    public int findKthLargest(int[] nums, int k) {
        return findKth(nums, k, true);
    }

    private int findKth(int[] nums, int k, boolean isLarge) {
        if (k > (nums.length + 1) >> 1) return findKth(nums, nums.length - k + 1, !isLarge);
        PriorityQueue<Integer> queue = new PriorityQueue<>(nums.length, (i1, i2) -> isLarge ? i2 - i1 : i1 - i2);
        Arrays.stream(nums).forEach(i -> queue.add(i));
        for (; --k > 0; queue.poll());
        return queue.poll();
    }

    //quick sort
    public int findKthLargestII(int[] nums, int k) {
        return findK(nums, nums.length - k, 0, nums.length - 1);//k large = length - k + 1 small, so index = length - k
    }

    /**
     * @param nums
     * @param k 查找的下标
     * @param from 起始下标 inclusive
     * @param to 结束下标 inclusive
     * @return 返回排序后下标为k的元素,亦即第k+1个元素
     */
    private int findK(int[] nums, int k, int from, int to) {
        if (from >= to) return nums[from];
        int m = partition(nums, from, to);
        if (m == k) return nums[m];
        else if (m < k) return findK(nums, k, m + 1, to);
        else return findK(nums, k, from, m - 1);
    }

    /**
     * @param nums
     * @param from 起始下标 inclusive
     * @param to 结束下标 inclusive
     * @return index k where element k is smaller than elements in [from,k) and is larger than elements in (k,end]
     */
    private int partition(int[] nums, int from, int to) {
        int e = nums[from], idx = from;//第一个元素e, e排序后的下标
        for (int i = from + 1; i <= to; i++)//遍历e以后的元素
            if (nums[i] < e) swap(nums, ++idx, i);//将比e优先度低的元素连续排列
        swap(nums, from, idx);//将e放置在比其优先度低的元素后
        return idx;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
