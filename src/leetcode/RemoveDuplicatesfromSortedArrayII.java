/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class RemoveDuplicatesfromSortedArrayII {
    /*
    Follow up for "Remove Duplicates":
    What if duplicates are allowed at most twice?
    
    For example,
    Given sorted array nums = [1,1,1,2,2,3],
    
    Your function should return length = 5, 
    with the first five elements of nums being 1, 1, 2, 2 and 3. 
    It doesn't matter what you leave beyond the new length. 
    */

    public int removeDuplicates(int[] A) {
        return removeDuplicates(A, 2);
    }

    public int removeDuplicates(int[] A, int n) {
        for (int i = n, size = n; i < A.length; i++)
            if (A[i] != A[n - size]) A[n++] = A[i];
        return Math.min(A.length, n);
    }

    @Test
    public void test() {
        Assert.assertEquals(5, removeDuplicates(new int[] { 1, 1, 1, 2, 2, 3 }));
    }

}
