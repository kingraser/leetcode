package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class StrobogrammaticNumberII {

  /*
  A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).  
  Find all strobogrammatic numbers that are of length = n.  
  For example,
  Given n = 2, return ["11","69","88","96"].
  Hint:Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(""), findStrobogrammatic(0));
    assertEquals(Arrays.asList("0", "1", "8"), findStrobogrammatic(1));
    assertEquals(Arrays.asList("11", "69", "88", "96"),
        findStrobogrammatic(2).stream().sorted().collect(Collectors.toList()));
    assertEquals(Arrays.asList("101", "111", "181", "609", "619", "689", "808", "818", "888", "906", "916", "986"),
        findStrobogrammatic(3));
    assertEquals(Arrays.asList("1001", "1111", "1691", "1881", "1961", "6009", "6119", "6699", "6889", "6969", "8008",
        "8118", "8698", "8888", "8968", "9006", "9116", "9696", "9886", "9966"), findStrobogrammatic(4));
  }

  private static final char[][] PAIRS = { { '0', '0' }, { '1', '1' }, { '6', '9' }, { '8', '8' }, { '9', '6' } };

  public List<String> findStrobogrammatic(int n) {
    List<String> result = new ArrayList<>();
    dfs(result, new char[n], 0);
    return result;
  }

  private void dfs(List<String> result, char[] s, int idx) {
    if (idx == (s.length + 1) >> 1) {
      result.add(new String(s));
      return;
    }
    for (int i = idx == 0 && s.length > 1 ? 1 : 0, last = s.length - 1; i < PAIRS.length; i++) {
      if (idx == s.length >> 1 && PAIRS[i][0] != PAIRS[i][1]) continue;
      s[idx] = PAIRS[i][0];
      s[last - idx] = PAIRS[i][1];
      dfs(result, s, idx + 1);
    }
  }

}
