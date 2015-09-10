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
public class ThreeSum {
    /*
    给一int数组和int target,求三下标使和为target(本题锁定为0).需要去重    
    For example, given array S = {-1 0 1 2 -1 -4},  
        A solution set is:
        (-1, 0, 1)
        (-1, -1, 2)    
    思路:把问题转为2sum,去重的问题在于转化2sum的头指针(第二个下标)在第一个下标之后
    */

    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 0; i < num.length - 2; twoSum(num, 1 + i, -num[i++], res))
            for (; i > 0 && i < num.length - 2 && num[i] == num[i - 1]; i++);//重复的元素不用计算
        return res;
    }

    public void twoSum(int[] s, int start, int target, List<List<Integer>> res) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = start; i < s.length; i++)
            if (set.contains(target - s[i])) {
                res.add(Lists.newArrayList(-target, target - s[i], s[i]));
                for (; i + 1 < s.length && s[i + 1] == s[i]; i++);
            } else set.add(s[i]);
    }

    @Test
    public void test() {
        List<List<Integer>> expected = Lists.newArrayList();
        expected.add(Lists.newArrayList(-1, 0, 1));
        expected.add(Lists.newArrayList(-1, -1, 2));
        Assert.assertEquals(expected, threeSum(new int[] { -1, 0, 1, 2, -1, -4 }));
    }

}
