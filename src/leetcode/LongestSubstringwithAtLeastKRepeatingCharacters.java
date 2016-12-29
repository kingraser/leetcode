package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestSubstringwithAtLeastKRepeatingCharacters {

  /*
  Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
  
  Example 1:
  
  Input:
  s = "aaabb", k = 3
  
  Output:
  3
  
  The longest substring is "aaa", as 'a' is repeated 3 times.
  
  Example 2:
  
  Input:
  s = "ababbc", k = 2
  
  Output:
  5
  
  The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
  */

  @Test
  public void test() {
    assertEquals(3, longestSubstring("bbaaacbd", 3));
    assertEquals(0, longestSubstring("ababacb", 3));
    assertEquals(3, longestSubstring("aaabb", 3));
    assertEquals(5, longestSubstring("ababbc", 2));
  }

  public int longestSubstring(String s, int k) {
    return longestSubstring(s.toCharArray(), 0, s.length(), k);
  }

  private int longestSubstring(char[] A, int begin, int end, int k) {
    if (end - begin < k) return 0;
    int[] map = new int[128];
    for (int i = begin; i < end; map[A[i++]]++);
    int max = -1, last = begin;
    for (int i = begin; i < end; i++)
      if (map[A[i]] < k) {
        max = Math.max(max, longestSubstring(A, last, i, k));
        last = i + 1;
      }
    return max == -1 ? end - begin : Math.max(max, longestSubstring(A, last, end, k));
  }

}
