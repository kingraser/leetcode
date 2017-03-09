package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class PalindromePairs {

  /*
  Given a list of unique words. Find all pairs of indices (i, j) in the given list, 
  so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
  
  Example 1:
  Given words = ["bat", "tab", "cat"]
  Return [[0, 1], [1, 0]]
  The palindromes are ["battab", "tabbat"]
  
  Example 2:
  Given words = ["abcd", "dcba", "lls", "s", "sssll"]
  Return [[0, 1], [1, 0], [3, 2], [2, 4]]
  The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
  */

  @Test
  public void test() {
    List<List<Integer>> expected = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0));
    assertEquals(new HashSet<>(expected), new HashSet<>(palindromePairs(new String[] { "bat", "tab", "cat" })));
    expected = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 0), Arrays.asList(3, 2), Arrays.asList(2, 4));
    assertEquals(new HashSet<>(expected),
        new HashSet<>(palindromePairs(new String[] { "abcd", "dcba", "lls", "s", "sssll" })));
  }

  public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> pairs = new ArrayList<>();
    if (words == null || words.length == 0) return pairs;
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < words.length; map.put(words[i], i++));
    for (int i = 0; i < words.length; ++i) {
      char[] s = words[i].toCharArray();
      int l = 0, r = 0;
      while (l <= r) {
        Integer j = map.get(reverse(s, l, r));
        if (j != null && j.intValue() != i && isPalindrome(s, l == 0 ? r : 0, l == 0 ? s.length : l))
          pairs.add(Arrays.asList(l == 0 ? new Integer[] { i, j } : new Integer[] { j, i }));
        if (r < words[i].length()) ++r;
        else++l;
      }
    }
    return pairs;
  }

  private String reverse(char[] s, int l, int r) {
    if (r == l) return "";
    char[] reverse = new char[r - l];
    for (int i = 0; i < reverse.length; reverse[i++] = s[--r]);
    return new String(reverse);
  }

  private boolean isPalindrome(char[] s, int l, int r) {
    while (l < r)
      if (s[l++] != s[--r]) return false;
    return true;
  }
}
