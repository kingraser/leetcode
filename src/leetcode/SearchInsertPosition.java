/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月15日<p>
//-------------------------------------------------------
public class SearchInsertPosition {

    /*
    [1,3,5,6], 5 → 2
    [1,3,5,6], 2 → 1
    [1,3,5,6], 7 → 4
    [1,3,5,6], 0 → 0 
    */

    public int searchInsert(int[] A, int target) {
        int l = 0;
        for (int r = A.length, mid = l + (r - l) / 2; l != r; mid = l + (r - l) / 2)
            if (target > A[mid]) l = ++mid;
            else r = mid;
        return l;
    }

    @Test
    public void test() {
        int[] A = new int[] { 1, 3, 5, 6 };
        Assert.assertEquals(2, searchInsert(A, 5));
        Assert.assertEquals(1, searchInsert(A, 2));
        Assert.assertEquals(4, searchInsert(A, 7));
        Assert.assertEquals(0, searchInsert(A, 0));
    }

}
