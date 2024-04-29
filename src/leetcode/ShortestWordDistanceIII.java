package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

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
    WordDistance wordDistance = new WordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"});
    assertEquals(1, wordDistance.shortestDistance("coding", "makes"));
    assertEquals(3, wordDistance.shortestDistance("makes", "makes"));
  }

  static class WordDistance extends ShortestWordDistanceII.WordDistance {
    public WordDistance(String[] words) {
      super(words);
    }

    public int shortestDistance(String word1, String word2) {
      return word1.equals(word2)
              ? shortestDistance(new ArrayList<>(map.get(word1)))
              : super.shortestDistance(word1, word2);
    }

    private int shortestDistance(List<Integer> list) {
      int result = Integer.MAX_VALUE;
      for (int i = 1; i < list.size(); i++)
        result = Math.min(result, list.get(i) - list.get(i - 1));
      return result;
    }
  }
}
