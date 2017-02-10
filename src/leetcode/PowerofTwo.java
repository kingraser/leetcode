package leetcode;
/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class PowerofTwo {

  /*
  Given an integer, write a function to determine if it is a power of two
  */

  @Test
  public void test() {
    assertTrue(isPowerOfTwo(16));
    assertFalse(isPowerOfTwo(5));
  }

  public boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
  }

}
