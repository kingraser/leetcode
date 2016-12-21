/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.stream.Collectors;

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

  @Test
  public void test() {
    assertTrue(containsDuplicate(new int[] { 1, 2, 3, 1 }));
    assertFalse(containsDuplicate(new int[] { 1, 2, 3 }));
  }

}
