/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class AddDigits {

  /*
  For example:
  Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.  
  */

  @Test
  public void test() {
    assertEquals(2, addDigits(38));
  }

  public int addDigits(int num) {
    return num == 0 ? 0 : num % 9 == 0 ? 9 : num % 9;
  }

}
