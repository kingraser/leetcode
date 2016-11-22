/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月22日;
//-------------------------------------------------------
public class EliminationGame {

  /*
  There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.  
  Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.  
  We keep repeating the steps again, alternating left to right and right to left, until a single number remains.  
  Find the last number that remains starting with a list of length n.  
  Example:  
  Input:
  n = 9,
  1 2 3 4 5 6 7 8 9
  2 4 6 8
  2 6
  6  
  Output:
  6
  */

  /*
      第一次从左往右删除的时候，奇数都被删掉了，剩下的都是偶数。如果我们对所有数都除以2，那么得到一个1到n/2的新数列。
      下一次我们从右往左删出，那么返回的结果应该是调用递归的结果lastRemaining(n/2)在数组1到n/2之间的镜像。
      何为镜像，比如1, 2, 3, 4这个数字，2的镜像就是3, 1的镜像是4
  */
  public int lastRemaining(int n) {
    return n == 1 ? 1 : (1 + (n >>= 1) - lastRemaining(n)) << 1;
  }

  @Test
  public void test() {
    Assert.assertEquals(6, lastRemaining(9));
  }
}
