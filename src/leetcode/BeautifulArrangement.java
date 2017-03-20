package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.BitSet;

import org.junit.Test;

public class BeautifulArrangement {

  /*
  Suppose you have N integers from 1 to N. 
  We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 ≤ i ≤ N) in this array:
    The number at the ith position is divisible by i.
    i is divisible by the number at the ith position.  
  Now given N, how many beautiful arrangements can you construct?
  
  Example 1:  
  Input: 2
  Output: 2
  Explanation:   
  The first beautiful arrangement is [1, 2]:  
  Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).  
  Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).  
  The second beautiful arrangement is [2, 1]:  
  Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).  
  Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.  
  Note:  
    N is a positive integer and will not exceed 15.
  */

  @Test
  public void test() {
    assertEquals(2, countArrangement(2));
    assertEquals(24679, countArrangement(15));
  }

  public int countArrangement(int N) {
    return dfs(1, new BitSet(), 0, ++N);
  }

  private int dfs(int idx, BitSet used, int count, int n) {
    if (idx == n) return ++count;
    for (int i = used.nextClearBit(1); i < n; i = used.nextClearBit(++i))
      if (i % idx == 0 || idx % i == 0) {
        used.set(i);
        count = dfs(idx + 1, used, count, n);
        used.clear(i);
      }
    return count;
  }
}
