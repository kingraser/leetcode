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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class CombinationSumIII {

    /*
    Find all possible combinations of k numbers that add up to a number n, 
    given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
    Ensure that numbers within the set are sorted in ascending order.    
    Example 1:    
    Input: k = 3, n = 7
    Output:    
    [[1,2,4]] 
    Example 2:    
    Input: k = 3, n = 9
    Output:    
    [[1,2,6], [1,3,5], [2,3,4]]    
    */

    @Test
    public void test() {
        Assert.assertEquals(Stream.of(Arrays.asList(1, 2, 6), Arrays.asList(1, 3, 5), Arrays.asList(2, 3, 4))
                .collect(Collectors.toSet()), new HashSet<>(combinationSum3(3, 9)));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combine(res, new ArrayDeque<>(9), n, k, 1, 9);
        return res;
    }

    private void combine(List<List<Integer>> r, Deque<Integer> deque, int sum, int k, int start, int end) {
        if (!isPossible(sum, k, start, end)) return;
        if (k == 1) {
            deque.addLast(sum);
            r.add(new ArrayList<>(deque));
            deque.pollLast();
        } else for (int size = r.size(); isPossible(sum, k, start, end); size = r.size()) {
            deque.addLast(start);
            combine(r, deque, sum - start, k - 1, ++start, end);
            if (r.size() > size) end = r.get(size).get(r.get(size).size() - 1);//new end
            deque.pollLast();
        }
    }

    //case1 剩下的元素至少k个
    //case2 理论上的最小和小于等于sum
    //case3 理论上的最大和大于等于sum
    private boolean isPossible(int sum, int k, int start, int end) {
        return start + k - 2 < end && (k - 1 + (start << 1)) * k <= (sum <<= 1) && ((end << 1) + 1 - k) * k >= sum;
    }

}
