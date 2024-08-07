package leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class SubstringwithConcatenationofAllWords {

  /*
  You are given a string, S, and a list of words, L, that are all of the same length. 
  Find all starting indices of substring(s) in S 
  that is a concatenation of each word in L exactly once and without any intervening characters.
  For example, given:
  S: "barfoothefoobarman"
  L: ["foo", "bar"]
  You should return the indices: [0,9].(order does not matter).
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(0, 9), findSubstring("barfoothefoobarman", new String[] { "foo", "bar" }));
  }

  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> result = new ArrayList<>();
    Map<String, Integer> dictionary = Arrays.stream(words).collect(Collectors.toMap(k -> k, k -> 1, Integer::sum, HashMap::new));
    for (int i = 0, size = words[0].length(), length = size * words.length; i <= s.length() - length; i++)
      if (isConcatenationofAllWords(s.substring(i, i + length), new HashMap<>(dictionary), size)) result.add(i);
    return result;
  }

  public boolean isConcatenationofAllWords(String s, Map<String, Integer> map, int wordLength) {
    String word;
    for (int idx = 0; idx < s.length(); idx += wordLength, map.remove(word, 0))
      if (Objects.isNull(map.computeIfPresent(word = s.substring(idx, idx + wordLength), (k, v) -> v - 1)))
        return false;
    return map.isEmpty();
  }

}
