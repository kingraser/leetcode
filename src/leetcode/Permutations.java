/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月10日<p>
//-------------------------------------------------------
public class Permutations {
    /*
    Given a collection of numbers, return all possible permutations.
    
    For example,
    [1,2,3] have the following permutations:
    [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1]. 
    */

    public List<List<Integer>> permute(int[] num) {
        List<LinkedList<Integer>> lists = permuteII(num);
        List<List<Integer>> result = Lists.newArrayListWithCapacity(lists.size());
        for (List<Integer> list : lists)
            result.add(list);
        return result;
    }

    public List<LinkedList<Integer>> permuteII(int[] num) {
        List<LinkedList<Integer>> result = Lists.newArrayListWithCapacity(getPermuteNum(num.length));
        if (num.length == 0) result.add(Lists.newLinkedList());
        for (int i = 0; i < num.length; i++) {
            swap(num, 0, i);
            List<LinkedList<Integer>> lists = permuteII(Arrays.copyOfRange(num, 1, num.length));
            for (LinkedList<Integer> list : lists)
                list.addFirst(num[0]);
            result.addAll(lists);
        }
        return result;
    }

    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    private int getPermuteNum(int n) {
        int result = n;
        for (; --n > 1; result *= n);
        return result;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        Assert.assertEquals(1, getPermuteNum(1));
        Assert.assertEquals(2, getPermuteNum(2));
        Assert.assertEquals(6, getPermuteNum(3));
        Assert.assertEquals(24, getPermuteNum(4));
        Assert.assertEquals(120, getPermuteNum(5));
        Assert.assertEquals(Lists.newArrayList(Lists.newArrayList(1, 2, 3), Lists.newArrayList(1, 3, 2),
                Lists.newArrayList(2, 1, 3), Lists.newArrayList(2, 3, 1), Lists.newArrayList(3, 1, 2),
                Lists.newArrayList(3, 2, 1)), permute(new int[] { 1, 2, 3 }));
    }
}
