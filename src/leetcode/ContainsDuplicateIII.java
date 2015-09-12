/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Map;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class ContainsDuplicateIII {

    /*
    Given an array of integers, 
    find out whether there are two distinct indices i and j in the array 
    such that the difference between nums[i] and nums[j] is at most t 
    and the difference between i and j is at most k.   
    */

    //I am the very one who published the unprecedented O(n) algorithm with bucketing 
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new java.util.LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long j = t == 0 ? (long) nums[i] - Integer.MIN_VALUE : (((long) nums[i] - Integer.MIN_VALUE) / t);
            if (map.containsKey(j) || (map.containsKey(j - 1) && Math.abs(map.get(j - 1) - nums[i]) <= t)
                    || (map.containsKey(j + 1) && Math.abs(map.get(j + 1) - nums[i]) <= t))
                return true;
            if (map.keySet().size() == k) map.remove(map.keySet().iterator().next());
            map.put(j, (long) nums[i]);
        }
        return false;
    }

}
