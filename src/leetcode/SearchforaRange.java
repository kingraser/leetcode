/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class SearchforaRange {
    /*
    Given a sorted array of integers, find the starting and ending position of a given target value.    
    Your algorithm's runtime complexity must be in the order of O(log n).    
    If the target is not found in the array, return [-1, -1].    
    For example,
    Given [5, 7, 7, 8, 8, 10] and target value 8,
    return [3, 4]. 
    */

    public int[] searchRange(int[] nums, int target) {
        int start = firstGreaterEqual(nums, target);
        if (start == nums.length || nums[start] != target) return new int[] { -1, -1 };
        return new int[] { start, firstGreaterEqual(nums, target + 1) - 1 };
    }

    public int firstGreaterEqual(int[] A, int target) {
        int l = 0, r = A.length, mid = l + ((r - l) >> 1);
        for (; l < r; mid = l + ((r - l) >> 1))
            if (A[mid] < target) l = mid + 1;
            else r = mid;
        return l;
    }

    @Test
    public void test() {
        int[] a = { 5, 7, 7, 8, 8, 10 };
        Assert.assertArrayEquals(new int[] { 3, 4 }, searchRange(a, 8));;
    }

}
