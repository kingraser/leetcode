/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
            if (map.containsKey(target - nums[i])) return new int[] { Math.min(i, map.get(target - nums[i])) + 1,
                    Math.max(i, map.get(target - nums[i])) + 1 };
            else map.put(nums[i], i);
        return null;
    }

    public int[] twoSumII(int[] nums, int target) {
        List<Pair> pairs = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++)
            pairs.add(new Pair(nums[i], i));
        Collections.sort(pairs, new Comparator<Pair>() {

            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.value.compareTo(p2.value);
            }
        });
        int left = 0, right = nums.length - 1;
        while (pairs.get(left).value + pairs.get(right).value != target)
            if (pairs.get(left).value + pairs.get(right).value > target) right--;
            else left++;
        return new int[] { 1 + Math.min(pairs.get(left).place, pairs.get(right).place),
                1 + Math.max(pairs.get(left).place, pairs.get(right).place) };
    }

    public static class Pair {

        Integer value, place;

        public Pair(int v, int p) {
            value = v;
            place = p;
        }

    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[] { 1, 2 }, twoSum(new int[] { 2, 7, 11, 15 }, 9));
    }

    @Test
    public void testII() {
        Assert.assertArrayEquals(new int[] { 1, 2 }, twoSumII(new int[] { 2, 7, 11, 15 }, 9));
    }

}
