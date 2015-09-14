/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class FindPeakElement {

    /*
    For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
    */

    public int findPeakElement(int[] num) {
        int l = 0, r = num.length - 1, mid = r / 2;
        for (; l < r; mid = (l + r) / 2)
            if (num[mid] < num[mid + 1]) l = mid + 1;
            else r = mid;
        return l;
    }
}
