/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class FourSum {

    /*
    转为3Sum(k Sum可递归为k-1 Sum)   
    */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }

    /**
     * @param nums 数组
     * @param target 目标和
     * @param k k个数和
     * @param start 数组的起始位置
     * @return
     */
    private List<List<Integer>> kSum(int[] nums, int target, int k, int start) {
        List<List<Integer>> result = Lists.newArrayList();
        if (k > 2) {//turn to k-1 sum
            for (int i = start; i < nums.length - k; i++) {
                if (i > 0 && nums[i - 1] == nums[i]) continue;
                for (List<Integer> list : kSum(nums, target - nums[i], k - 1, i + 1)) {
                    List<Integer> temp = Lists.newArrayList(nums[i]);
                    temp.addAll(list);
                    result.add(temp);
                }
            }
        } else {//2 sum
            HashSet<Integer> set = new HashSet<>();
            for (int i = start; i < nums.length; i++)
                if (set.contains(target - nums[i])) {
                    result.add(Lists.newArrayList(target - nums[i], nums[i]));
                    for (; i + 1 < nums.length && nums[i + 1] == nums[i]; i++);
                } else set.add(nums[i]);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        List<List<Integer>> expected = Lists.newArrayList(Lists.newArrayList(-2, -1, 1, 2),
                Lists.newArrayList(-2, 0, 0, 2), Lists.newArrayList(-1, 0, 0, 1));
        Assert.assertEquals(expected, fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, 0));
    }

}
