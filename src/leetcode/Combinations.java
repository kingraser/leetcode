/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
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
            深搜遍历所有组合，返回长度为k的
    */

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0, size = (1 << n); i < size; i++) {
            if (Integer.bitCount(i) != k) continue;
            List<Integer> list = new ArrayList<>(k);
            for (int j = 1, l = i; l > 0; l >>= 1, j++)
                if ((l & 1) == 1) list.add(j);
            result.add(list);
        }
        return result;
    }

    public List<List<Integer>> combineII(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k >= 0 && k <= n) dfs(result, new ArrayDeque<>(), n, k, 1);
        return result;
    }

    private void dfs(List<List<Integer>> result, Deque<Integer> deque, int n, int k, int start) {
        if (deque.size() == k) result.add(new ArrayList<>(deque));
        else for (int i = start; n - i + 1 >= k - deque.size(); i++) {
            deque.addLast(i);
            dfs(result, deque, n, k, i + 1);
            deque.pollLast();
        }
    }

    @Test
    public void test() {
        Set<List<Integer>> expected = Stream.of(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(1, 4),
                Arrays.asList(2, 3), Arrays.asList(2, 4), Arrays.asList(3, 4)).collect(Collectors.toSet());
        Assert.assertEquals(expected, new HashSet<>(combine(4, 2)));
        Assert.assertEquals(expected, new HashSet<>(combineII(4, 2)));
    }
}
