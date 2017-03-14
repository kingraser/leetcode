package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestSubstringwithAtMostKDistinctCharacters {

  /*
  Given a string, find the length of the longest substring T that contains at most k distinct characters.  
  For example, Given s = "eceba" and k = 2.  
  T is "ece" which its length is 3.
  */

  @Test
  public void test() {
    assertEquals(3, lengthOfLongestSubstringKDistinct("eceba", 2));
    assertEquals(4, lengthOfLongestSubstringKDistinct("eceba", 3));
  }

  public static int lengthOfLongestSubstringKDistinct(String s, int k) {
    int result = 0, map[] = new int[128];
    for (int left = 0, right = 0, count = 0; right < s.length(); result = Math.max(result, ++right - left))
      if (map[s.charAt(right)]++ == 0 && ++count > k) {
        while (--map[s.charAt(left++)] > 0);
        count--;
      }
    return result;
  }
}
