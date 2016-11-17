/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年11月16日;
//-------------------------------------------------------
public class OneThreeTwoPattern {
  /*
  Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a
  subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an
  algorithm that takes a list of n numbers as input and checks whether there
  is a 132 pattern in the list.
  
  Note: n will be less than 15,000.
  
  Example 1:
  
  Input: [1, 2, 3, 4]
  
  Output: False
  
  Explanation: There is no 132 pattern in the sequence.
  
  Example 2:
  
  Input: [3, 1, 4, 2]
  
  Output: True
  
  Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
  
  Example 3:
  
  Input: [-1, 3, 2, 0]
  
  Output: True
  
  Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1,
  3, 0] and [-1, 2, 0].
  */

  @Test
  public void test() {
    Assert.assertFalse(find132pattern(new int[] { 1, 2, 3, 4 }));
    Assert.assertTrue(find132pattern(new int[] { 3, 1, 4, 2 }));
    Assert.assertTrue(find132pattern(new int[] { -1, 3, 2, 0 }));
  }

  public boolean find132pattern(int[] nums) {
    Stack<Integer> stack = new Stack<>();
    for (int i = nums.length - 1, two = Integer.MIN_VALUE; i >= 0; stack.push(nums[i--]))
      if (nums[i] < two) return true;
      else for (; !stack.empty() && nums[i] > stack.peek(); two = Math.max(two, stack.pop()));
    return false;
  }
}
