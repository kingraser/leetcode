package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import org.junit.Test;

import leetcode.common.Pair;

public class AlienDictionary {

  /*
  There is a new alien language which uses the latin alphabet. 
  However, the order among letters are unknown to you. 
  You receive a list of words from the dictionary, wherewords are sorted lexicographically by the rules of this new language. 
  Derive the order of letters in this language.
  For example,
  Given the following words in dictionary,  
  [
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
  ]  
  The correct order is: "wertf".  
  Note:  
        You may assume all letters are in lowercase.
        If the order is invalid, return an empty string.
        There may be multiple valid order of letters, return any one of them is fine. 
  */

  @Test
  public void test() {
    assertEquals("wertf", alienOrder(Arrays.asList("wrt", "wrf", "er", "ett", "rftt")));
    assertEquals("", alienOrder(Arrays.asList("wrtkj", "wrt")));
  }

  public String alienOrder(List<String> words) {
    return sort(getOrders(words), words);
  }

  private String sort(Pair<boolean[][], int[]> orders, List<String> words) {
    if (Objects.isNull(orders)) return "";
    BitSet letters = new BitSet();
    words.forEach(word -> word.chars().forEach(c -> letters.set(c)));
    int count = letters.cardinality(), result[] = new int[count];
    Deque<Integer> zeroDegrees = new ArrayDeque<>();
    IntStream.range(0, 26).filter(i -> orders.value[i] == 0 && letters.get(i + 'a')).forEach(i -> zeroDegrees.add(i));
    for (int node, i = 0, j; !zeroDegrees.isEmpty(); result[i++] = node + 'a', count--)
      for (node = zeroDegrees.poll(), j = 0; j < 26; j++)
        if (orders.key[node][j] && --orders.value[j] == 0) zeroDegrees.add(j);
    return count == 0 ? new String(result, 0, result.length) : "";
  }

  private Pair<boolean[][], int[]> getOrders(List<String> words) {
    boolean[][] map = new boolean[26][26];
    int[] degrees = new int[26];
    A: for (int wordIdx = 0; wordIdx < words.size() - 1;) {
      String former = words.get(wordIdx), latter = words.get(++wordIdx);
      for (int idx = 0, len = Math.min(former.length(), latter.length()), c1, c2; idx < len; idx++)
        if ((c1 = former.charAt(idx) - 'a') != (c2 = latter.charAt(idx) - 'a')) {
          degrees[c2]++;
          map[c1][c2] = true;
          continue A;
        }
      if (former.length() > latter.length()) return null;
    }
    return new Pair<>(map, degrees);
  }
}
