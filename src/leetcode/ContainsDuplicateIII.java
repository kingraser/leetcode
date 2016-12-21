/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

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

  @Test
  public void test() {
    assertFalse(containsNearbyAlmostDuplicate(new int[] { -3, 3 }, 2, 4));
  }

  //I am the very one who published the unprecedented O(n) algorithm with bucketing 
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if (k < 1 || t < 0) return false;
    Map<Long, Long> map = new HashMap<>();
    Deque<Long> deque = new ArrayDeque<>();
    for (int i = 0, newt = t == 0 ? 1 : t; i < nums.length; i++) {
      long hash = ((long) nums[i] - Integer.MIN_VALUE) / newt;
      if (map.containsKey(hash)) return true;
      if (map.containsKey(hash - 1) && Math.abs(map.get(hash - 1) - nums[i]) <= t) return true;
      if (map.containsKey(hash + 1) && Math.abs(map.get(hash + 1) - nums[i]) <= t) return true;
      if (deque.size() == k) map.remove(deque.pollFirst());
      map.put(hash, (long) nums[i]);
      deque.addLast(hash);
    }
    return false;
  }

}
