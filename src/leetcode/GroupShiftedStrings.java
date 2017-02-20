package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Lists;

public class GroupShiftedStrings {

  /*
  Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". 
  We can keep "shifting" which forms the sequence: 
  "abc" -> "bcd" -> ... -> "xyz"  
  Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
  
  For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
  Return:
  
  [
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
  ]
  
  Note: For the return value, each inner list's elements must follow the lexicographic order.
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList("a", "z"), Arrays.asList("az", "ba"), Arrays.asList("abc", "bcd", "xyz"),
        Arrays.asList("acef")), groupStrings(Arrays.asList("abc", "bcd", "acef", "xyz", "az", "ba", "a", "z")));
  }

  public List<List<String>> groupStrings(List<String> strings) {
    Collections.sort(strings,
        (s1, s2) -> s1.length() != s2.length() ? s1.length() - s2.length() : s1.charAt(0) - s2.charAt(0));
    List<List<String>> result = new ArrayList<>();
    Map<Integer, Map<String, List<String>>> map = new LinkedHashMap<>();
    A: for (String s : strings) {
      for (String key : map.computeIfAbsent(s.length(), k -> new HashMap<>()).keySet())
        if (s.length() == 1 || isShifted(key, s)) {
          map.get(s.length()).get(key).add(s);
          continue A;
        }
      map.get(s.length()).put(s, Lists.newArrayList(s));
    }
    map.values().forEach(m -> m.values().forEach(value -> result.add(value)));
    return result;
  }

  private boolean isShifted(String s1, String s2) {
    int step = s2.charAt(0) - s1.charAt(0);
    for (int i = 1; i < s1.length(); i++)
      if ((s2.charAt(i) - s1.charAt(i) + 26) % 26 != step) return false;
    return true;
  }
}
