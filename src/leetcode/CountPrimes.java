package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.BitSet;

import org.junit.Test;

public class CountPrimes {

  //Count the number of prime numbers less than a non-negative number, n.
  public int countPrimes(int n) {
    BitSet bs = new BitSet(n);
    bs.set(0, 2);
    int idx = 0, count = 0;
    for (; (idx = bs.nextClearBit(idx + 1)) < n; count++)
      for (int i = idx << 1; i < n; bs.set(i), i += idx);
    return count;
  }

  @Test
  public void test() {
    assertEquals(4, countPrimes(10));
  }

}
