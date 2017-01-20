package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class WordPatternII {

  /*
  Given a pattern and a string str, find if str follows the same pattern.  
  Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
  Examples:
        pattern = "abab", str = "redblueredblue" should return true.
        pattern = "aaaa", str = "asdasdasdasd" should return true.
        pattern = "aabb", str = "xyzabcxzyabc" should return false.   
  Notes: You may assume both pattern and str contains only lowercase letters.
  */

  @Test
  public void test() {
    assertFalse(wordPatternMatch("aaaa", "aaa"));
    assertFalse(wordPatternMatch("a", ""));
    assertTrue(wordPatternMatch("abab", "redblueredblue"));
    assertTrue(wordPatternMatch("aaaa", "asdasdasdasd"));
    assertFalse(wordPatternMatch("aabb", "xyzabcxzyabc"));
  }

  public boolean wordPatternMatch(String pattern, String str) {
    return match(pattern, str, new HashMap<>());
  }

  private boolean match(String pattern, String str, Map<Integer, String> map) {
    if (pattern.isEmpty()) return str.isEmpty();
    String s = map.get((int) pattern.charAt(0)), next = pattern.substring(1);
    if (s != null) return str.startsWith(s) ? match(next, str.substring(s.length()), map) : false;
    for (int i = 1, first = pattern.charAt(0); i <= str.length() - next.length(); i++) {
      if (map.containsValue(s = str.substring(0, i))) continue;
      map.put(first, s);
      if (match(next, str.substring(i), map)) return true;
      map.remove(first);
    }
    return false;
  }
}
