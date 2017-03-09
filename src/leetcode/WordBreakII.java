package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class WordBreakII {

  /*
  Given a string s and a dictionary of words dict, 
  add spaces in s to construct a sentence where each word is a valid dictionary word.
  
  Return all such possible sentences.
  
  For example, given
  s = "catsanddog",
  dict = ["cat", "cats", "and", "sand", "dog"]. 
  A solution is ["cats and dog", "cat sand dog"]. 
  */

  @Test
  public void test() {
    Set<String> dict = Sets.newHashSet("cat", "cats", "and", "sand", "dog");
    List<String> expected = Arrays.asList("cat sand dog", "cats and dog");
    assertEquals(Sets.newHashSet(expected), new HashSet<>(wordBreak("catsanddog", dict)));
  }

  public List<String> wordBreak(String s, Set<String> dic) {
    boolean[] cuts = new boolean[s.length() + 1];
    boolean[][] words = new boolean[s.length()][s.length() + 1];
    cuts[0] = true;
    for (int right = 1; right <= s.length(); right++)
      for (int left = right - 1; left >= 0; left--)
        if (cuts[left] && dic.contains(s.substring(left, right))) {
          cuts[right] = true;
          words[left][right] = true;
        }
    return getResult(s, words);
  }

  private List<String> getResult(String s, boolean[][] words) {
    List<String> result = new ArrayList<>();
    dfs(s, words, 0, new ArrayDeque<>(), result);
    return result;
  }

  private void dfs(String s, boolean[][] words, int start, Deque<String> path, List<String> result) {
    if (start == s.length()) {
      result.add(String.join(" ", path));
      return;
    }
    for (int end = start + 1; end <= s.length(); end++)
      if (words[start][end]) {
        path.addLast(s.substring(start, end));
        dfs(s, words, end, path, result);
        path.pollLast();
      }
  }
}
