package leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
  Let f[i][j][n] represents whether s1[i, i + n] is a scramble string of s2[j, j + n],
  Thus f[i][j][n] = (f[i][j][k] && f[i+k][j+k][n-k]) || (f[i][j+n-k][k] && f[i+k][j][n-k])
  */

  public boolean isScramble(String s1, String s2) {
    boolean[][][] dp = new boolean[s1.length()][s2.length()][s1.length() + 1];
    for (int idx1 = s1.length() - 1; idx1 >= 0; idx1--)
      for (int idx2 = s2.length() - 1; idx2 >= 0; idx2--)
        for (int len = 1, size = s1.length() - Math.max(idx1, idx2); len <= size; len++)
          if (equals(s1, s2, idx1, idx2, len)) dp[idx1][idx2][len] = true;
          else for (int l = 1; l < len; l++)
            if (dp[idx1][idx2][l] && dp[idx1 + l][idx2 + l][len - l]
                || dp[idx1][idx2 + len - l][l] && dp[idx1 + l][idx2][len - l]) {
              dp[idx1][idx2][len] = true;
              break;
            }
    return dp[0][0][s1.length()];
  }

  private boolean equals(String s1, String s2, int idx1, int idx2, int len) {
    while (len-- > 0)
      if (s1.charAt(idx1++) != s2.charAt(idx2++)) return false;
    return true;
  }

  @Test
  public void test() {
    assertTrue(isScramble("great", "rgtae"));
    assertTrue(isScramble("great", "rgeat"));
  }

}
