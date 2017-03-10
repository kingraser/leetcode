package leetcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

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
  The correct order is: "werft".  
  Note:  
        You may assume all letters are in lowercase.
        If the order is invalid, return an empty string.
        There may be multiple valid order of letters, return any one of them is fine. 
  */

  @Test
  public void test() {
    assertTrue(alienOrder(Arrays.asList("wrt", "wrf", "er", "ett", "rftt")).matches("[we]{2}rft"));
    assertEquals("", alienOrder(Arrays.asList("ab", "ba")));
  }

  public String alienOrder(List<String> words) {
    Map<Character, Node> map = new HashMap<>();
    for (String word : words)
      if (!add(word, map)) return "";
    return sort(map);
  }

  private String sort(Map<Character, Node> map) {
    return new String(map.values().stream().sorted((n1, n2) -> n1.val - n2.val).mapToInt(n -> n.c).toArray(), 0,
        map.size());
  }

  private boolean add(String word, Map<Character, Node> map) {
    Node current, previous = null;
    for (int i = word.length() - 1; i >= 0; previous = current) {
      char c = word.charAt(i--);
      current = map.computeIfAbsent(c, k -> new Node(c));
      if (previous == null || previous.c == current.c) continue;
      if (current.hasChild(previous)) return false;
      previous.add(current);
    }
    return true;
  }

  public class Node {
    public char c;
    public int val = Integer.MAX_VALUE - 1;
    public Map<Character, Node> nextMap = new HashMap<>();

    public Node(char c) {
      this.c = c;
    }

    private void sub(Node node, Node prev) {
      if (node.val < prev.val) return;
      node.val = prev.val - 1;
      node.nextMap.values().forEach(next -> sub(next, node));
    }

    public void add(Node next) {
      nextMap.computeIfAbsent(next.c, k -> next);
      sub(next, this);
    }

    public boolean hasChild(Node node) {
      return node == null ? false
          : nextMap.containsKey(node.c) ? true : nextMap.values().stream().anyMatch(next -> next.hasChild(node));
    }
  }
}
