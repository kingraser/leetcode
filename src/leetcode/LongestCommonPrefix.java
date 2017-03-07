package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Objects;

import org.junit.Test;

public class LongestCommonPrefix {

  // Write a function to find the longest common prefix string amongst an array of strings. 

  @Test
  public void test() {
    assertEquals("ab", longestCommonPrefix(new String[] { "abc", "abcd", "ab", "abde" }));
  }

  public String longestCommonPrefix(String[] strs) {
    if (Objects.isNull(strs) || strs.length == 0) return "";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strs[0].length(); sb.append(strs[0].charAt(i++)))
      for (int j = 1; j < strs.length; j++)
        if (i == strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) return sb.toString();
    return sb.toString();
  }

}
