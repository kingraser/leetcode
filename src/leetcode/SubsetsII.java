/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class SubsetsII {
    /*
    Given a collection of integers that might contain duplicates, nums, return all possible subsets.    
    Note:    
    Elements in a subset must be in non-descending order.
    The solution set must not contain duplicate subsets.
    
    For example,
    If nums = [1,2,2], a solution is:
    
    [
      [2],
      [1],
      [1,2,2],
      [2,2],
      [1,2],
      []
    ]    
    */

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        Assert.assertEquals(
                Sets.newHashSet(Lists.newArrayList(2), Lists.newArrayList(1), Lists.newArrayList(1, 2, 2),
                        Lists.newArrayList(2, 2), Lists.newArrayList(1, 2), Lists.newArrayList()),
                Sets.newHashSet(subsetsWithDupII2(new int[] { 1, 2, 2 })));
    }

    //递归I
    //I1增量构造1
    public List<List<Integer>> subsetsWithDup(int[] A) {
        Arrays.sort(A);
        List<List<Integer>> result = Lists.newLinkedList();
        dfs(A, 0, Lists.newLinkedList(), result);
        return result;
    }

    private void dfs(int[] A, int start, LinkedList<Integer> path, List<List<Integer>> result) {
        result.add(Lists.newArrayList(path));
        for (int i = start; i < A.length; i++) {
            if (i != start && A[i] == A[i - 1]) continue;
            path.add(A[i]);
            dfs(A, i + 1, path, result);
            path.removeLast();
        }
    }

    //I1增量构造2
    public List<List<Integer>> subsetsWithDupI(int[] A) {
        List<List<Integer>> result = Lists.newLinkedList();
        Map<Integer, Integer> countMap = Maps.newTreeMap();
        for (int i : A)
            countMap.put(i, 1 + (countMap.containsKey(i) ? countMap.get(i) : 0));
        dfs(Lists.newArrayList(countMap.entrySet()), 0, Lists.newLinkedList(), result);
        return result;
    }

    void dfs(List<Entry<Integer, Integer>> elems, int start, LinkedList<Integer> path, List<List<Integer>> result) {
        if (start == elems.size()) {
            result.add(Lists.newArrayList(path));
            return;
        }
        for (int i = 0, size = elems.get(start).getValue(); i <= size; i++) {
            for (int j = 0; j < i; path.add(elems.get(start).getKey()), ++j);
            dfs(elems, start + 1, path, result);
            for (int j = 0; j < i; path.removeLast(), ++j);
        }
    }

    //I2位向量法
    public List<List<Integer>> subsetsWithDupI2(int[] A) {
        List<List<Integer>> result = Lists.newLinkedList();
        Map<Integer, Integer> countMap = Maps.newTreeMap();
        for (int i : A)
            countMap.put(i, 1 + (countMap.containsKey(i) ? countMap.get(i) : 0));
        dfs(A, Lists.newArrayList(countMap.values()), new int[countMap.size()], 0, result);
        return result;
    }

    private void dfs(int[] A, List<Integer> count, int[] selected, int start, List<List<Integer>> result) {
        if (start == selected.length) {
            save(A, result, selected);
            return;
        }
        for (int i = 0; i <= count.get(start); selected[start] = i++, dfs(A, count, selected, start + 1, result));
    }

    private void save(int[] A, List<List<Integer>> result, int[] selected) {
        List<Integer> path = Lists.newArrayListWithCapacity(selected.length);
        for (int i = 0; i < selected.length; i++)
            for (int j = 0; j < selected[i]; path.add(A[i]), j++);
        result.add(path);
    }

    //II迭代

    //II1增量构造 bfs
    @SuppressWarnings("unchecked")
    public List<List<Integer>> subsetsWithDupII(int[] A) {
        List<List<Integer>> result = Lists.newArrayList(new ArrayList<Integer>());
        Arrays.sort(A);
        for (int i = 0, j = 0, size = 0, previousSize = 0; i < A.length; previousSize = size, i++)
            for (j = 0, size = result.size(); j < size; j++)
                if (i == 0 || A[i] != A[i - 1] || j >= previousSize) {
                    result.add(Lists.newArrayList(result.get(j)));
                    result.get(result.size() - 1).add(A[i]);
                }
        return result;
    }

    //II2 二进制法
    public List<List<Integer>> subsetsWithDupII2(int[] A) {
        Set<List<Integer>> result = Sets.newHashSet();
        Arrays.sort(A);
        for (int i = 0, size = 1 << A.length; i < size; i++) {
            List<Integer> list = Lists.newArrayList();
            for (int j = 0, l = i; l > 0; l >>= 1, j++)
                if ((l & 1) == 1) list.add(A[j]);
            result.add(list);
        }
        return Lists.newArrayList(result);
    }

}
