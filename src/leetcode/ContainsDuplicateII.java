/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class ContainsDuplicateII {

    /*
    Given an array of integers and an integer k, 
    find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] 
    and the difference between i and j is at most k. 
    */

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) return true;
            else map.put(nums[i], i);
        return false;
    }

}
