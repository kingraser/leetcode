/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.BitSet;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年10月10日;
//-------------------------------------------------------
public class PartitionEqualSubsetSum {

  /*
  Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
  
  Note:
  Both the array size and each of the array element will not exceed 100.
  
  Example 1:    
  Input: [1, 5, 11, 5]    
  Output: true    
  Explanation: The array can be partitioned as [1, 5, 5] and [11].
  
  Example 2:    
  Input: [1, 2, 3, 5]    
  Output: false    
  Explanation: The array cannot be partitioned into equal sum subsets.
  */

  @Test
  public void test() {
    assertTrue(canPartition(new int[] { 1, 5, 11, 5 }));
    assertTrue(canPartitionII(new int[] { 1, 5, 11, 5 }));
  }

  //dfs
  public boolean canPartition(int[] nums) {
    int sum = Arrays.stream(nums).sum();
    return (sum & 1) == 0 && canSum(nums, 0, sum >> 1);
  }

  private boolean canSum(int[] nums, int begin, int target) {
    if (begin == nums.length || target <= 0) return 0 == target;
    return canSum(nums, begin + 1, target - nums[begin]) || canSum(nums, begin + 1, target);
  }

  public boolean canPartitionII(int[] nums) {
    BitSet bs = new BitSet(5001);
    bs.set(0);
    int sum = 0;
    for (int n : nums) {
      for (int i = sum; -1 != (i = bs.previousSetBit(i)) && i < 5001;)
        bs.set(i-- + n);
      sum += n;
    }
    return (sum & 1) == 0 && bs.get(sum >> 1);
  }

}
