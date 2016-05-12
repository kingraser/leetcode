/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年3月4日;
//-------------------------------------------------------
public class FindtheDuplicateNumber {
    /*
    Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
    prove that at least one duplicate number must exist. Assume that there is only one duplicate number, 
    find the duplicate one.
    
    Note:    
    You must not modify the array (assume the array is read only).
    You must use only constant, O(1) extra space.
    Your runtime complexity should be less than O(n^2).
    There is only one duplicate number in the array, but it could be repeated more than once.
    */

    @Test
    public void test() {
        Assert.assertEquals(1, findDuplicate(new int[] { 1, 2, 3, 4, 5, 6, 7, 1 }));
    }

    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0, finder = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        do {
            slow = nums[slow];
            finder = nums[finder];
        } while (slow != finder);
        return slow;
    }
}
