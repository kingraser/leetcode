package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Test;

public class IsomorphicStrings {

  /*
  For example,
  Given "egg", "add", return true.    
  Given "foo", "bar", return false.    
  Given "paper", "title", return true.
  */

  @Test
  public void test() {
    assertTrue(isIsomorphic("egg", "add"));
    assertTrue(isIsomorphic("paper", "title"));
    assertFalse(isIsomorphic("foo", "bar"));
  }

  public boolean isIsomorphic(String s, String t) {
    if (s.length() != t.length()) return false;
    Map<Character, Character> map = new HashMap<>();
    return IntStream.range(0, s.length())
        .allMatch(i -> map.computeIfAbsent(s.charAt(i), k -> t.charAt(i)) == t.charAt(i));
  }
}
