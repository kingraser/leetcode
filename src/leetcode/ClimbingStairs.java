/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class ClimbingStairs {

  /*    
  You are climbing a stair case. It takes n steps to reach to the top.    
  Each time you can either climb 1 or 2 steps. 
  In how many distinct ways can you climb to the top? 
  */

  /*
  Let f(n) represents ways to get n step.
  To get to n, you can climb 1 step from n-1 or 2 steps from n-2
  So f(n)=f(n−1)+f(n−2)
  */

  public int climbStairs(int n) {
    int prev = 0, cur = 1;
    for (; n-- > 0; prev = (cur += prev) - prev);
    return cur;
  }

  @Test
  public void test() {
    assertEquals(3, climbStairs(3));
  }

}
