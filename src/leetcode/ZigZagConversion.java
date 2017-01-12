/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月7日<p>
//-------------------------------------------------------
public class ZigZagConversion {

  /*
  Given string PAYPALISHIRING and rows 3
  P       A       H       N
  A   P   L   S   I   I   G
  Y       I       R
  And then read line by line: "PAHNAPLSIIGYIR"
                             
  Δ=2n-2    1                           2n-1                         4n-3
  Δ=        2                     2n-2  2n                    4n-4   4n-2
  Δ=        3               2n-3        2n+1              4n-5       .
  Δ=        .           .               .               .            .
  Δ=        .       n+2                 .           3n               .
  Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
  Δ=2n-2    n                           3n-2                         5n-4
  */

  public String convert(String s, int row) {
    if (row == 1) return s;
    char[] result = new char[s.length()];
    for (int rowIdx = 0, writeIdx = 0, step = (row - 1) << 1; rowIdx < row; rowIdx++)
      for (int sIdx = rowIdx, step2 = rowIdx << 1; sIdx < s.length();) {
        result[writeIdx++] = s.charAt(sIdx);
        if ((sIdx += step) - step2 < s.length() && rowIdx > 0 && rowIdx < row - 1)
          result[writeIdx++] = s.charAt(sIdx - step2);
      }
    return new String(result);
  }

  @Test
  public void test() {
    assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
  }

}
