/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

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

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs(result, Arrays.stream(nums).boxed().collect(Collectors.toList()), 0);
        return result;
    }

    void dfs(List<List<Integer>> result, List<Integer> list, int idx) {
        if (idx == list.size()) result.add(new ArrayList<>(list));
        else for (int i = idx; i < list.size(); i++) {
            if (idx != i) Collections.swap(list, idx, i);
            dfs(result, list, idx + 1);
            if (idx != i) Collections.swap(list, i, idx);
        }
    }

    @Test
    public void test() {
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2),
                Arrays.asList(2, 1, 3), Arrays.asList(2, 3, 1), Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1));
        Assert.assertEquals(new HashSet<>(expected), new HashSet<>(permute(new int[] { 1, 2, 3 })));
    }
}
