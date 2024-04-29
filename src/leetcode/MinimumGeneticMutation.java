package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class MinimumGeneticMutation {

  /*
  A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".  
  Suppose we need to investigate about a mutation (mutation from "start" to "end"), 
  where ONE mutation is defined as ONE single character changed in the gene string.  
  For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.  
  Also, there is a given gene "bank", which records all the valid gene mutations. 
  A gene must be in the bank to make it a valid gene string.  
  Now, given 3 things - start, end, bank, 
  your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". 
  If there is no such a mutation, return -1.
  
  Note:  
    Starting point is assumed to be valid, so it might not be included in the bank.
    If multiple mutations are needed, all mutations during in the sequence must be valid.
    You may assume start and end string is not the same.
  
  Example 1:  
  start: "AACCGGTT"
  end:   "AACCGGTA"
  bank: ["AACCGGTA"]  
  return: 1
  
  Example 2:  
  start: "AACCGGTT"
  end:   "AAACGGTA"
  bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]  
  return: 2
  
  Example 3:  
  start: "AAAAACCC"
  end:   "AACCCCCC"
  bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]  
  return: 3 
  */

  @Test
  public void test() {
    assertEquals(1, minMutation("AACCGGTT", "AACCGGTA", new String[] { "AACCGGTA" }));
    assertEquals(2, minMutation("AACCGGTT", "AAACGGTA", new String[] { "AACCGGTA", "AACCGCTA", "AAACGGTA" }));
    assertEquals(3, minMutation("AAAAACCC", "AACCCCCC", new String[] { "AAAACCCC", "AAACCCCC", "AACCCCCC" }));

  }

  public int minMutation(String start, String end, String[] bank) {
    return Objects.equals(start, end) ? 0
        : minMutation(Set.of(start), Set.of(end), Arrays.stream(bank).collect(Collectors.toSet()), 1);
  }

  private int minMutation(Set<String> starts, Set<String> ends, Set<String> set, int i) {
    if (starts.size() > ends.size()) return minMutation(ends, starts, set, i);
    set.removeAll(starts);
    Set<String> nexts = new HashSet<>();
    for (String start : starts)
      for (String s : set)
        if (isMutation(start, s)) if (ends.contains(s)) return i;
        else nexts.add(s);
    return nexts.isEmpty() ? -1 : minMutation(nexts, ends, set, ++i);
  }

  private boolean isMutation(String a, String b) {
    for (int i = 0, count = 0, l = a.length(); i < l; i++)
      if (a.charAt(i) != b.charAt(i) && ++count > 1) return false;
    return true;
  }

}
