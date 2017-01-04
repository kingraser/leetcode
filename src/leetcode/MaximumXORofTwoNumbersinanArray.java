package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class MaximumXORofTwoNumbersinanArray {

  /*
  Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
  
  Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
  
  Could you do this in O(n) runtime?
  
  Example:
  
  Input: [3, 10, 5, 25, 2, 8]
  
  Output: 28
  
  Explanation: The maximum result is 5 ^ 25 = 28.  
  */

  @Test
  public void test() {
    assertEquals(28, findMaximumXOR(new int[] { 3, 10, 5, 25, 2, 8 }));
  }

  public int findMaximumXOR(int[] nums) {
    int max = 0;
    for (int bit = 1 << 30, mask = bit, tmp = bit; bit != 0; bit >>= 1, mask |= bit, tmp = max | bit) {
      Set<Integer> set = new HashSet<>();
      for (int num : nums)
        set.add(num & mask);
      for (int prefix : set)
        if (set.contains(tmp ^ prefix)) {// cause if a ^ b = c then a = b ^ c
          max = tmp;
          break;
        }
    }
    return max;
  }
}
