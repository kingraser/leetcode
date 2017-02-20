package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

public class LFUCache {

  /*
  Design and implement a data structure for Least Frequently Used (LFU) cache. 
  It should support the following operations: get and set.
  
  get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
  set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
  
  Follow up:
  Could you do both operations in O(1) time complexity?
  
  Example:
  
  LFUCache cache = new LFUCache(2); // capacity
  
  cache.set(1, 1);
  cache.set(2, 2);
  cache.get(1);       // returns 1
  cache.set(3, 3);    // evicts key 2
  cache.get(2);       // returns -1 (not found)
  cache.get(3);       // returns 3.
  cache.set(4, 4);    // evicts key 1.
  cache.get(1);       // returns -1 (not found)
  cache.get(3);       // returns 3
  cache.get(4);       // returns 4
  */

  @Test
  public void test() {
    Cache cache = new Cache(2);
    cache.set(1, 1);
    cache.set(2, 2);
    assertEquals(1, cache.get(1));
    cache.set(3, 3);
    assertEquals(-1, cache.get(2));
    assertEquals(3, cache.get(3));
    cache.set(4, 4);
    assertEquals(-1, cache.get(1));
    assertEquals(3, cache.get(3));
    assertEquals(4, cache.get(4));
  }

  class Cache {
    Node head = null;
    final int capacity;
    Map<Integer, Integer> valueMap = new HashMap<>();
    Map<Integer, Node> nodeMap = new HashMap<>();

    public Cache(int capacity) {
      this.capacity = capacity;
    }

    public int get(int key) {
      if (nodeMap.containsKey(key)) increase(key);
      return valueMap.getOrDefault(key, -1);
    }

    private void increase(int key) {
      Node node = nodeMap.get(key);
      node.keys.remove(key);
      if (Objects.isNull(node.next)) node.next = new Node(node, null, 1 + node.count, key);
      else if (node.next.count == node.count + 1) node.next.keys.add(key);
      else node.next = node.next.prev = new Node(node, node.next, node.count + 1, key);
      nodeMap.put(key, node.next);
      if (node.keys.isEmpty()) remove(node);
    }

    private void remove(Node node) {
      if (head == node) head = node.next;
      else node.prev.next = node.next;
      if (Objects.nonNull(node.next)) node.next.prev = node.prev;
    }

    public void set(int key, int value) {
      if (0 == this.capacity) return;
      if (Objects.nonNull(valueMap.put(key, value))) increase(key);
      else {
        if (nodeMap.size() == this.capacity) remove();
        add(key);
      }
    }

    private void add(int key) {
      if (Objects.isNull(head)) head = new Node(null, null, 1, key);
      else if (head.count == 1) head.keys.add(key);
      else head = head.prev = new Node(null, head, 1, key);
      nodeMap.put(key, head);
    }

    private void remove() {
      if (Objects.isNull(head)) return;
      int oldest = head.keys.iterator().next();
      head.keys.remove(oldest);
      if (head.keys.isEmpty()) remove(head);
      nodeMap.remove(oldest);
      valueMap.remove(oldest);
    }

    class Node {
      public Node prev, next;
      public final int count;
      public LinkedHashSet<Integer> keys = new LinkedHashSet<>();

      public Node(Node prev, Node next, int count, int key) {
        this.prev = prev;
        this.next = next;
        this.count = count;
        keys.add(key);
      }
    }
  }
}
