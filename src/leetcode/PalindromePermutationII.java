package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

public class PalindromePermutationII {

  /*
  Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.  
  For example:  
  Given s = "aabb", return ["abba", "baab"].  
  Given s = "abc", return []. 
  Hint:  
    If a palindromic permutation exists, we just need to generate the first half of the string.
    To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.
  */

  @Test
  public void test() {
    assertEquals(new ArrayList<>(), generatePalindromes("abc"));
    assertEquals(Arrays.asList("abba", "baab"), generatePalindromes("aabb"));
    assertEquals(Arrays.asList("abcba", "bacab"), generatePalindromes("aabbc"));
  }

  public List<String> generatePalindromes(String s) {
    List<String> result = new ArrayList<>();
    int oddCount = 0, oddChar = 0;
    int[] map = new int[128];
    Deque<Character> deque = new ArrayDeque<>();
    for (char c : s.toCharArray())
      if ((map[c] = 1 ^ map[c]) == 0) {
        deque.add((char) c);
        oddCount--;
      } else {
        oddChar = c;
        oddCount++;
      }
    if (oddCount > 1) return result;
    char[] cs = new char[s.length()];
    if (oddCount == 1) cs[s.length() >> 1] = (char) oddChar;
    dfs(result, cs, 0, deque);
    return result;
  }

  private void dfs(List<String> result, char[] s, int idx, Deque<Character> deque) {
    if (idx == s.length >> 1) {
      fill(s);
      result.add(new String(s));
      return;
    }
    for (int i = 0; i++ < deque.size();) {
      s[idx] = deque.pollFirst();
      dfs(result, s, idx + 1, deque);
      deque.addLast(s[idx]);
    }
  }

  private void fill(char[] s) {
    for (int left = 0, right = s.length - 1, half = s.length >> 1; left < half; s[right--] = s[left++]);
  }
}
