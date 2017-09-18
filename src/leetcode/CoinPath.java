package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

public class CoinPath {

  /*
  Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. 
  The integer B denotes that from any place (suppose the index is i) in the array A, 
  you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. 
  Also, if you step on the index i, you have to pay Ai coins. 
  If Ai is -1, it means you can’t jump to the place indexed i in the array.
  
  Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. 
  You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.
  
  If there are multiple paths with the same cost, return the lexicographically smallest such path.
  
  If it's not possible to reach the place indexed N then you need to return an empty array.
  
  Example 1:  
  Input: [1,2,4,-1,2], 2
  Output: [1,3,5]
  
  Example 2:  
  Input: [1,2,4,-1,2], 1
  Output: []
  
  Note:  
    Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
    A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
    Length of A is in the range of [1, 1000].
    B is in the range of [1, 100].  
  */

  @Test
  public void test() {
    int[] A = new int[] { 1, 2, 4, -1, 2 };
    assertEquals(Arrays.asList(1, 3, 5), cheapestJump(A, 2));
    assertEquals(new ArrayList<>(), cheapestJump(A, 1));
  }

  // If there are 2 paths with the same cost, then the longer path is lexicographically smaller 
  public List<Integer> cheapestJump(int[] A, int B) {
    int len = A.length, cost[] = IntStream.range(0, len).map(i -> Integer.MAX_VALUE).toArray(),
        previousIdx[] = IntStream.range(0, len).map(i -> -1).toArray(), length[] = new int[len], i;
    for (i = 0, cost[0] = 0; i < len; i++)
      if (A[i] != -1) for (int j = Math.max(0, i - B), alt; j < i; j++)
        if (A[j] >= 0 && ((alt = cost[j] + A[i]) < cost[i] || alt == cost[i] && length[i] < length[j] + 1)) {
          cost[i] = alt;
          previousIdx[i] = j;
          length[i] = length[j] + 1;
        }
    List<Integer> result = new ArrayList<>();
    for (int cur = len - 1; cur >= 0; cur = previousIdx[cur])
      result.add(0, cur + 1);
    return result.get(0) != 1 ? new ArrayList<>() : result;
  }
}
