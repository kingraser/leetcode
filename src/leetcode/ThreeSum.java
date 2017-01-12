/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class ThreeSum {

  /*
          给一int数组和int target,求三元素使和为target(本题锁定为0).需要去重    
  For example, given array S = {-1 0 1 2 -1 -4},  
      A solution set is:
      (-1, 0, 1)
      (-1, -1, 2)    
          思路:把问题转为2sum,去重的问题在于转化2sum的头指针(第二个下标)在第一个下标之后
  */

  public List<List<Integer>> threeSum(int[] num) {
    Arrays.sort(num);
    return FourSum.kSum(num, 0, 3, 0);
  }

  @Test
  public void test() {
    assertEquals(Stream.of(Arrays.asList(-1, 0, 1), Arrays.asList(-1, -1, 2)).collect(Collectors.toSet()),
        new HashSet<>(threeSum(new int[] { -1, 0, 1, 2, -1, -4 })));
  }

}
