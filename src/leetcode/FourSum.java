/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
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
    public static List<List<Integer>> kSum(int[] nums, int target, int k, int start) {
        if (k == 2) return TwoSum(nums, target, start);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = start; i < nums.length - k; i++)
            if (i > start && nums[i - 1] == nums[i]) continue;
            else for (List<Integer> list : kSum(nums, target - nums[i], k - 1, i + 1)) {
                list.add(0, nums[i]);
                result.add(list);
            }
        return result;
    }

    private static List<List<Integer>> TwoSum(int[] nums, int target, int start) {
        List<List<Integer>> result = new ArrayList<>();
        for (int l = start, r = nums.length - 1, v; l < r;)
            if (l > start && nums[l - 1] == nums[l]) l++;
            else if ((v = nums[l] + nums[r]) == target) result.add(Lists.newArrayList(nums[l++], nums[r]));
            else if (v < target) l++;
            else r--;
        return result;
    }

    @Test
    public void test() {
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(-2, -1, 1, 2), Arrays.asList(-2, 0, 0, 2),
                Arrays.asList(-1, 0, 0, 1));
        Assert.assertEquals(new HashSet<>(expected), new HashSet<>(fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, 0)));
        expected = Arrays.asList(Arrays.asList(0, 0, 0, 0));
        Assert.assertEquals(expected, fourSum(new int[] { 0, 0, 0, 0, 0, 0 }, 0));
    }

}
