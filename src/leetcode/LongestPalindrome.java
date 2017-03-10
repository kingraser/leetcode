package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.BitSet;

import org.junit.Test;

public class LongestPalindrome {

  /*
  Given a string which consists of lowercase or uppercase letters, 
  find the length of the longest palindromes that can be built with those letters.  
  This is case sensitive, for example "Aa" is not considered a palindrome here.  
  Note: Assume the length of given string will not exceed 1,010.
  
  Example:  
  Input: "abccccdd"  
  Output: 7  
  Explanation:
  One longest palindrome that can be built is "dccaccd", whose length is 7. 
  */

  @Test
  public void test() {
    assertEquals(7, longestPalindrome("abccccdd"));
  }

  public int longestPalindrome(String s) {
    BitSet bitSet = new BitSet(128);
    int len = 0;
    for (char c : s.toCharArray()) {
      bitSet.flip(c);
      if (!bitSet.get(c)) len += 2;
    }
    return len < s.length() ? ++len : len;
  }
}
