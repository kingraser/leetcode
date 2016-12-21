/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class HIndex {
  /*
  Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
  
  According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
  
  For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
  
  Note: If there are several possible values for h, the maximum one is taken as the h-index.
  
  Hint:
  
    An easy approach is to sort the array first.
    What are the possible values of h-index?
    A faster approach is to use extra space     
  */

  //1 O(n*log(n))
  public int hIndex(int[] citations) {
    Arrays.sort(citations);
    for (int i = 0; i < citations.length; i++)
      if (citations[i] >= citations.length - i) return citations.length - i;
    return 0;
  }

  //1 O(n*log(n)) optimization
  public int hIndexI(int[] citations) {
    Arrays.sort(citations);
    int l = 0, r = citations.length - 1;
    for (int m; l <= r;)
      if (citations[m = (l + r) >> 1] < citations.length - m) l = m + 1;
      else r = m - 1;
    return citations.length - l;
  }

  //2 O(n)
  public int hIndexII(int[] citations) {
    int[] array = new int[citations.length + 1];
    Arrays.stream(citations).forEach(i -> array[Math.min(citations.length, i)]++);
    for (int i = citations.length, t = 0; i >= 0; i--)
      if ((t += array[i]) >= i) return i;
    return 0;
  }

  @Test
  public void test() {
    assertEquals(3, hIndex(new int[] { 3, 0, 6, 1, 5 }));
    assertEquals(1, hIndex(new int[] { 100 }));

    assertEquals(3, hIndexI(new int[] { 3, 0, 6, 1, 5 }));
    assertEquals(1, hIndexI(new int[] { 100 }));

    assertEquals(3, hIndexII(new int[] { 3, 0, 6, 1, 5 }));
    assertEquals(1, hIndexII(new int[] { 100 }));
  }
}
