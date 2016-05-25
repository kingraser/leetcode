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

    /*
            解法1
    For example,[1,4]可表示为1001,[2,3]可表示为[0110],1代表元素被选取.
    n=4时,10000(五位)足以覆盖所有情况.
    1.用O(1)的Integer.bitCount判断1的个数,不为k略过
    2.取出值为1的位的相应位数
            
            解法2
            根据公式C(n,k)=C(n-1,k-1)+C(n-1,k)
    */

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>(getCombinationCount(n, k));
        for (int i = 0, size = (1 << n); i < size; i++) {
            if (Integer.bitCount(i) != k) continue;
            List<Integer> list = new ArrayList<>(k);
            for (int j = 1, l = i; l > 0; l >>= 1, j++)
                if ((l & 1) == 1) list.add(j);
            result.add(list);
        }
        return result;
    }

    private int getCombinationCount(int n, int k) {
        if ((k << 1) > n) k = n - k;
        Double result = (double) n;
        for (; k > 1; result /= k--, result *= --n);
        return result.intValue();
    }

    public List<List<Integer>> combineII(int n, int k) {
        List<List<Integer>> result = new ArrayList<>(getCombinationCount(n, k));
        if (k == 1) for (int i = 0; i < n; result.add(Stream.of(++i).collect(Collectors.toList())));
        else if (k == n) {
            List<Integer> inner = new ArrayList<>(n);
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

    @Test
    public void test() {
        Set<List<Integer>> expected = Stream.of(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(1, 4),
                Arrays.asList(2, 3), Arrays.asList(2, 4), Arrays.asList(3, 4)).collect(Collectors.toSet());
        Assert.assertEquals(expected, new HashSet<>(combine(4, 2)));
        Assert.assertEquals(expected, new HashSet<>(combineII(4, 2)));
    }
}
