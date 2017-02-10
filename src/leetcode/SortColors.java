/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static leetcode.util.ArrayUtil.swap;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class SortColors {
  /*
  Given an array with n objects colored red, white or blue, 
  sort them so that objects of the same color are adjacent, 
  with the colors in the order red, white and blue.    
  Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
  
  Note:
  You are not suppose to use the library's sort function for this problem. 
  
  Follow up:
  A rather straight forward solution is a two-pass algorithm using counting sort.
  First, iterate the array counting number of 0’s, 1’s, and 2’s, then overwrite array with total number of 0’s,
  then 1’s and followed by 2’s.
  Could you come up with an one-pass algorithm using only constant space?
  */

  @Test
  public void test() {
    int[] actual = new int[] { 0, 1, 2, 0, 1, 2 };
    sortColors(actual);
    assertArrayEquals(new int[] { 0, 0, 1, 1, 2, 2 }, actual);

    actual = new int[] { 0, 1, 2, 0, 1, 2 };
    sortColorsI(actual);
    assertArrayEquals(new int[] { 0, 0, 1, 1, 2, 2 }, actual);
  }

  public void sortColors(int[] A) {
    int[] counts = new int[3];
    for (int i = 0; i < A.length; counts[A[i++]]++);
    for (int i = 0, index = 0; i < 3; i++)
      for (int j = 0; j < counts[i]; A[index++] = i, j++);
  }

  public void sortColorsI(int A[]) {
    int red = 0, blue = A.length - 1;
    for (int i = 0; i < blue + 1;)
      if (A[i] == 0) swap(A, i++, red++);
      else if (A[i] == 2) swap(A, i, blue--);
      else i++;
  }
}
