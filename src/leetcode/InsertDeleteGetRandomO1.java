package leetcode;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
    assertTrue(set.insert(1));
    assertTrue(set.insert(2));
    assertFalse(set.insert(1));
    assertTrue(Set.of(1, 2).contains(set.getRandom()));
    assertTrue(set.remove(1));
    assertFalse(set.remove(1));
    assertEquals(2, set.getRandom());
    assertTrue(set.remove(2));
  }

  public static class RandomizedSet {
    private final Map<Integer, Integer> map = new HashMap<>();
    private final List<Integer> list = new ArrayList<>();
    Random random = new Random();

    @SuppressWarnings("ConstantValue")
    public boolean insert(int val) {
      return Objects.isNull(map.putIfAbsent(val, list.size())) && list.add(val);
    }

    public boolean remove(int val) {
      Integer idx = map.remove(val), last;
      if (Objects.isNull(idx)) return false;
      map.computeIfPresent(last = list.getLast(), (k, v) -> idx);
      list.set(idx, last);
      list.removeLast();
      return true;
    }

    public int getRandom() {
      return list.get(random.nextInt(list.size()));
    }
  }
}
