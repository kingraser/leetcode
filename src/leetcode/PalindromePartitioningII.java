package leetcode;

import static leetcode.PalindromePartitioning.getPalindromeMap;
import static leetcode.PalindromePartitioning.isPalindrome;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class PalindromePartitioningII {

  /*
  Given a string s, partition s such that every substring of the partition is a palindrome.    
  Return the minimum cuts needed for a palindrome partitioning of s.    
  For example, given s = "aab",
  Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut. 
  */

  /*
  Let f(i, j) represents the minimum cuts of range [i, j]
  Thus f(i,j) = min { f(i, k) + f(k + 1, j) }, i ≤ k ≤ j, 0 ≤ i ≤ j < n
  */

  @Test
  public void test() {
    assertEquals(1, minCut("aab"));
    assertEquals(2, minCut("aabccbd"));
  }

  public int minCut(String s) {
    boolean[][] map = getPalindromeMap(s.toCharArray());
    int[] minCut = new int[s.length()]; // minCut[i] represents minimum cuts of substring(i)
    Arrays.fill(minCut, Integer.MAX_VALUE);
    for (int end = s.length() - 1, left = end; left >= 0; left--)
      if (isPalindrome(left, end, map)) minCut[left] = 0;
      else for (int right = left; right < end; right++)
        if (isPalindrome(left, right, map)) minCut[left] = Math.min(minCut[left], 1 + minCut[right + 1]);
    return minCut[0];
  }

}
