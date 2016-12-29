package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestRepeatingCharacterReplacement {

  /*
  Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.
  
  Note:
  Both the string's length and k will not exceed 10^4.
  
  Example 1:
  
  Input:
  s = "ABAB", k = 2
  
  Output:
  4
  
  Explanation:
  Replace the two 'A's with two 'B's or vice versa.
  
  Example 2:
  
  Input:
  s = "AABABBA", k = 1
  
  Output:
  4
  
  Explanation:
  Replace the one 'A' in the middle with 'B' and form "AABBBBA".
  The substring "BBBB" has the longest repeating letters, which is 4. 
  */

  @Test
  public void test() {
    assertEquals(4, characterReplacement("ABAB", 2));
    assertEquals(4, characterReplacement("AABABBA", 1));
  }

  public int characterReplacement(String s, int k) {
    int begin = 0;
    int[] count = new int[128];
    for (int end = 0, max = 0; end < s.length(); end++)
      if ((max = Math.max(max, ++count[s.charAt(end)])) + k <= end - begin) count[s.charAt(begin++)]--;
    return s.length() - begin;
  }
}
