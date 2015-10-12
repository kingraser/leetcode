/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class MaximumSubarray {
    /*
    Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
    
    For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
    the contiguous subarray [4,−1,2,1] has the largest sum = 6. 
    */

    public int maxSubArray(int[] A) {
        int sum = 0, maxsum = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if (sum > maxsum) maxsum = sum;
            if (sum < 0) sum = 0;
        }
        return maxsum;
    }

}
