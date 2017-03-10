package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.util.MathUtil;

public class EncodeStringwithShortestLength {

  /*
  Given a non-empty string, encode the string such that its encoded length is the shortest.  
  The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
  
  Note:  
    k will be a positive integer and encoded string will not be empty or have extra space.
    You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
    If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
  
  Example 1:  
  Input: "aaa"
  Output: "aaa"
  Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
  
  Example 2:  
  Input: "aaaaa"
  Output: "5[a]"
  Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
  
  Example 3:  
  Input: "aaaaaaaaaa"
  Output: "10[a]"
  Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
  
  Example 4:  
  Input: "aabcaabcd"
  Output: "2[aabc]d"
  Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
  
  Example 5:  
  Input: "abbbabbbcabbbabbbc"
  Output: "2[2[abbb]c]"
  Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".  
  */

  @Test
  public void test() {
    assertEquals("aaa".length(), encode("aaa").length());
    assertEquals("5[a]".length(), encode("aaaaa").length());
    assertEquals("10[a]".length(), encode("aaaaaaaaaa").length());
    assertEquals("2[aabc]d".length(), encode("aabcaabcd").length());
    assertEquals("2[2[abbb]c]".length(), encode("abbbabbbcabbbabbbc").length());
  }

  public String encode(String s) {
    String sub, dp[][] = new String[s.length()][s.length()]; // sub for substring; dp[i][j] represents the shortest encode of substring (i, j). i, j are both inclusive
    for (int subLen = 1; subLen <= s.length(); subLen++) // subLen represents the length of the substring to be encoded
      for (int left = 0, right = left + subLen - 1; right < s.length(); left++, right++)
        if ((dp[left][right] = sub = s.substring(left, left + subLen)).length() > 4) {
          for (int i = left; i < right; i++)
            if (dp[left][i].length() + dp[i + 1][right].length() < dp[left][right].length())
              dp[left][right] = dp[left][i] + dp[i + 1][right];
          for (int patternLen = 1, repeat; patternLen <= (subLen >> 1); patternLen++) // patternLen represents length of the pattern in substring
            if (subLen % patternLen == 0
                && dp[left][left + patternLen - 1].length() + 2 // 2 for '[' and ']'
                    + MathUtil.log10(repeat = subLen / patternLen) < dp[left][right].length()
                && sub.replaceAll(sub.substring(0, patternLen), "").isEmpty())
              dp[left][right] = String.format("%d[%s]", repeat, dp[left][left + patternLen - 1]);
        }
    return dp[0][s.length() - 1];
  }
}
