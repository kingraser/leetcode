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

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int k = (nums1.length + nums2.length - 1) >> 1, l = 0, r = Math.min(k, nums1.length);
        for (int mid1 = (l + r) >> 1, mid2 = k - mid1; l < r; mid1 = (l + r) >> 1, mid2 = k - mid1)
            if (nums1[mid1] < nums2[mid2]) l = mid1 + 1;
            else r = mid1;
        double a = l == 0 ? nums2[k - l] : Math.max(nums1[l - 1], nums2[k - l]);
        if (((nums1.length + nums2.length) & 1) == 1) return a;//odd
        return (a + (l == nums1.length ? nums2[k - l + 1]
                : k - l + 1 < nums2.length ? Math.min(nums1[l], nums2[k - l + 1]) : nums1[l])) / 2;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, findMedianSortedArrays(new int[] { 1 }, new int[] { 1 }), 0);
    }
}
