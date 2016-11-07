/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年11月7日;
//-------------------------------------------------------
public class MinimumMovestoEqualArrayElements {

  /*
  Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, where a move is incrementing n - 1 elements by 1.
  
  Example:
  
  Input:
  [1,2,3]
  
  Output:
  3
  
  Explanation:
  Only three moves are needed (remember each move increments two elements):
  
  [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
  */

  @Test
  public void test() {
    minMoves(new int[] { 1, 2, 3 });
  }

  public int minMoves(int[] nums) {
    int sum = 0, min = Integer.MAX_VALUE;
    for (int i : nums) {
      sum += i;
      if (i < min) min = i;
    }
    return sum - min * nums.length;
  }
}
