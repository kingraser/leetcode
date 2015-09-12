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
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class CombinationSum {

    /*
    Given a set of candidate numbers (C) and a target number (T), 
    find all unique combinations in C where the candidate numbers sums to T.
    The same repeated number may be chosen from C unlimited number of times.
    
    Note:
    
        All numbers (including target) will be positive integers.
        Elements in a combination (a1, a2, … , ak) must be in non-descending order. 
        (ie, a1 ≤ a2 ≤ … ≤ ak).
        The solution set must not contain duplicate combinations.
    
    For example, given candidate set 2,3,6,7 and target 7,
    A solution set is:
    [7]
    [2, 2, 3]
    
    */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = Lists.newArrayList();
        dfs(candidates, target, 0, Lists.newLinkedList(), result);
        return result;
    }

    private void dfs(int[] n, int gap, int start, LinkedList<Integer> list, List<List<Integer>> result) {
        if (gap == 0) {
            result.add(Lists.newArrayList(list));
            return;
        }
        for (int i = start; i < n.length && gap >= n[i]; list.addLast(n[i]), dfs(n, gap - n[i], i, list, result), list
                .pollLast(), i++);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        List<List<Integer>> expected = Lists.newArrayList(Lists.newArrayList(2, 2, 3), Lists.newArrayList(7));
        Assert.assertEquals(expected, combinationSum(new int[] { 2, 3, 6, 7 }, 7));
    }

}
