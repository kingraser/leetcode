/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class FindMinimuminRotatedSortedArray {

    /*
    Suppose a sorted array is rotated at some pivot unknown to you beforehand.    
    (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).    
    Find the minimum element.    
    You may assume no duplicate exists in the array.
    */

    @Test
    public void test() {
        Assert.assertEquals(0, findMin(new int[] { 4, 5, 6, 7, 0, 1, 2 }));
    }

    public int findMin(int[] A) {
        int beg = 0, end = A.length - 1, mid;
        while (beg < end && A[end] <= A[beg])
            if (A[mid = (beg + end) >> 1] < A[beg]) end = mid;
            else beg = mid + 1;
        return A[beg];
    }
}
