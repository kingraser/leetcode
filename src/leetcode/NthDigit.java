/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月19日;
//-------------------------------------------------------
public class NthDigit {

  /*  
  find the length of the number where the nth digit is from
  find the actual number where the nth digit is from
  find the nth digit and return
  */

  @Test
  public void test() {
    assertEquals(3, findNthDigit(3));
    assertEquals(0, findNthDigit(11));
  }

  static int[] powOfTen = new int[] { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000 };

  public int findNthDigit(int n) {
    int len = 1, count = 9, start = 1;
    for (; n > (long) len * count; n -= (count * len++), count *= 10, start *= 10);
    return getNDigit(start + --n / len, len - n % len);
  }

  /**
   * @param n
   *          整数
   * @param k
   * @return n的从最右位开始数第k位
   */
  public int getNDigit(int n, int k) {
    return (n % powOfTen[k]) / powOfTen[--k];
  }

}
