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
    复杂度Olg(m+n)
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int start1 = -1, start2 = -1, k = (nums1.length + nums2.length) / 2,
                end1 = Math.min(nums1.length - 1, (start1 + k / 2) < 0 ? 0 : start1 + k / 2),
                end2 = Math.min(nums2.length - 1, (start2 + k / 2) < 0 ? 0 : start2 + k / 2), flag = 0;
        while (k != 0) {
            if (end1 < nums1.length && end1 > -1) {
                if (end2 < nums2.length && end2 > -1) {
                    if (nums1[end1] < nums2[end2]) {
                        flag = 0;
                        k -= (end1 - start1);
                        start1 = end1;
                    } else {
                        flag = 1;
                        k -= (end2 - start2);
                        start2 = end2;
                    }
                    end1 = Math.max(start1 + 1, start1 + k / 2);
                    end2 = Math.max(start2 + 1, start2 + k / 2);
                } else return (nums1.length + nums2.length) % 2 == 1 ? nums1[start1 + k + 1]
                        : ((double) nums1[start1 + k + 1] + nums1[start1 + k]) / 2;
            } else return (nums1.length + nums2.length) % 2 == 1 ? nums2[start2 + k + 1]
                    : ((double) nums2[start2 + k + 1] + nums2[start2 + k]) / 2;
        }
        if ((nums1.length + nums2.length) % 2 == 1) {
            if (start1 == nums1.length - 1) return nums2[start2 + 1];
            if (start2 == nums2.length - 1) return nums1[start1 + 1];
            return Math.min(nums1[start1 + 1], nums2[start2 + 1]);
        } else {
            double first = flag == 0 ? nums1[start1] : nums2[start2], second = first;
            if (start1 == nums1.length - 1) second = nums2[start2 + 1];
            else if (start2 == nums2.length - 1) second = nums1[start1 + 1];
            else second = Math.min(nums1[start1 + 1], nums2[start2 + 1]);
            return (first + second) / 2;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(3.5, findMedianSortedArrays(new int[] { 1, 3, 5 }, new int[] { 2, 4, 6 }), 0);
    }
}
