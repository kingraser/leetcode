/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class MaximumProductSubarray {

  /*
  Find the contiguous subarray within an array (containing at least one number) 
  which has the largest product.
  
  For example, given the array [2,3,-2,4],
  the contiguous subarray [2,3] has the largest product = 6. 
  */

  @Test
  public void test() {
    assertEquals(6, maxProduct(new int[] { 2, 3, -2, 4 }));
  }

  public int maxProduct(int[] A) {
    if (A.length == 0) return 0;
    int maxherepre = A[0], minherepre = A[0], maxsofar = A[0], maxhere, minhere;
    for (int i = 1; i < A.length; i++) {
      maxhere = Math.max(Math.max(maxherepre * A[i], minherepre * A[i]), A[i]);
      minhere = Math.min(Math.min(maxherepre * A[i], minherepre * A[i]), A[i]);
      maxsofar = Math.max(maxhere, maxsofar);
      maxherepre = maxhere;
      minherepre = minhere;
    }
    return maxsofar;
  }
}
