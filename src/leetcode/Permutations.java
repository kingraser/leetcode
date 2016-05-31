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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<List<Integer>> permute(int[] num) {
        return permute(num, 0);
    }

    private List<List<Integer>> permute(int[] num, int start) {
        List<List<Integer>> result = new ArrayList<>();
        if (start == num.length) result.add(new ArrayList<>(num.length));
        for (int i = start; i < num.length; swap(num, start, i++)) {
            swap(num, start, i);
            permute(num, start + 1).forEach(l -> {
                l.add(num[start]);
                result.add(l);
            });
        }
        return result;
    }

    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    @Test
    public void test() {
        Set<List<Integer>> expected = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2), Arrays.asList(2, 1, 3),
                Arrays.asList(2, 3, 1), Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1)).collect(Collectors.toSet());
        Assert.assertEquals(expected, new HashSet<>(permute(new int[] { 1, 2, 3 })));
    }
}
