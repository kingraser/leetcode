/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class GrayCode {
  /*
  The gray code is a binary numeral system where two successive values differ in only one bit.    
  Given a non-negative integer n representing the total number of bits in the code, 
  print the sequence of gray code. A gray code sequence must begin with 0.
  
  For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
  
  00 - 0
  01 - 1
  11 - 3
  10 - 2
  
  Note:
  For a given n, a gray code sequence is not uniquely defined.    
  For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
  For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
  */

  /*
  The definition of Gray Code is here http://en.wikipedia.org/wiki/Gray_code
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(0, 1, 3, 2), grayCode(2));
  }

  public List<Integer> grayCode(int n) {
    List<Integer> result = new ArrayList<>(1 << n);// 2^n
    for (int i = 0, size = 1 << n; i < size; result.add(i ^ (i >> 1)), i++);
    return result;
  }
}
