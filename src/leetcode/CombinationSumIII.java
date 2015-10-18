/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<LinkedList<Integer>> res = combinationSum3(k, n, 1, 9);
        List<List<Integer>> result = new ArrayList<>(res.size());
        result.addAll(res);
        return result;
    }

    private LinkedList<LinkedList<Integer>> combinationSum3(int k, int n, int start, int end) {
        LinkedList<LinkedList<Integer>> result = new LinkedList<>();
        if (k == 2) {
            if (n >= (end << 1) || n <= (start << 1)) return result;
            for (; n - start > end; start++);
            for (; start < n - start; start++) {
                LinkedList<Integer> tempList = new LinkedList<Integer>();
                tempList.add(start);
                tempList.add(n - start);
                result.add(tempList);
            }
        } else {
            boolean hasFound = false;
            for (; start < end - k + 2; start++) {
                LinkedList<LinkedList<Integer>> tempList = combinationSum3(k - 1, n - start, start + 1, end);
                if (!tempList.isEmpty()) {
                    hasFound = true;
                    end = tempList.getFirst().getLast();
                    for (LinkedList<Integer> list : tempList)
                        list.addFirst(start);
                    result.addAll(tempList);
                } else if (hasFound) break;
            }
        }
        return result;
    }

}
