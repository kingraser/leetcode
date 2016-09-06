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
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

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

  @Test
  public void test() {
    RandomizedSet set = new RandomizedSet();
    Assert.assertTrue(set.insert(0));
    Assert.assertTrue(set.remove(0));
    Assert.assertTrue(set.insert(-1));
    Assert.assertFalse(set.remove(0));
    Assert.assertEquals(-1, set.getRandom());
    Assert.assertEquals(-1, set.getRandom());
    Assert.assertEquals(-1, set.getRandom());
    Assert.assertEquals(-1, set.getRandom());
    Assert.assertEquals(-1, set.getRandom());
  }

  public class RandomizedSet {

    private Map<Integer, Integer> map = new HashMap<>();

    private List<Integer> list = new ArrayList<>();

    Random random = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
      return null != map.putIfAbsent(val, list.size()) ? false : list.add(val);
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
      Integer idx = map.remove(val), last;
      if (idx == null) return false;
      map.computeIfPresent(last = list.get(list.size() - 1), (k, v) -> idx);
      list.set(idx, last);
      list.remove(list.size() - 1);
      return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
      return list.get(random.nextInt(list.size()));
    }
  }
}
