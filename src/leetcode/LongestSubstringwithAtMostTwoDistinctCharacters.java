package leetcode;

import static leetcode.LongestSubstringwithAtMostkDistinctCharacters.lengthOfLongestSubstringKDistinct;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestSubstringwithAtMostTwoDistinctCharacters {
  /*
  Given a string S, find the length of the longest substring T that contains at most two distinct characters.
  For example,
  Given S = "eceba",
  T is "ece" which its length is 3. 
  */

  @Test
  public void test() {
    assertEquals(3, lengthOfLongestSubstringTwoDistinct("eceba"));
  }

  public int lengthOfLongestSubstringTwoDistinct(String s) {
    return lengthOfLongestSubstringKDistinct(s, 2);
  }

}
