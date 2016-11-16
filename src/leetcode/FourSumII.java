/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年11月16日;
//-------------------------------------------------------
public class FourSumII {

  /*
  Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.  
  To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. 
  All integers are in the range of -2^28 to 2^28 - 1 and the result is guaranteed to be at most 2^31 - 1.
  
  Example:
  
  Input:
  A = [ 1, 2]
  B = [-2,-1]
  C = [-1, 2]
  D = [ 0, 2]
  
  Output:
  2
  
  Explanation:
  The two tuples are:
  1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
  2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
  */

  @Test
  public void test() {
    Assert.assertEquals(2,
        fourSumCount(new int[] { 1, 2 }, new int[] { -2, -1 }, new int[] { -1, 2 }, new int[] { 0, 2 }));
  }

  public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
    Map<Integer, Integer> map = new HashMap<>();
    int result = 0;
    for (int a : A)
      for (int b : B)
        map.compute(a + b, (k, v) -> Objects.isNull(v) ? 1 : v + 1);
    for (int c : C)
      for (int d : D)
        result += map.getOrDefault(0 - c - d, 0);
    return result;
  }

}
