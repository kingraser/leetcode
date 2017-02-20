package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Test;

public class ShortestWordDistanceII {

  /*
  This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?
  Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.
  For example,
  Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
  Given word1 = "coding", word2 = "practice", return 3. Given word1 = "makes", word2 = "coding", return 1.
  Note You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
  */

  @Test
  public void test() {
    WordDistance wordDistance = new WordDistance(Arrays.asList("practice", "makes", "perfect", "coding", "makes"));
    assertEquals(3, wordDistance.shortestDistance("coding", "practice"));
    assertEquals(1, wordDistance.shortestDistance("coding", "makes"));
  }

  class WordDistance {
    private Map<String, TreeSet<Integer>> map = new HashMap<>();

    public WordDistance(List<String> words) {
      for (int i = 0; i < words.size(); i++)
        map.computeIfAbsent(words.get(i), k -> new TreeSet<>()).add(i);
    }

    public int shortestDistance(String word1, String word2) {
      if (map.get(word1).size() > map.get(word2).size()) return shortestDistance(word2, word1);
      int result = Integer.MAX_VALUE;
      for (int i : map.get(word1))
        result = Math.min(result, compute(i, map.get(word2)));
      return result;
    }

    private int compute(int i, TreeSet<Integer> set) {
      Integer max = set.ceiling(i), min = set.floor(i);
      return max == null ? Math.abs(min - i)
          : min == null ? Math.abs(max - i) : Math.min(Math.abs(min - i), Math.abs(max - i));
    }
  }
}
