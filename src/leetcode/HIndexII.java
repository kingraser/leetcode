package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HIndexII {

  /*
  Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
  Hint:Expected runtime complexity is in O(log n) and the input is sorted.
  */

  public int hIndex(int[] citations) {
    for (int i = 0; i < citations.length; i++)
      if (citations[i] >= citations.length - i) return citations.length - i;
    return 0;
  }

  public int hIndexII(int[] citations) {
    int l = 0, r = citations.length - 1;
    for (int m; l <= r;)
      if (citations[m = (l + r) >> 1] < citations.length - m) l = m + 1;
      else r = m - 1;
    return citations.length - l;
  }

  @Test
  public void test() {
    assertEquals(3, hIndex(new int[] { 0, 1, 3, 5, 6 }));
    assertEquals(1, hIndex(new int[] { 100 }));
  }

  @Test
  public void test2() {
    assertEquals(3, hIndexII(new int[] { 0, 1, 3, 5, 6 }));
    assertEquals(1, hIndexII(new int[] { 100 }));
    assertEquals(0, hIndexII(new int[] { 0 }));
    assertEquals(1, hIndexII(new int[] { 1 }));
    assertEquals(0, hIndexII(new int[] {}));
  }

}
