/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class MajorityElement {

  /*    
  Given an array of size n, find the majority element. 
  The majority element is the element that appears more than n/2 times.
  */

  @Test
  public void test() {
    assertEquals(1, majorityElement(new int[] { 2, 3, 4, 1, 1, 1, 1 }));
  }

  public int majorityElement(int[] num) {
    int major = num[0];
    for (int i = 1, count = 1; i < num.length; i++)
      if (major == num[i]) count++;
      else if (count-- == 0) {
        count = 1;
        major = num[i];
      }
    return major;
  }
}
