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
        List<List<Integer>> expected = Arrays.asList(Arrays.asList(1, 2, 6), Arrays.asList(1, 3, 5),
                Arrays.asList(2, 3, 4));
        Assert.assertEquals(new HashSet<>(expected), new HashSet<>(combinationSum3(3, 9)));
    }

    private static final int end = 9;

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayDeque<>(9), n, k, 1);
        return res;
    }

    private void dfs(List<List<Integer>> r, Deque<Integer> deque, int sum, int k, int start) {
        if (k == 0 && sum == 0) r.add(new ArrayList<>(deque));
        else while (isPossible(sum, k, start)) {
            deque.addLast(start);
            dfs(r, deque, sum - start, k - 1, ++start);
            deque.pollLast();
        }
    }

    //case1 剩下的元素至少k个
    //case2 理论上的最小和小于等于sum
    //case3 理论上的最大和大于等于sum
    private boolean isPossible(int sum, int k, int start) {
        return start + k - 1 <= end && (k - 1 + (start << 1)) * k <= (sum <<= 1) && ((end << 1) + 1 - k) * k >= sum;
    }

}
