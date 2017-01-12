package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.TreeSet;

import org.junit.Test;

/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年10月24日;
//-------------------------------------------------------
public class ThirdMaximumNumber {
  /*
  Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
  
  Example 1:
  
  Input: [3, 2, 1]
  
  Output: 1
  
  Explanation: The third maximum is 1.
  
  Example 2:
  
  Input: [1, 2]
  
  Output: 2
  
  Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
  
  Example 3:
  
  Input: [2, 2, 3, 1]
  
  Output: 1
  
  Explanation: Note that the third maximum here means the third maximum distinct number.
  Both numbers with value 2 are both considered as second maximum.  
  */

  @Test
  public void test() {
    assertEquals(1, thirdMax(new int[] { 3, 2, 1 }));
    assertEquals(2, thirdMax(new int[] { 1, 2 }));
    assertEquals(1, thirdMax(new int[] { 2, 2, 3, 1 }));
  }

  public int thirdMax(int[] nums) {
    TreeSet<Integer> queue = new TreeSet<>();
    for (int i : nums) {
      queue.add(i);
      if (queue.size() > 3) queue.pollFirst();
    }
    return queue.size() == 3 ? queue.pollFirst() : queue.pollLast();
  }
}
