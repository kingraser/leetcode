/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class ScrambleString {
  /*
  Given a string s1, 
  we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.    
  Below is one possible representation of s1 = "great":
  
      great
     /    \
    gr    eat
   / \    /  \
  g   r  e   at
             / \
            a   t
  
  To scramble the string, we may choose any non-leaf node and swap its two children.    
  For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
  
      rgeat
     /    \
    rg    eat
   / \    /  \
  r   g  e   at
             / \
            a   t
  
  We say that "rgeat" is a scrambled string of "great".    
  Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
  
      rgtae
     /    \
    rg    tae
   / \    /  \
  r   g  ta  e
         / \
        t   a
  
  We say that "rgtae" is a scrambled string of "great".    
  Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1. 
  */

  /*
          首先想到的是递归(即深搜),对两个string进行分割,然后比较四对字符串。
          代码虽然简单,但是复杂度比较高。
          有两种加速策略,一种是剪枝,提前返回;一种是加缓存,缓存中间结果,即memorization(翻译为记忆化搜索)。
          剪枝可以五花八门,要充分观察,充分利用信息,找到能让节点提前返回的条件。
          例如,判断两个字符串是否互为scamble,至少要求每个字符在两个字符串中出现的次数要相等,如果不相等则返回false。
          加缓存,可以用数组或HashMap。本题维数较高,用HashMap,map和unordered_map均可。
          既然可以用记忆化搜索,这题也一定可以用动规。
          设状态为f[i][j][n],表示长度为n,起点为s1[i]和起点为s2[j]两个字符串是否互为scramble,
          则状态转移方程为f[i][j][n] = (f[i][j][k] && f[i+k][j+k][n-k]) || (f[i][j+n-k][k] && f[i+k][j][n-k])
  */

  public boolean isScramble(String s1, String s2) {
    boolean[][][] dp = new boolean[s1.length()][s1.length()][s1.length() + 1];
    for (int i = s1.length() - 1; i >= 0; i--)
      for (int j = s1.length() - 1; j >= 0; j--)
        for (int k = 1, size = s1.length() - Math.max(i, j); k <= size; k++)
          if (equals(s1, s2, i, j, k)) dp[i][j][k] = true;
          else for (int l = 1; l < k; l++)
            if (dp[i][j][l] && dp[i + l][j + l][k - l] || dp[i][j + k - l][l] && dp[i + l][j][k - l]) {
              dp[i][j][k] = true;
              break;
            }
    return dp[0][0][s1.length()];
  }

  private boolean equals(String s1, String s2, int i, int j, int len) {
    while (len-- > 0)
      if (s1.charAt(i++) != s2.charAt(j++)) return false;
    return true;
  }

  @Test
  public void test() {
    assertTrue(isScramble("great", "rgtae"));
    assertTrue(isScramble("great", "rgeat"));
  }

}
