package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class WordAbbreviation {

  /*
  Given an array of n distinct non-empty strings, 
  you need to generate minimal possible abbreviations for every word following rules below.  
    Begin with the first character and then the number of characters abbreviated, which followed by the last character.
    If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
    If the abbreviation doesn't make the word shorter, then keep it as original.
  
  Example:  
  Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
  Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
  
  Note:  
    Both n and the length of each word will not exceed 400.
    The length of each word is greater than 1.
    The words consist of lowercase English letters only.
    The return answers should be in the same order as the original array.  
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList("bbbbab", "bbbaab", "ba3b"),
        wordsAbbreviation(Arrays.asList("bbbbab", "bbbaab", "baaaab")));
    assertEquals(Arrays.asList("abcd2g", "abccefg", "abcckkg"),
        wordsAbbreviation(Arrays.asList("abcdefg", "abccefg", "abcckkg")));

    assertEquals(Arrays.asList("l2e", "god", "internal", "me", "i6t", "interval", "inte4n", "f2e", "intr4n"),
        wordsAbbreviation(
            Arrays.asList("like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion")));
  }

  public List<String> wordsAbbreviation(List<String> dict) {
    List<String> result = new ArrayList<>();
    Map<String, String> map = new HashMap<>(); // key is word, value is abbreviation
    Map<Integer, List<String>> lengthMap = new HashMap<>(); // key is length, value is words
    for (String word : dict)
      if (word.length() < 4) map.put(word, word);
      else lengthMap.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word);
    lengthMap.values().forEach(words -> map.putAll(getAbbreviation(words)));
    dict.forEach(word -> result.add(map.get(word)));
    return result;
  }

  private Map<String, String> getAbbreviation(List<String> words) {
    Map<String, String> result = new HashMap<>();
    Set<String> next = new HashSet<>(words);
    for (int i = 1, len = words.get(0).length(), end = len - 2; i < end && !next.isEmpty(); i++) {
      Map<String, String> map = new HashMap<>(); // key is abbreviation, value is word
      for (String s : next)
        map.compute(getAbbreviation(s, i), (k, v) -> v == null ? s : "");
      for (Entry<String, String> e : map.entrySet())
        if (e.getValue().length() > 0) {
          next.remove(e.getValue());
          result.put(e.getValue(), e.getKey());
        }
    }
    next.forEach(word -> result.put(word, word));
    return result;
  }

  private String getAbbreviation(String s, int firstPartEnd) {
    return s.substring(0, firstPartEnd) + (s.length() - 1 - firstPartEnd) + s.charAt(s.length() - 1);
  }
}
