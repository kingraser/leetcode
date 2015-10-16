/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class PalindromePartitioningII {

    /*
    Given a string s, partition s such that every substring of the partition is a palindrome.    
    Return the minimum cuts needed for a palindrome partitioning of s.    
    For example, given s = "aab",
    Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut. 
    */

    /*
    定义状态 f(i,j) 表示区间 [i,j] 之间最小的 cut 数,则状态转移方程为
    f(i,j)=min{f(i,k)+f(k+1,j)},i≤k≤j,0≤i≤j<n
    这是一个二维函数,实际写代码比较麻烦。
    所以要转换成一维DP。如果每次,从i往右扫描,每找到一个回文就算一次DP的话,就可以
    转换为f(i)=区间[i,n-1]之间最小的cut数,n为字符串长度,则状态转移方程为
    f(i)=min{f(j+1)+1},i≤j<n
    一个问题出现了,就是如何判断[i,j]是否是回文?每次都从i到j比较一遍?
    太浪费了,这里也是一个DP问题。
    定义状态P[i][j]=true if S[i,j]为回文,那么
    P[i][j]=str[i]==str[j]&&P[i+1][j-1]
    */

    private static boolean[][] palindromeArray;

    private static int[] dp;

    public int minCut(String s) {
        char[] sArray = s.toCharArray();
        buildPalindromeArray(sArray);
        dp = new int[s.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = s.length() - 1; i > -1; i--) {
            if (isPalindrome(i, s.length() - 1)) {
                dp[i] = 0;
                continue;
            }
            for (int j = i; j < s.length() - 1; j++)
                if (isPalindrome(i, j)) dp[i] = Math.min(dp[i], 1 + dp[j + 1]);
        }
        return dp[0];
    }

    private boolean isPalindrome(int i, int j) {
        return palindromeArray[i][j];
    }

    private void buildPalindromeArray(char[] s) {
        palindromeArray = new boolean[s.length][s.length];
        for (int i = 0; i < s.length - 1; i++) {
            palindromeArray[i][i] = true;
            palindromeArray[i][i + 1] = s[i] == s[i + 1];
        }
        palindromeArray[s.length - 1][s.length - 1] = true;
        for (int j = 2; j < s.length; j++)
            for (int i = 0; i < s.length - j; i++)
                palindromeArray[i][i + j] = palindromeArray[i + 1][i + j - 1] && s[i] == s[i + j];
    }
}
