package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidAnagram {

  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    int[] map = new int[128];
    s.chars().forEach(c -> map[c]++);
    return t.chars().noneMatch(c -> map[c]-- == 0);
  }

  @Test
  public void test() {
    assertTrue(isAnagram("anagram", "nagaram"));
    assertFalse(isAnagram("rat", "car"));
  }

}
