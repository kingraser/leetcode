/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年11月8日;
//-------------------------------------------------------
public class ArithmeticSlicesIISubsequence {

  @Test
  public void test() {
    Assert.assertEquals(7, numberOfArithmeticSlices(new int[] { 2, 4, 6, 8, 10 }));
    Assert.assertEquals(5, numberOfArithmeticSlices(new int[] { 7, 7, 7, 7 }));
    Assert.assertEquals(1030,
        numberOfArithmeticSlices(new int[] { 79, 20, 64, 28, 67, 81, 60, 58, 97, 85, 92, 96, 82, 89, 46, 50, 15, 2, 36,
            44, 54, 2, 90, 37, 7, 79, 26, 40, 34, 67, 64, 28, 60, 89, 46, 31, 9, 95, 43, 19, 47, 64, 48, 95, 80, 31, 47,
            19, 72, 99, 28, 46, 13, 9, 64, 4, 68, 74, 50, 28, 69, 94, 93, 3, 80, 78, 23, 80, 43, 49, 77, 18, 68, 28, 13,
            61, 34, 44, 80, 70, 55, 85, 0, 37, 93, 40, 47, 47, 45, 23, 26, 74, 45, 67, 34, 20, 33, 71, 48, 96 }));
  }

  public int numberOfArithmeticSlices(int[] A) {
    int result = 0;
    List<Map<Integer, Integer>> list = new ArrayList<>(A.length);
    for (int i = 0; i < A.length; i++) {
      list.add(new HashMap<>(i, 1f));
      for (int j = 0, count; j < i; j++) {
        long distance = ((long) A[i]) - A[j];
        if (distance > Integer.MAX_VALUE || distance < Integer.MIN_VALUE) continue;
        result += count = list.get(j).getOrDefault((int) distance, 0);
        add(list.get(i), (int) distance, count + 1);
      }
    }
    return (int) result;
  }

  private void add(Map<Integer, Integer> map, int key, int value) {
    map.compute(key, (k, v) -> (v == null ? 0 : v) + value);
  }

}
