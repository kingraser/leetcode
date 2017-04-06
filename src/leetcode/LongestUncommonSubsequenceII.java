package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class LongestUncommonSubsequenceII {

  /*
  Given a list of strings, you need to find the longest uncommon subsequence among them. 
  The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.
  
  A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. 
  Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.
  
  The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. 
  If the longest uncommon subsequence doesn't exist, return -1.
  
  Example 1:  
  Input: "aba", "cdc", "eae"
  Output: 3
  
  Note:  
    All the given strings' lengths will not exceed 10.
    The length of the given list will be in the range of [2, 50].
  */

  @Test
  public void test() {
    assertEquals(3, findLUSlength(new String[] { "aba", "cdc", "eae" }));
  }

  public int findLUSlength(String[] strs) {
    Arrays.sort(strs, (s1, s2) -> s2.length() - s1.length());
    A: for (int i = 0, j; i < strs.length; i++) {
      for (j = 0; j < strs.length; j++)
        if (i != j && isSubSequence(strs[i], strs[j])) continue A;
      return strs[i].length();
    }
    return -1;
  }

  private boolean isSubSequence(String s1, String s2) {
    int idx1 = 0, idx2 = 0;
    while (idx1 < s1.length() && idx2 < s2.length())
      if (s1.charAt(idx1) == s2.charAt(idx2++)) idx1++;
    return idx1 == s1.length();
  }
}
