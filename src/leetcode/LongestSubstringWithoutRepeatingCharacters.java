package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestSubstringWithoutRepeatingCharacters {

  /*
  Given a string, find the length of the longest substring without repeating characters. 
  Examples:  
  Given "abcabcbb", the answer is "abc", which the length is 3.
  Given "bbbbb", the answer is "b", with the length of 1.  
  Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
  */

  @Test
  public void test() {
    assertEquals(2, lengthOfLongestSubstring("abba"));
    assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
    assertEquals(1, lengthOfLongestSubstring("bbbbb"));
  }

  public int lengthOfLongestSubstring(String s) {
    int result = 0, map[] = new int[128];
    for (int idx = 0, left = 0, c, len = 0; idx < s.length(); map[c] = idx, result = Math.max(result, len))
      len = map[c = s.charAt(idx++)] == 0 ? ++len : idx - (left = Math.max(left, map[c]));
    return result;
  }

}
