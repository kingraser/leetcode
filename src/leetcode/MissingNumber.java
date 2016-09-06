/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月2日<p>
//-------------------------------------------------------
public class MissingNumber {

    /*
            给定一数组0, 1, 2, ..., n，从中取一个数，找出这个数
    For example
    Given [0, 1, 3] return 2. 
    
            解法一：异或    
            解法二：取和
            解法三：二分
    */

    public int missingNumber(int[] nums) {
        int check = 0;
        for (int i = 0; i < nums.length; i++)
            check ^= nums[i] ^ (i + 1);
        return check;
    }

    public int missingNumberII(int[] nums) {
        int sum = (nums.length * (nums.length + 1)) >> 1;
        for (int i = 0; i < nums.length; sum -= nums[i], i++);
        return sum;
    }

    public int missingNumberIII(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length, mid;
        while (left < right)
            if (nums[mid = (left + right) >> 1] > mid) right = mid;
            else left = mid + 1;
        return left;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, missingNumber(new int[] { 0, 1, 3 }));
        Assert.assertEquals(2, missingNumberII(new int[] { 0, 1, 3 }));
        Assert.assertEquals(2, missingNumberIII(new int[] { 0, 1, 3 }));
    }
}
