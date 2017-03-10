package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ConcatenatedWords {

  /*
  Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
  A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
  
  Example:  
  Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
  Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
  
  Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
  "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
  "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
  
  Note:
    The number of elements of the given array will not exceed 10,000
    The length sum of elements in the given array will not exceed 600,000.
    All the input string will only include lower case letters.
    The returned elements order does not matter.
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList("dogcatsdog", "catsdogcats", "ratcatdogcat"), findAllConcatenatedWordsInADict(
        new String[] { "cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat" }));
  }

  public List<String> findAllConcatenatedWordsInADict(String[] words) {
    List<String> result = new ArrayList<>();
    Set<String> preWords = new HashSet<>();
    Arrays.stream(words).sorted((s1, s2) -> s1.length() - s2.length())
        .forEach(word -> concatenate(word, preWords, result));
    return result;
  }

  private void concatenate(String word, Set<String> dict, List<String> result) {
    if (dict.isEmpty()) {
      dict.add(word);
      return;
    }
    BitSet cuts = new BitSet(word.length() + 1);
    cuts.set(0);
    for (int i = 1; i <= word.length(); i++)
      for (int j = 0; j < i; j++)
        if (cuts.get(j) && dict.contains(word.substring(j, i))) {
          cuts.set(i);
          break;
        }
    dict.add(word);
    if (cuts.get(word.length())) result.add(word);
  }

}
