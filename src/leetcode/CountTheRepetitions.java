package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.BitSet;

import org.junit.Test;

public class CountTheRepetitions {

  /*
  Define S = [s,n] as the string S which consists of n connected strings s. 
  For example, ["abc", 3] ="abcabcabc".  
  On the other hand, we define that string s1 can be obtained from string s2 if we can remove some characters from s2 such that it becomes s1. 
  For example, "abc" can be obtained from "abdbec" based on our definition, but it can not be obtained from "acbbe".
  You are given two non-empty strings s1 and s2 (each at most 100 characters long) and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. 
  Now consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M such that [S2,M] can be obtained from S1.
  
  Example:
  Input:s1="acb", n1=4, s2="ab", n2=2
  Return:2 
  */

  @Test
  public void test() {
    assertEquals(2, getMaxRepetitions("acb", 4, "ab", 2));
  }

  public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
    char[] S1 = s1.toCharArray(), S2 = s2.toCharArray();
    BitSet bitSet = new BitSet(10000);
    int l1 = S1.length, l2 = S2.length, count1 = 0, count2 = 0, idx1 = 0, idx2 = 0, idx;
    while (count1 < n1) {
      if (S1[idx1] == S2[idx2]) {
        if (bitSet.get(idx = idx1 * 100 + idx2)) break;
        bitSet.set(idx);
        if (++idx2 == l2) {
          idx2 = 0;
          count2++;
        }
      }
      if (++idx1 == l1) {
        idx1 = 0;
        count1++;
      }
    }
    return count2 == 0 ? 0 : l1 * n1 / (count1 * l1 + idx1) * count2 / n2;
  }

}
