/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class Subsets {
    /*
    Given a set of distinct integers, nums, return all possible subsets.
    
    Note:
    
    Elements in a subset must be in non-descending order.
    The solution set must not contain duplicate subsets.
    
    For example,
    If nums = [1,2,3], a solution is:
    
    [
      [3],
      [1],
      [2],
      [1,2,3],
      [1,3],
      [2,3],
      [1,2],
      []
    ]    
    */

    public List<List<Integer>> subsets(int[] nums) {
        int size = 1 << nums.length;
        List<List<Integer>> result = Lists.newArrayListWithCapacity(size);
        Arrays.sort(nums);
        for (int i = 0; i < size; i++) {
            List<Integer> list = Lists.newArrayListWithCapacity(nums.length);
            for (int j = 0, l = i; l > 0; l >>= 1, j++)
                if ((l & 1) == 1) list.add(nums[j]);
            result.add(list);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        Set<List<Integer>> expected = Sets.newHashSet(Lists.newArrayList(1), Lists.newArrayList(2),
                Lists.newArrayList(3), Lists.newArrayList(1, 2), Lists.newArrayList(1, 3), Lists.newArrayList(2, 3),
                Lists.newArrayList(1, 2, 3), Lists.newArrayList());
        Assert.assertEquals(expected, Sets.newHashSet(subsets(new int[] { 1, 2, 3 })));
    }
}
