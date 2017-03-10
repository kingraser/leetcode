package leetcode;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.junit.Test;

import com.google.common.collect.Sets;

public class SortCharactersByFrequency {

  /*
  Given a string, sort it in decreasing order based on the frequency of characters.
  
  Example 1:  
  Input: "tree"  
  Output: "eert"  
  Explanation:
  'e' appears twice while 'r' and 't' both appear once.
  So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
  
  Example 2:  
  Input: "cccaaa"  
  Output: "cccaaa"  
  Explanation:
  Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
  Note that "cacaca" is incorrect, as the same characters must be together.
  
  Example 3:  
  Input: "Aabb"  
  Output: "bbAa"  
  Explanation:
  "bbaA" is also a valid answer, but "Aabb" is incorrect.
  Note that 'A' and 'a' are treated as two different characters.  
  */

  @Test
  public void test() {
    assertTrue(Sets.newHashSet("eert", "eetr").contains(frequencySort("tree")));
    assertTrue(Sets.newHashSet("aaaccc", "cccaaa").contains(frequencySort("cccaaa")));
    assertTrue(Sets.newHashSet("bbAa", "bbaA").contains(frequencySort("Aabb")));
  }

  public String frequencySort(String s) {
    StringBuilder sb = new StringBuilder();
    Map<Integer, Integer> map = new HashMap<>();
    s.chars().forEach(c -> map.compute(c, (k, v) -> Objects.isNull(v) ? 1 : v + 1));
    map.entrySet().stream().sorted((e1, e2) -> e2.getValue() - e1.getValue()).forEach(e -> push(e, sb));
    return sb.toString();
  }

  private void push(Entry<Integer, Integer> e, StringBuilder sb) {
    for (int c = e.getKey(), times = e.getValue(); times-- > 0; sb.append((char) c));
  }

}
