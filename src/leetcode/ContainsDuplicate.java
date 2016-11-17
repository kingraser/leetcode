/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

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
        return Arrays.stream(nums).boxed().collect(Collectors.toSet()).size() < nums.length;
    }

    public boolean containsDuplicateII(int[] nums) {
        Set<Integer> set = new HashSet<>();
        return Arrays.stream(nums).filter(i -> !set.add(i)).findFirst().isPresent();
    }

    @Test
    public void test() {
        Assert.assertTrue(containsDuplicate(new int[] { 1, 2, 3, 1 }));
        Assert.assertFalse(containsDuplicate(new int[] { 1, 2, 3 }));
    }

}
