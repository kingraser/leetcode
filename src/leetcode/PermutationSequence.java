package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.Test;

public class PermutationSequence {

  /*
  The set [1,2,3,…,n] contains a total of n! unique permutations.
  
  By listing and labeling all of the permutations in order,
  We get the following sequence (ie, for n = 3):
  
  "123"
  "132"
  "213"
  "231"
  "312"
  "321"
  
  Given n and k, return the kth permutation sequence.
  
  Note: Given n will be between 1 and 9 inclusive.
  */

  public String getPermutation(int n, int k) {
    StringBuilder sb = new StringBuilder(), result = new StringBuilder();
    IntStream.range(1, n + 1).forEach(i -> sb.append(i));
    int[] factor = new int[n];
    factor[0] = 1;
    for (int i = 1; i < n; factor[i] = factor[i - 1] * i, i++);
    k--;
    for (int i = n - 1, code; i >= 0; sb.deleteCharAt(code), k = k % factor[i--])
      result.append(sb.charAt(code = k / factor[i]));
    return result.toString();
  }

  @Test
  public void test() {
    assertEquals("321", getPermutation(3, 6));
  }
}
