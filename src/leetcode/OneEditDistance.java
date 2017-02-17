package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OneEditDistance {

  @Test
  public void test() {
    assertTrue(isOneEditDistance("abc", "abd"));
    assertTrue(isOneEditDistance("abc", "ab"));
    assertTrue(isOneEditDistance("ab", "abc"));
    assertFalse(isOneEditDistance("abc", "abc"));
  }

  public boolean isOneEditDistance(String s, String t) {
    return isOneModified(s, t) || isOneDeleted(s, t);
  }

  private boolean isOneModified(String s, String t) {
    if (s.length() != t.length()) return false;
    int count = 0;
    for (int i = 0; i < s.length();)
      if (s.charAt(i) != t.charAt(i++) && count++ > 0) return false;
    return count == 1;
  }

  public boolean isOneDeleted(String longer, String shorter) {
    if (longer.length() < shorter.length()) return isOneDeleted(shorter, longer);
    if (longer.length() - shorter.length() != 1) return false;
    for (int i = 0, j = 0; i < shorter.length();)
      if (shorter.charAt(i++) != longer.charAt(j++) && j - --i > 1) return false;
    return true;
  }

}
