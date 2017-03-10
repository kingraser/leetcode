package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShortestWordDistance {

  /*
  Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
  For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].  
  Given word1 = "coding", word2 = "practice", return 3. Given word1 = "makes", word2 = "coding", return 1.
  Note You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
  */

  @Test
  public void test() {
    String[] words = { "practice", "makes", "perfect", "coding", "makes" };
    assertEquals(3, shortestDistance(words, "coding", "practice"));
    assertEquals(1, shortestDistance(words, "coding", "makes"));
  }

  public int shortestDistance(String[] words, String word1, String word2) {
    int result = Integer.MAX_VALUE;
    for (int idx = 0, idx1 = -1, idx2 = -1; idx < words.length; result = Math.min(result,
        idx1 == -1 || idx2 == -1 ? result : Math.abs(idx1 - idx2)), idx++)
      if (word1.equals(words[idx])) idx1 = idx;
      else if (word2.equals(words[idx])) idx2 = idx;
    return result;
  }

}
