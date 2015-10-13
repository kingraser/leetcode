/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class Combinations {
    /*
    Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
    
    For example,
    If n = 4 and k = 2, a solution is:
    
    [
      [2,4],
      [3,4],
      [2,3],
      [1,2],
      [1,3],
      [1,4],
    ]
    */

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = Lists.newArrayListWithCapacity(getCombinationCount(n, k));
        for (int i = 0, size = (1 << n); i < size; i++) {
            if (Integer.bitCount(i) != k) continue;
            List<Integer> list = Lists.newArrayListWithCapacity(k);
            for (int j = 1, l = i; l > 0; l >>= 1, j++)
                if ((l & 1) == 1) list.add(j);
            result.add(list);
        }
        return result;
    }

    private int getCombinationCount(int n, int k) {
        if ((k << 1) > n) k = n - k;
        Double result = (double) n;
        for (; k > 1; result *= --n, result /= k--);
        return result.intValue();
    }

    public List<List<Integer>> combineII(int n, int k) {
        List<List<Integer>> result = Lists.newArrayListWithCapacity(getCombinationCount(n, k));
        if (k == 1) for (int i = 0; i < n; result.add(Lists.newArrayList(++i)));
        else if (k == n) {
            List<Integer> inner = Lists.newArrayListWithCapacity(n);
            for (int i = 0; i < n; inner.add(++i));
            result.add(inner);
        } else {
            for (List<Integer> sub : combineII(n - 1, k - 1)) {
                sub.add(n);
                result.add(sub);
            }
            result.addAll(combineII(n - 1, k));
        }
        return result;
    }

    @Test
    public void testGetCombinationCount() {
        Assert.assertEquals(10, getCombinationCount(5, 2));
        Assert.assertEquals(10, getCombinationCount(5, 3));
        Assert.assertEquals(6, getCombinationCount(4, 2));
        Assert.assertEquals(35, getCombinationCount(7, 3));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        Set<List<Integer>> expected = Sets.newHashSet(Lists.newArrayList(1, 2), Lists.newArrayList(1, 3),
                Lists.newArrayList(1, 4), Lists.newArrayList(2, 3), Lists.newArrayList(2, 4), Lists.newArrayList(3, 4));
        Assert.assertEquals(expected, Sets.newHashSet(combine(4, 2)));
        Assert.assertEquals(expected, Sets.newHashSet(combineII(4, 2)));
    }
}
