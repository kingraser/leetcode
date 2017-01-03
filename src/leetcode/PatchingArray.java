/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月30日;
//-------------------------------------------------------
public class PatchingArray {

  @Test
  public void test() {
    assertEquals(1, minPatches(new int[] { 1, 3 }, 6));
  }

  public int minPatches(int[] nums, int n) {
    int i = 0, added = 0;
    for (long miss = 1; miss <= n;)
      if (i < nums.length && nums[i] <= miss) miss += nums[i++];
      else {
        miss <<= 1;
        added++;
      }
    return added;
  }

}
