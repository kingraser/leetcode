/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class FindMinimuminRotatedSortedArrayII {

    /*
    Follow up for "Find Minimum in Rotated Sorted Array":
    What if duplicates are allowed?    
    Would this affect the run-time complexity? How and why?    
    Suppose a sorted array is rotated at some pivot unknown to you beforehand.    
    (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
    Find the minimum element.    
    The array may contain duplicates.
    */
    public int findMin(int[] A) {
        int beg = 0, end = A.length - 1, mid = (beg + end) / 2;
        for (; beg < end && A[end] <= A[beg]; mid = (beg + end) / 2) {
            if (A[end] == A[beg]) {
                beg++;
                continue;
            }
            if (A[mid] < A[beg]) end = mid;
            else beg = mid + 1;
        }
        return A[beg];
    }
}
