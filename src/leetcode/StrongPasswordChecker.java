package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;

import org.junit.Test;

public class StrongPasswordChecker {

  /*
  A password is considered strong if below conditions are all met:
  
    It has at least 6 characters and at most 20 characters.
    It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
    It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
  
  Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to make s a strong password. If s is already strong, return 0.
  
  Insertion, deletion or replace of any one character are all considered as one change.
  */

  @Test
  public void test() {
    assertEquals(3, strongPasswordChecker("aaaabbaaabbaaa123456A"));
    assertEquals(4, strongPasswordChecker("AAAAAABBBBBB123456789a"));
    assertEquals(3, strongPasswordChecker("ppppppppp"));
    assertEquals(2, strongPasswordChecker("aaa111"));
    assertEquals(6, strongPasswordChecker(""));
    assertEquals(3, strongPasswordChecker("1234567890123456Baaaaa"));
    assertEquals(1, strongPasswordChecker("aaa123"));
    assertEquals(3, strongPasswordChecker("1111111111"));
  }

  public int strongPasswordChecker(String s) {
    CheckResult result = check(s);
    int count = 0, len = s.length(), delete = len - 20, insert = 6 - len, change = 3 - result.types;
    for (int e; !result.repeats.isEmpty() && delete-- > 0; count++)
      if ((e = result.repeats.poll()) > 3) result.repeats.add(--e);
    for (int e; !result.repeats.isEmpty() && insert-- > 0; change--, count++)
      if ((e = result.repeats.poll()) > 4) result.repeats.add(e - 2);
    for (int e; !result.repeats.isEmpty() && change-- > 0; count++)
      if ((e = result.repeats.poll()) > 5) result.repeats.add(e - 3);
    return Math.max(0, delete) + Math.max(0, Math.max(insert, change))
        + result.repeats.stream().mapToInt(i -> i / 3).sum() + count;
  }

  private CheckResult check(String s) {
    CheckResult result = new CheckResult();
    int low = 0, up = 0, digit = 0;
    for (int i = 0, j, c, len = s.length(), repeat; i < len; i = j) {
      if ((c = s.charAt(i)) >= 'a' && c <= 'z') low = 1;
      else if (c >= 'A' && c <= 'Z') up = 1;
      else if (c >= '0' && c <= '9') digit = 1;
      for (j = i + 1; j < len && c == s.charAt(j); j++);
      if ((repeat = j - i) > 2) result.repeats.add(repeat);
    }
    result.types = low + up + digit;
    return result;
  }

  class CheckResult {
    int types = 0;
    ArrayDeque<Integer> repeats = new ArrayDeque<>();
  }
}
