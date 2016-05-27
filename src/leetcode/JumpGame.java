/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月15日<p>
//-------------------------------------------------------
public class JumpGame {

    /*
    Given an array of non-negative integers, you are initially positioned at the first index of the array.    
    Each element in the array represents your maximum jump length at that position.    
    Determine if you are able to reach the last index.
    
    For example:
    A = [2,3,1,1,4], return true.    
    A = [3,2,1,0,4], return false. 
    
            由于每层最多可以跳 A[i]步,也可以跳 0或 1步,
            因此如果能到达最高层,则说明每一层都可以到达。
            有了这个条件,说明可以用贪心法。
            思路一:正向,从 0出发,一层一层往上跳,
            看最后能不能超过最高层,能超过,说明能到达,否则不能到达。
            思路二:逆向,从最高层下楼梯,一层一层下降,看最后能不能下降到第 0层。
            思路三:如果不敢用贪心,可以用动规,
            设状态为 f[i],表示从第 0层出发,走到 A[i]时剩余的最大步数
            则状态转移方程为:
        f[i] = max(f[i − 1], A[i − 1]) − 1, i > 0
    */

    public boolean canJump(int A[]) {
        int reach = 1, n = A.length; // 最右能跳到哪里
        for (int i = 0; i < reach && reach < n; reach = Math.max(reach, i + 1 + A[i]), ++i);
        return reach >= n;
    }

    public boolean canJumpII(int[] A) {
        if (A.length == 0) return true;
        int leftMost = A.length - 1;// 逆向下楼梯,最左能下降到第几层
        for (int i = A.length - 2; i >= 0; --i)
            if (i + A[i] >= leftMost) leftMost = i;
        return leftMost == 0;
    }

    public boolean canJumpIII(int A[]) {
        int[] f = new int[A.length];
        for (int i = 1; i < A.length; i++) {
            f[i] = Math.max(f[i - 1], A[i - 1]) - 1;
            if (f[i] < 0) return false;;
        }
        return f[A.length - 1] >= 0;
    }

    @Test
    public void test() {
        Assert.assertTrue(canJump(new int[] { 2, 3, 1, 1, 4 }));
        Assert.assertTrue(canJumpII(new int[] { 2, 3, 1, 1, 4 }));
        Assert.assertTrue(canJumpIII(new int[] { 2, 3, 1, 1, 4 }));
        Assert.assertFalse(canJump(new int[] { 3, 2, 1, 0, 4 }));
        Assert.assertFalse(canJumpII(new int[] { 3, 2, 1, 0, 4 }));
        Assert.assertFalse(canJumpIII(new int[] { 3, 2, 1, 0, 4 }));
    }

}
