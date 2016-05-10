/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
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
            设f(n)表示爬n阶楼梯的不同方法数,为了爬到第n阶楼梯,有两个选择:
                从第n−1阶前进1步;
                从第n−1阶前进2步;    
            因此,有f(n)=f(n−1)+f(n−2)。这是一个斐波那契数列。
            方法1,递归,太慢;方法2,迭代。
            方法3,数学公式。
    */

    public int climbStairs(int n) {
        int prev = 0, cur = 1;
        for (int i = 1; i <= n; i++) {
            int tmp = cur;
            cur += prev;
            prev = tmp;
        }
        return cur;
    }

    public int climbStairsI(int n) {
        return (int) ((Math.pow((1 + Math.sqrt(5)) / 2, n + 1) + Math.pow((1 - Math.sqrt(5)) / 2, n + 1)) / Math.sqrt(5)
                + 0.5);
    }

    @Test
    public void test() {
        Assert.assertEquals(3, climbStairs(3));
        Assert.assertEquals(3, climbStairsI(3));
    }

}
