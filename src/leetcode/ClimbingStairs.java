/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class ClimbingStairs {

    /*    
    You are climbing a stair case. It takes n steps to reach to the top.    
    Each time you can either climb 1 or 2 steps. 
    In how many distinct ways can you climb to the top? 
    */

    public int climbStairs(int n) {
        if (n < 3) return n;
        int stepTwo = 1, stepOne = 2, result = 0;
        for (int i = 0; i < n - 2; result = stepOne + stepTwo, stepTwo = stepOne, stepOne = result, i++);
        return result;
    }

}
