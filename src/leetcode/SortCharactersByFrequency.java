package leetcode;

import static org.junit.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.junit.Test;

import com.google.common.collect.Sets;

public class SortCharactersByFrequency {

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
