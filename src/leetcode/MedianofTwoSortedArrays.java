/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class MedianofTwoSortedArrays {

    /*
            给两个有序int array 找出中位数(奇数时中间,偶数时中间两数的平均数)
            
            思想:转化为两个有序数列中找第k大的数,此题k=(m+n)/2
            尝试二分法,一次排除k/2个数
            若a[k/2]<b[k/2] 则a[start...k/2]可排除,否则b[start...k/2]可排除
            复杂度O(log(min(m,n)))
    */

    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length) return findMedianSortedArrays(B, A);
        int k = (A.length + B.length - 1) >> 1, l = 0, r = Math.min(k, A.length);
        for (int mid1; l < r;)
            if (A[mid1 = (l + r) >> 1] < B[k - mid1]) l = mid1 + 1;
            else r = mid1;
        double a = l == 0 ? B[k - l] : Math.max(A[l - 1], B[k - l]);
        if (((A.length + B.length) & 1) == 1) return a;//odd
        return (a + (l == A.length ? B[k - l + 1] : k - l + 1 < B.length ? Math.min(A[l], B[k - l + 1]) : A[l])) / 2;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, findMedianSortedArrays(new int[] { 1 }, new int[] { 1 }), 0);
    }
}
