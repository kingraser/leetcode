/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Map;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class LRUCache {

    private Map<Integer, Integer> map;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new java.util.LinkedHashMap<Integer, Integer>(capacity, 1, true);
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        else return map.get(key);
    }

    public void set(int key, int value) {
        if (!map.containsKey(key) && (map.size() == capacity)) map.remove(map.keySet().iterator().next());
        map.put(key, value);
    }
}
