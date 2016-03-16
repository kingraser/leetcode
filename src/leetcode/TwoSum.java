/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class TwoSum {

    /*
            给一int数组及target,求两下标使和为target.有且仅有一组解
    
    Input: numbers={2, 7, 11, 15}, target=9
    Output: index1=0, index2=1
    
            两种解法    
    1map法
        key为数值,value为下标.遍历keyset,判断target减去key的差是否在map.复杂度On
    2双指针法
         1排序    
         2头指针在首,尾指针在尾.头尾和>target,尾指针向前,小于首指针向后.复杂度Onlgn 
    */

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0;;)
            if (map.containsKey(target - nums[i])) return new int[] { map.get(target - nums[i]), i };
            else map.put(nums[i], i++);
    }

    public int[] twoSumII(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; map.put(nums[i], i++));
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for (int l = 0, r = nums.length - 1;;)
            if (list.get(l) + list.get(r) == target) return new int[] { map.get(list.get(l)), map.get(list.get(r)) };
            else if (list.get(l) + list.get(r) > target) r--;
            else l++;
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] { 0, 1 }, twoSum(new int[] { 2, 7, 11, 15 }, 9));
        Assert.assertArrayEquals(new int[] { 0, 1 }, twoSumII(new int[] { 2, 7, 11, 15 }, 9));
    }

}
