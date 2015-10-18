/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

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

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        List<List<Integer>> expected = Lists.newArrayList(Lists.newArrayList(1, 2, 6), Lists.newArrayList(1, 3, 5),
                Lists.newArrayList(2, 3, 4));
        Assert.assertEquals(expected, combinationSum3(3, 9));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>(45);
        combine(res, new ArrayList<>(9), 0, k, n, 1, 9);
        return res;
    }

    private boolean combine(List<List<Integer>> res, List<Integer> cur, int sum, int k, int n, int start, int end) {
        if (k == 1) {
            if (n - sum >= start && n - sum <= end) {
                cur.add(n - sum);
                res.add(new ArrayList<>(cur));
                cur.remove(cur.size() - 1);
                return true;
            }
            return false;
        }
        //case1 剩下的元素不足k个
        //case2 理论上的最小sum也大于n
        //case3 理论上的最大sum也小于n
        if (k > 10 - start || sum + (((k - 1 + (start << 1)) * k) >> 1) > n
                || sum + (((end << 1) + 1 - k) * k >> 1) < n)
            return false;
        boolean hasFound = false;
        for (int size = res.size(); start < end - k + 2; start++, size = res.size()) {
            cur.add(start);
            if (combine(res, cur, sum + start, k - 1, n, start + 1, end)) {
                hasFound = true;
                end = res.get(size).get(res.get(size).size() - 1);//new end
            } else if (hasFound) start = end - k + 2;//break
            cur.remove(cur.size() - 1);
        }
        return hasFound;
    }

}
