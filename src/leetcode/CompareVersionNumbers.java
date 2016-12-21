/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class CompareVersionNumbers {

  /*
  If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
  0.1 < 1.1 < 1.2 < 13.37
  */

  @Test
  public void test() {
    List<String> actual = Arrays.asList("1.1", "13.37", "1.2", "0.1");
    Collections.sort(actual, (s1, s2) -> compareVersion(s1, s2));
    assertEquals(Arrays.asList("0.1", "1.1", "1.2", "13.37"), actual);
  }

  public int compareVersion(String version1, String version2) {
    String[] v1s = version1.split("\\."), v2s = version2.split("\\.");
    for (int i = 0, len = Math.max(v1s.length, v2s.length); i < len; i++) {
      int ver1 = i < v1s.length ? Integer.parseInt(v1s[i]) : 0;
      int ver2 = i < v2s.length ? Integer.parseInt(v2s[i]) : 0;
      if (ver1 > ver2) return 1;
      if (ver1 < ver2) return -1;
    }
    return 0;
  }

}
