package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    String result[] = new String[dict.size()];
    abbreviate(result, dict, IntStream.range(0, result.length).boxed().collect(Collectors.toList()), 1);
    return Arrays.asList(result);
  }

  private void abbreviate(String[] result, List<String> dict, List<Integer> duplicateIdxs, int firstPartEnd) {
    Map<String, List<Integer>> map = new HashMap<>();
    duplicateIdxs.stream().forEach(
        idx -> map.computeIfAbsent(getAbbreviation(dict.get(idx), firstPartEnd), k -> new ArrayList<>()).add(idx));
    for (Entry<String, List<Integer>> entry : map.entrySet())
      if (entry.getValue().size() == 1) result[entry.getValue().get(0)] = entry.getKey();
      else abbreviate(result, dict, entry.getValue(), firstPartEnd + 1);
  }

  private String getAbbreviation(String s, int firstPartEnd) {
    return s.length() - firstPartEnd < 3 ? s
        : s.substring(0, firstPartEnd) + (s.length() - 1 - firstPartEnd) + s.charAt(s.length() - 1);
  }
}
