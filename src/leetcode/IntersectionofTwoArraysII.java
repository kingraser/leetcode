/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年5月18日;
//-------------------------------------------------------
public class IntersectionofTwoArraysII {

    /*
    Given two arrays, write a function to compute their intersection.
    
    Example:
    Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2]. 
    */

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] { 2, 2 }, intersection(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 }));
        Assert.assertArrayEquals(new int[] { 2, 2 }, intersectionII(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 }));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        return Arrays.stream(nums2).boxed().filter(list::remove).mapToInt(i -> i).toArray();
    }

    public int[] intersectionII(int[] nums1, int[] nums2) {
        int k = 0, l1 = nums1.length, l2 = nums2.length;
        int[] result = new int[l1];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i = 0, j = 0; i < l1 && j < l2;)
            if (nums1[i] < nums2[j]) i++;
            else if (nums1[i] == nums2[j++]) result[k++] = nums1[i++];
        return Arrays.copyOf(result, k);
    }

}
