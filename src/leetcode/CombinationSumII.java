/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class CombinationSumII {

    /*
    Given a collection of candidate numbers (C) and a target number (T), 
    find all unique combinations in C where the candidate numbers sums to T.
    Each number in C may only be used once in the combination.
    
    Note:
    
    All numbers (including target) will be positive integers.
    Elements in a combination (a1, a2, … , ak) must be in non-descending order. 
    (ie, a1 ≤ a2 ≤ … ≤ ak).
    The solution set must not contain duplicate combinations.
    
    For example, given candidate set 10,1,2,7,6,1,5 and target 8,
    A solution set is:
    [1, 7]
    [1, 2, 5]
    [2, 6]
    [1, 1, 6]    
    */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Set<List<Integer>> result = Sets.newHashSet();
        dfs(candidates, target, 0, Lists.newLinkedList(), result);
        return Lists.newArrayList(result);
    }

    private void dfs(int[] n, int gap, int start, LinkedList<Integer> list, Set<List<Integer>> result) {
        if (gap == 0) {
            result.add(Lists.newArrayList(list));
            return;
        }
        for (int i = start; i < n.length && gap >= n[i]; list.addLast(n[i]), dfs(n, gap - n[i], ++i, list, result), list
                .pollLast());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        Set<List<Integer>> expected = Sets.newHashSet(Lists.newArrayList(1, 7), Lists.newArrayList(1, 2, 5),
                Lists.newArrayList(2, 6), Lists.newArrayList(1, 1, 6));
        Assert.assertEquals(expected, Sets.newHashSet(combinationSum2(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8)));
    }

}
