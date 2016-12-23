/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月6日;
//-------------------------------------------------------
public class InsertDeleteGetRandomO1 {
  /*
  Design a data structure that supports all following operations in average O(1) time.
  
  insert(val): Inserts an item val to the set if not already present.
  remove(val): Removes an item val from the set if present.
  getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
  */

  public class RandomizedSet {
    private Map<Integer, Integer> map = new HashMap<>();
    private List<Integer> list = new ArrayList<>();
    Random random = new Random();

    public boolean insert(int val) {
      return Objects.isNull(map.putIfAbsent(val, list.size())) ? list.add(val) : false;
    }

    public boolean remove(int val) {
      Integer idx = map.remove(val), last;
      if (Objects.isNull(idx)) return false;
      map.computeIfPresent(last = list.get(list.size() - 1), (k, v) -> idx);
      list.set(idx, last);
      list.remove(list.size() - 1);
      return true;
    }

    public int getRandom() {
      return list.get(random.nextInt(list.size()));
    }
  }
}
