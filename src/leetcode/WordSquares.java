package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import org.junit.Test;

import leetcode.common.Trie;

public class WordSquares {

  /*
  Given a set of words (without duplicates), find all word squares you can build from them.  
  A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).  
  For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
  
  b a l l
  a r e a
  l e a d
  l a d y
  
  Note:  
    There are at least 1 and at most 1000 words.
    All words will have the exact same length.
    Word length is at least 1 and at most 5.
    Each word contains only lowercase English alphabet a-z.
  
  Example 1:  
  Input: ["area","lead","wall","lady","ball"]  
  Output:
  [
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
  ]  
  Explanation: The output consists of two word squares. 
  The order of output does not matter (just the order of words in each word square matters).
    
  Example 2:  
  Input: ["abat","baba","atan","atal"]  
  Output:
  [
  [ "baba",
    "abat",
    "baba",
    "atan"
  ],
  [ "baba",
    "abat",
    "baba",
    "atal"
  ]
  ]  
  Explanation: The output consists of two word squares. 
  The order of output does not matter (just the order of words in each word square matters).  
  */

  @Test
  public void test() {
    assertEquals(
        Arrays.asList(Arrays.asList("wall", "area", "lead", "lady"), Arrays.asList("ball", "area", "lead", "lady")),
        wordSquares(new String[] { "area", "lead", "wall", "lady", "ball" }));

    assertEquals(
        Arrays.asList(Arrays.asList("baba", "abat", "baba", "atal"), Arrays.asList("baba", "abat", "baba", "atan")),
        wordSquares(new String[] { "abat", "baba", "atan", "atal" }));
  }

  public List<List<String>> wordSquares(String[] words) {
    List<List<String>> result = new ArrayList<>();
    if (words == null || words.length == 0) return result;
    Trie trie = getTrie(words);
    Deque<String> deque = new ArrayDeque<>();
    for (String word : words) {
      deque.add(word);
      dfs(trie, result, deque);
      deque.pollLast();
    }
    return result;
  }

  private Trie getTrie(String[] words) {
    Trie trie = new Trie();
    Arrays.stream(words).forEach(word -> trie.add(word));
    return trie;
  }

  private void dfs(Trie trie, List<List<String>> result, Deque<String> deque) {
    if (deque.size() == deque.peek().length()) {
      result.add(new ArrayList<>(deque));
      return;
    }
    for (String word : trie.getWithPrefix(getPrefix(deque))) {
      deque.add(word);
      dfs(trie, result, deque);
      deque.pollLast();
    }
  }

  private String getPrefix(Deque<String> deque) {
    char[] result = new char[deque.size()];
    int idx = 0;
    for (String word : deque)
      result[idx++] = word.charAt(deque.size());
    return new String(result);
  }
}
