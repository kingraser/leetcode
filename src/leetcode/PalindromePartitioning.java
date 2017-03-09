package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

public class PalindromePartitioning {

  /*
  Given a string s, partition s such that every substring of the partition is a palindrome.    
  Return all possible palindrome partitioning of s.    
  For example, given s = "aab",
  Return    
    [
      ["aa","b"],
      ["a","a","b"]
    ]
  */

  @Test
  public void test() {
    List<List<String>> expected = Arrays.asList(Arrays.asList("a", "a", "b"), Arrays.asList("aa", "b"));
    assertEquals(new HashSet<>(expected), new HashSet<>(partition("aab")));
  }

  public List<List<String>> partition(String s) {
    return partition(s, getPalindromeMap(s.toCharArray()));
  }

  public List<List<String>> partition(String s, boolean[][] palindromeMap) {
    if (s.length() == 0) return Arrays.asList(new ArrayList<>());
    List<List<String>> result = new ArrayList<>();
    for (int end = s.length() - 1, start = end; start >= 0; start--)
      if (isPalindrome(start, end, palindromeMap)) for (List<String> list : partition(s.substring(0, start))) {
        list.add(s.substring(start));
        result.add(list);
      }
    return result;
  }

  public static boolean isPalindrome(int startInclusive, int endInclusive, boolean[][] map) {
    return startInclusive >= endInclusive || map[startInclusive][endInclusive];
  }

  public static boolean[][] getPalindromeMap(char[] s) {
    boolean[][] map = new boolean[s.length][s.length];
    for (int start = 0; start < s.length; start++)
      for (int end = start + 1; end < s.length; end++)
        map[start][end] = s[start] == s[end] && isPalindrome(start + 1, end - 1, map);
    return map;
  }
}
