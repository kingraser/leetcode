/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月15日<p>
//-------------------------------------------------------
public class DistinctSubsequences {

  /*
  Given a string S and a string T, count the number of distinct subsequences of T in S.    
  A subsequence of a string is a new string 
  which is formed from the original string by deleting some (can be none) of the characters 
  without disturbing the relative positions of the remaining characters. 
  (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
  
  Here is an example:
  S = "rabbbit", T = "rabbit"
  
  Return 3. 
  */

  /*
  Let f(i,j) represents the times of T[0,j] occurs in S[0,i]
  Without S[i],f(i,j)=f(i−1,j);
  If S[i]==T[j],f(i,j)=f(i−1,j)+f(i−1,j−1)。
  */

  @Test
  public void test() {
    assertEquals(3, numDistinct("rabbbit", "rabbit"));
    assertEquals(3, numDistinctII("rabbbit", "rabbit"));
  }

  public int numDistinct(String s, String t) {
    int[][] table = new int[s.length() + 1][t.length() + 1];
    for (int i = 0; i <= s.length(); table[i++][0] = 1);
    for (int i = 1; i <= s.length(); i++)
      for (int j = 1; j <= t.length(); j++)
        if (s.charAt(i - 1) == t.charAt(j - 1)) table[i][j] = table[i - 1][j - 1] + table[i - 1][j];
        else table[i][j] = table[i - 1][j];
    return table[s.length()][t.length()];
  }

  //optimization
  public int numDistinctII(String s, String t) {
    int[] table = new int[t.length() + 1];
    table[0] = 1;
    for (int i = 0; i < s.length(); i++)
      for (int j = t.length() - 1; j >= 0; j--)
        table[j + 1] += s.charAt(i) == t.charAt(j) ? table[j] : 0;
    return table[t.length()];
  }

}
