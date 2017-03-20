package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

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
    for (int idx = 0, len = Math.max(v1s.length, v2s.length), ver1, ver2; idx < len; idx++)
      if ((ver1 = getInt(v1s, idx)) > (ver2 = getInt(v2s, idx))) return 1;
      else if (ver1 < ver2) return -1;
    return 0;
  }

  private int getInt(String[] s, int idx) {
    return idx >= s.length ? 0 : Integer.parseInt(s[idx]);
  }

}
