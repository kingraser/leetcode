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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

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
        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void dfs(int[] n, int gap, int start, List<Integer> list, List<List<Integer>> result) {
        if (gap == 0) result.add(new ArrayList<>(list));
        else for (int i = start; i < n.length && gap >= n[i]; i++) {
            list.add(n[i]);
            dfs(n, gap - n[i], i, list, result);
            list.remove(list.size() - 1);
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(Stream.of(Arrays.asList(2, 2, 3), Arrays.asList(7)).collect(Collectors.toSet()),
                new HashSet<>(combinationSum(new int[] { 2, 3, 6, 7 }, 7)));
    }

}
