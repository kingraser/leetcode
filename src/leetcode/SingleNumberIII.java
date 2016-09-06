/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年3月2日;
//-------------------------------------------------------
public class SingleNumberIII {
    /*
    Given an array of numbers nums, 
    in which exactly two elements appear only once and all the other elements appear exactly twice. 
    Find the two elements that appear only once.
    
    For example:
    
    Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
    
    Note:
    
    The order of the result is not important. So in the above example, [5, 3] is also correct.
    Your algorithm should run in linear runtime complexity. 
    Could you implement it using only constant space complexity?
    */

    /*
            先全部异或,出现两次的全部抵消,剩下的结果就是两个数(a和b)的异或
            又两数不同,所以异或的结果肯定会有位为1,说明a和该位和b的该位不同
            找到该位,将数组分为两类,该位为1的和该位为0的
            对每一类,问题转化为single number I 
    */

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] { 3, 5 }, singleNumber(new int[] { 1, 2, 1, 3, 2, 5 }));
    }

    public int[] singleNumber(int[] nums) {
        int aXorb = 0;
        for (int item : nums)
            aXorb ^= item;
        int lastBit = aXorb & (-aXorb);
        int a = 0, b = 0;
        for (int item : nums)
            if ((item & lastBit) != 0) a = a ^ item;
            else b = b ^ item;
        return new int[] { a, b };
    }
}
