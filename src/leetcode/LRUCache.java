package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class LRUCache {

  /*
  Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
  
  get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
  set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item. 
  */

  @Test
  public void test() {
    Cache cache = new Cache(2);
    cache.set(1, 1);
    cache.set(2, 2);
    cache.set(3, 3);
    assertEquals(-1, cache.get(1));
    assertEquals(2, cache.get(2));
    assertEquals(3, cache.get(3));
  }

  class Cache {
    private Map<Integer, Integer> map;
    private int capacity;

    public Cache(int capacity) {
      this.capacity = capacity;
      map = new LinkedHashMap<>(capacity + 1, 1, true);
    }

    public int get(int key) {
      return map.getOrDefault(key, -1);
    }

    public void set(int key, int value) {
      if (!map.containsKey(key) && map.size() == capacity) map.remove(map.keySet().iterator().next());
      map.put(key, value);
    }
  }
}
