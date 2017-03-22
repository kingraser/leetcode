package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

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
    assertEquals(Arrays.asList("cat sand dog", "cats and dog"),
        wordBreak("catsanddog", Stream.of("cat", "cats", "and", "sand", "dog").collect(Collectors.toSet())));
  }

  public List<String> wordBreak(String s, Set<String> dic) {
    BitSet cuts = new BitSet(), words = new BitSet();
    cuts.set(0);
    for (int right = 1; right <= s.length(); right++)
      for (int left = right - 1; left >= 0; left--)
        if (cuts.get(left) && dic.contains(s.substring(left, right))) {
          cuts.set(right);
          words.set(left * (s.length() + 1) + right);
        }
    return getResult(s, words);
  }

  private List<String> getResult(String s, BitSet words) {
    List<String> result = new ArrayList<>();
    dfs(s, words, 0, new ArrayDeque<>(), result);
    return result;
  }

  private void dfs(String s, BitSet words, int start, Deque<String> path, List<String> result) {
    if (start == s.length()) result.add(String.join(" ", path));
    else for (int end = start + 1; end <= s.length(); end++)
      if (words.get(start * (s.length() + 1) + end)) {
        path.addLast(s.substring(start, end));
        dfs(s, words, end, path, result);
        path.pollLast();
      }
  }
}
