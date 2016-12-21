/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class LRUCache {

  /*
  Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
  
  get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
  set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item. 
  */

  private Map<Integer, Integer> map;

  private int capacity;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    map = new LinkedHashMap<>(capacity, 1, true);
  }

  public int get(int key) {
    return map.getOrDefault(key, -1);
  }

  public void set(int key, int value) {
    if (!map.containsKey(key) && map.size() == capacity) map.remove(map.keySet().iterator().next());
    map.put(key, value);
  }
}
