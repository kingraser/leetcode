/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class PerfectSquares {
    /*
    1数论法
        Lagrange's four-square theorem
        任一自然数可以写成4个平方数的和
    
        拓展：任一自然数
                    是平方数,当且仅当该数的质因数在质因分解中出现偶数次。
                    是两个平方数的和，当且仅当其模4余3的质因数出现
                    是三个平方数的和，当且仅当该数不是 4^a(8b+7)的形式（a，b为整数）
                    
    2DP法
        初始化，令dp[y * y] = 1，其中y * y <= n
        状态转移方程：dp[x + y * y] = min(dp[x + y * y], dp[x] + 1)
    */

    public int numSquares(int n) {
        for (; (n & 3) == 0; n >>= 2);
        if (n % 8 == 7) return 4;//是4^a(8b+7)形式
        for (int a = 0, b = (int) Math.sqrt(n - a * a); a * a <= n; a++, b = (int) Math.sqrt(n - a * a))
            if (a * a + b * b == n) return a == 0 ? 1 : 2;
        return 3;
    }

    public int numSquaresII(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1, n + 1, 4);//as the theorem above
        for (int i = 0; i <= n; i++)
            for (int j = 1; i + j * j <= n; dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1), j++);
        return dp[n];
    }

    @Test
    public void test() {
        Assert.assertEquals(4, numSquares(60));
        Assert.assertEquals(2, numSquaresII(10));
    }

}
