package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StrobogrammaticNumberIII {

  /*
  A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).  
  Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
  
  For example,
  Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
  
  Note:
  Because the range might be a large number, the low and high numbers are represented as string.
  */

  @Test
  public void test() {
    assertEquals(3, strobogrammaticInRange("50", "100"));
  }

  int result;
  private static final char[][] PAIRS = { { '0', '0' }, { '1', '1' }, { '6', '9' }, { '8', '8' }, { '9', '6' } };

  public int strobogrammaticInRange(String low, String high) {
    result = 0;
    for (int len = low.length(); len <= high.length(); len++)
      dfs(new char[len], 0, low.toCharArray(), high.toCharArray());
    return result;
  }

  private void dfs(char[] cs, int idx, char[] low, char[] high) {
    if (idx == (cs.length + 1) >> 1) {
      if (compare(low, cs) <= 0 && compare(cs, high) <= 0) result++;
      return;
    }
    for (int i = idx == 0 && cs.length > 1 ? 1 : 0, last = cs.length - 1; i < PAIRS.length; i++) {
      if (idx == cs.length >> 1 && PAIRS[i][0] != PAIRS[i][1]) continue;
      cs[idx] = PAIRS[i][0];
      cs[last - idx] = PAIRS[i][1];
      dfs(cs, idx + 1, low, high);
    }
  }

  private int compare(char[] A, char[] B) {
    if (A.length != B.length) return A.length - B.length;
    for (int i = 0, diff; i < A.length; i++)
      if ((diff = A[i] - B[i]) != 0) return diff;
    return 0;
  }
}
