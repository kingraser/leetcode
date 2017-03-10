package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.IntStream;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ShortestWordDistanceIII {

  /*
  This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
  Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
  word1 and word2 may be the same and they represent two individual words in the list.
  For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].  
  Given word1 = "makes", word2 = "coding", return 1.
  Given word1 = "makes", word2 = "makes", return 3.
  Note: You may assume word1 and word2 are both in the list.
  */

  @Test
  public void test() {
    WordDistance wordDistance = new WordDistance(new String[] { "practice", "makes", "perfect", "coding", "makes" });
    assertEquals(1, wordDistance.shortestDistance("coding", "makes"));
    assertEquals(3, wordDistance.shortestDistance("makes", "makes"));
  }

  class WordDistance {
    private Map<String, TreeSet<Integer>> map = new HashMap<>();

    public WordDistance(String[] words) {
      IntStream.range(0, words.length).forEach(i -> map.computeIfAbsent(words[i], k -> new TreeSet<>()).add(i));
    }

    public int shortestDistance(String word1, String word2) {
      if (word1.equals(word2)) return shortestDistance(Lists.newArrayList(map.get(word1)));
      if (map.get(word1).size() > map.get(word2).size()) return shortestDistance(word2, word1);
      int result = Integer.MAX_VALUE;
      for (int i : map.get(word1))
        result = Math.min(result, compute(i, map.get(word2)));
      return result;
    }

    private int shortestDistance(List<Integer> list) {
      int result = Integer.MAX_VALUE;
      for (int i = 1; i < list.size(); i++)
        result = Math.min(result, list.get(i) - list.get(i - 1));
      return result;
    }

    private int compute(int i, TreeSet<Integer> set) {
      Integer max = set.ceiling(i), min = set.floor(i);
      return max == null ? Math.abs(min - i)
          : min == null ? Math.abs(max - i) : Math.min(Math.abs(min - i), Math.abs(max - i));
    }
  }
}
