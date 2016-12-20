package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.Test;

public class AllOoneDataStructure {

  /*
  Implement a data structure supporting the following operations:
  
    Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
    Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
    GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
    GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
  
  Challenge: Perform all these in O(1) time complexity. 
  */

  @Test
  public void test() {
    AllOne one = new AllOne();
    one.inc("a");
    one.inc("b");
    one.inc("b");
    one.inc("b");
    one.inc("b");
    one.dec("b");
    one.dec("b");
    assertEquals("b", one.getMaxKey());
    assertEquals("a", one.getMinKey());
  }

  public class AllOne {
    Node head, last;
    Map<String, Integer> valueMap = new HashMap<>();
    Map<String, Node> nodeMap = new HashMap<>();

    public void inc(String key) {
      valueMap.compute(key, (k, v) -> Objects.isNull(v) ? 1 : v + 1);
      Node node = nodeMap.get(key);
      if (Objects.isNull(node)) {
        if (Objects.isNull(head)) last = head = new Node(1, null, null, key);
        else if (head.value == 1) head.keys.add(key);
        else head = head.prev = new Node(1, null, head, key);
        nodeMap.put(key, head);
      } else {
        node.keys.remove(key);
        if (Objects.isNull(node.next)) last = node.next = new Node(node.value + 1, node, null, key);
        else if (node.next.value == node.value + 1) node.next.keys.add(key);
        else node.next = node.next.prev = new Node(node.value + 1, node, node.next, key);
        nodeMap.put(key, node.next);
        if (node.keys.isEmpty()) remove(node);
      }
    }

    private void remove(Node node) {
      if (head == node) head = node.next;
      if (node == last) last = node.prev;
      if (Objects.nonNull(node.prev)) node.prev.next = node.next;
      if (Objects.nonNull(node.next)) node.next.prev = node.prev;
    }

    public void dec(String key) {
      valueMap.computeIfPresent(key, (k, v) -> v - 1);
      valueMap.remove(key, 0);
      Node node = nodeMap.get(key);
      if (Objects.isNull(node)) return;
      node.keys.remove(key);
      if (node.value > 1) {
        if (Objects.isNull(node.prev)) head = node.prev = new Node(node.value - 1, null, node, key);
        else if (node.prev.value == node.value - 1) node.prev.keys.add(key);
        else node.prev.next = node.prev = new Node(node.value - 1, node.prev, node, key);
        nodeMap.put(key, node.prev);
      }
      if (node.keys.isEmpty()) remove(node);

    }

    public String getMaxKey() {
      return Objects.isNull(last) || last.keys.isEmpty() ? "" : last.keys.iterator().next();
    }

    public String getMinKey() {
      return Objects.isNull(head) || head.keys.isEmpty() ? "" : head.keys.iterator().next();
    }

    class Node {
      public int value;
      public Set<String> keys = new HashSet<>();
      public Node prev, next;

      public Node(int value, Node prev, Node next, String key) {
        this.value = value;
        this.prev = prev;
        this.next = next;
        keys.add(key);
      }
    }
  }
}
