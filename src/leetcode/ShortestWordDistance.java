package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

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
    List<String> list = Arrays.asList("practice", "makes", "perfect", "coding", "makes");
    assertEquals(3, shortestDistance(list, "coding", "practice"));
    assertEquals(1, shortestDistance(list, "coding", "makes"));
  }

  public int shortestDistance(List<String> words, String word1, String word2) {
    int result = Integer.MAX_VALUE;
    for (int i = 0, idx1 = -1, idx2 = -1; i < words.size(); result = Math.min(result,
        idx1 == -1 || idx2 == -1 ? result : Math.abs(idx1 - idx2)), i++)
      if (word1.equals(words.get(i))) idx1 = i;
      else if (word2.equals(words.get(i))) idx2 = i;
    return result;
  }

}
