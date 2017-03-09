package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class WordPattern {

  /*
  Given a pattern and a string str, find if str follows the same pattern.
  
  Examples:    
  pattern = "abba", str = "dog cat cat dog" should return true.
  pattern = "abba", str = "dog cat cat fish" should return false.
  pattern = "aaaa", str = "dog cat cat dog" should return false.
  pattern = "abba", str = "dog dog dog dog" should return false.
  
  Notes:    
  pattern contains only lowercase alphabetical letters, and str contains words separated by a single space. 
  Each word in str contains only lowercase alphabetical letters.
  Both pattern and str do not have leading or trailing spaces.
  Each letter in pattern must map to a word with length that is at least 1.    
  */

  public boolean wordPattern(String pattern, String str) {
    String[] array = str.split(" ");
    if (array.length != pattern.length()) return false;
    Map<String, Integer> map = new HashMap<>();
    Map<Integer, String> map2 = new HashMap<>();
    Iterator<String> iterator = Arrays.stream(array).iterator();
    return pattern.chars().allMatch(c -> {
      String s = iterator.next();
      return s.equals(map2.computeIfAbsent(c, k -> s)) && c == map.computeIfAbsent(s, k -> c).intValue();
    });
  }

  @Test
  public void test() {
    assertTrue(wordPattern("abba", "dog cat cat dog"));
    assertFalse(wordPattern("abba", "dog cat cat fish"));
    assertFalse(wordPattern("abba", "dog dog dog dog"));
    assertFalse(wordPattern("aaaa", "dog cat cat dog"));
  }
}
