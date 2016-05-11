/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashSet;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class ContainsDuplicate {
    /*
    Given an array of integers, find if the array contains any duplicates. 
    Your function should return true if any value appears at least twice in the array, 
    and it should return false if every element is distinct. 
    */

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>(nums.length);
        for (int i : nums)
            if (!set.add(i)) return true;
        return false;
    }

}
