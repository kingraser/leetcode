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
public class PermutationsII {
    /*
    Given a collection of numbers that might contain duplicates, 
    return all possible unique permutations.
    
    For example,
    [1,1,2] have the following unique permutations:
    [1,1,2], [1,2,1], and [2,1,1]. 
    */

    public List<List<Integer>> permuteUnique(int[] num) {
        return Lists.newArrayList(permute(num));
    }

    public List<LinkedList<Integer>> permute(int[] num) {
        List<LinkedList<Integer>> result = Lists.newArrayListWithCapacity(getPermuteNum(num.length));
        if (num.length == 0) result.add(Lists.newLinkedList());
        for (int i = 0; i < num.length; i++) {
            if (i != 0 && num[i] == num[0]) continue;
            swap(num, 0, i);
            List<LinkedList<Integer>> lists = permute(Arrays.copyOfRange(num, 1, num.length));
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
        Assert.assertEquals(Lists.newArrayList(Lists.newArrayList(1, 1, 2), Lists.newArrayList(1, 2, 1),
                Lists.newArrayList(2, 1, 1)), permuteUnique(new int[] { 1, 1, 2 }));
    }

}
