package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UniqueSubstringsinWraparoundString {

  /*
  Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....". 
  Now we have another string p. Your job is to find out how many unique non-empty substrings of p are present in s. In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.
  Note: p consists of only lowercase English letters and the size of p might be over 10000.
  
  Example 1:  
  Input: "a"
  Output: 1  
  Explanation: Only the substring "a" of string "a" is in the string s.
  
  Example 2:  
  Input: "cac"
  Output: 2
  Explanation: There are two substrings "a", "c" of string "cac" in the string s.
  
  Example 3:  
  Input: "zab"
  Output: 6
  Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s. 
  */

  @Test
  public void test() {
    assertEquals(1, findSubstringInWraproundString("a"));
    assertEquals(2, findSubstringInWraproundString("cac"));
    assertEquals(6, findSubstringInWraproundString("zab"));
  }

  public int findSubstringInWraproundString(String p) {
    int sum = 0, count[] = new int[26];
    for (int i = 0, max = 0, idx; i < p.length(); i++)
      count[idx = p.charAt(i) - 'a'] = Math.max(count[idx],
          i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25)) ? ++max : (max = 1));
    for (int i = 0; i < 26; sum += count[i++]);
    return sum;
  }

}
