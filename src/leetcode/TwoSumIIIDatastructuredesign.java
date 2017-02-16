package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TwoSumIIIDatastructuredesign {

  /*
  Design and implement a TwoSum class. It should support the following operations:add and find.  
  add - Add the number to an internal data structure.
  find - Find if there exists any pair of numbers which sum is equal to the value.
  
  For example,
  add(1); add(3); add(5);
  find(4) -> true
  find(7) -> false 
  */

  @Test
  public void test() {
    TwoSum twoSum = new TwoSum();
    twoSum.add(1);
    twoSum.add(3);
    twoSum.add(5);
    assertTrue(twoSum.find(4));
    assertFalse(twoSum.find(7));
  }

  class TwoSum {
    Map<Integer, Integer> map = new HashMap<>();

    public void add(int i) {
      map.compute(i, (k, v) -> v == null ? 1 : v + 1);
    }

    public boolean find(int sum) {
      return ((sum & 1) == 0 && map.getOrDefault(sum >> 1, -1) > 1)
          || map.keySet().stream().anyMatch(i -> map.containsKey(sum - i));
    }
  }

}
