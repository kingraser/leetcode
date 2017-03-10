package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RearrangeStringkDistanceApart {

  /*
  Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.  
  All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
  
  Example 1:  
  str = "aabbcc", k = 3  
  Result: "abcabc"  
  The same letters are at least distance 3 from each other.
  
  Example 2:  
  str = "aaabc", k = 3   
  Answer: ""  
  It is not possible to rearrange the string.
  
  Example 3:  
  str = "aaadbbcc", k = 2  
  Answer: "abacabcd"  
  Another possible answer is: "abcabcda"  
  The same letters are at least distance 2 from each other.
  */

  @Test
  public void test() {
    assertEquals("abacabcd", rearrangeString("aaadbbcc", 2));
    assertEquals("", rearrangeString("aaabc", 3));
    assertEquals("abcabc", rearrangeString("aabbcc", 3));
  }

  public String rearrangeString(String s, int k) {
    if (s == null || k < 2) return s;
    int[] validPosition = new int[26], letterCounts = new int[26];
    char[] result = new char[s.length()];
    s.chars().forEach(c -> letterCounts[c - 'a']++);
    for (int i = 0, nextLetter; i < s.length(); i++) {
      if ((nextLetter = findNext(letterCounts, validPosition, i)) == -1) return "";
      result[i] = (char) ('a' + nextLetter);
      validPosition[nextLetter] = i + k;
      letterCounts[nextLetter]--;
    }
    return new String(result);
  }

  public int findNext(int[] letterCounts, int[] validPosition, int index) {
    int max = 0, result = -1;
    for (int i = 0; i < 26; i++)
      if (letterCounts[i] > max && validPosition[i] <= index) {
        result = i;
        max = letterCounts[i];
      }
    return result;
  }
}
