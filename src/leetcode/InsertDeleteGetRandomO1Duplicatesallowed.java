package leetcode;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

public class InsertDeleteGetRandomO1Duplicatesallowed {

  /*
  Design a data structure that supports all following operations in average O(1) time.
  Note: Duplicate elements are allowed.
  
    insert(val): Inserts an item val to the collection.
    remove(val): Removes an item val from the collection if present.
    getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains. 
  */

  @Test
  public void test() {
    RandomizedCollection collection = new RandomizedCollection();
    assertTrue(collection.insert(1));
    assertTrue(collection.insert(2));
    assertFalse(collection.insert(1));
    assertTrue(Sets.newHashSet(1, 2).contains(collection.getRandom()));
    assertTrue(collection.remove(1));
    assertTrue(Sets.newHashSet(1, 2).contains(collection.getRandom()));
    assertTrue(collection.remove(1));
    assertEquals(2, collection.getRandom());
    assertTrue(collection.remove(2));
  }

  public class RandomizedCollection {
    Map<Integer, Set<Integer>> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    Random random = new Random();
    Set<Integer> set;

    public boolean insert(int val) {
      (set = map.computeIfAbsent(val, k -> new HashSet<>())).add(list.size());
      list.add(val);
      return set.size() == 1;
    }

    public boolean remove(int val) {
      if ((set = map.get(val)) == null || set.isEmpty()) return false;
      int idx = set.iterator().next(), lastIdx = list.size() - 1, last = list.get(lastIdx);
      set.remove(idx);
      if (idx != lastIdx) {
        (set = map.get(last)).remove(lastIdx);
        set.add(idx);
        list.set(idx, last);
      }
      list.remove(lastIdx);
      return true;
    }

    public int getRandom() {
      return list.get(random.nextInt(list.size()));
    }
  }
}
