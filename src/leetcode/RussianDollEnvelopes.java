package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class RussianDollEnvelopes {

  /*
  You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
  One envelope can fit into another if and only if 
  both the width and height of one envelope is greater than the width and height of the other envelope.
  
  What is the maximum number of envelopes can you Russian doll? (put one inside other)
  
  Example:
  Given envelopes = [[5,4],[6,4],[6,7],[2,3]], 
  the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]). 
  */

  @Test
  public void test() {
    assertEquals(3, maxEnvelopes(new int[][] { { 5, 4 }, { 6, 4 }, { 6, 7 }, { 2, 3 } }));
  }

  public int maxEnvelopes(int[][] envelopes) {
    int len = 0;
    Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
    int dp[] = new int[envelopes.length];
    for (int[] envelope : envelopes) {
      int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
      if (index < 0) index = -(index + 1);
      dp[index] = envelope[1];
      if (index == len) len++;
    }
    return len;
  }

}
