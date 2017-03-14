package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ZigZagConversion {

  /*
  Given string PAYPALISHIRING and rows 3
  P       A       H       N
  A   P   L   S   I   I   G
  Y       I       R
  And then read line by line: "PAHNAPLSIIGYIR"
  */

  /*                           
  Δ=2n-2    1                           2n-1                         4n-3
  Δ=        2                     2n-2  2n                    4n-4   4n-2
  Δ=        3               2n-3        2n+1              4n-5       .
  Δ=        .           .               .               .            .
  Δ=        .       n+2                 .           3n               .
  Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
  Δ=2n-2    n                           3n-2                         5n-4
  */

  @Test
  public void test() {
    assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
  }

  public String convert(String s, int rowCount) {
    if (rowCount == 1) return s;
    char[] result = new char[s.length()];
    for (int row = 0, writeIdx = 0, step1 = (rowCount - 1) << 1; row < rowCount; row++)
      for (int sIdx = row, step2 = row << 1; sIdx < s.length(); sIdx += step1) {
        if (row > 0 && row < rowCount - 1 && sIdx > step2) result[writeIdx++] = s.charAt(sIdx - step2);
        result[writeIdx++] = s.charAt(sIdx);
      }
    return new String(result);
  }

}
