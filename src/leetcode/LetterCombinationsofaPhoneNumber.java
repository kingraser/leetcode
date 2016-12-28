/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月9日<p>
//-------------------------------------------------------
public class LetterCombinationsofaPhoneNumber {

  /*
  Given a digit string, return all possible letter combinations that the number could represent.
  
  A mapping of digit to letters (just like on the telephone buttons) is given below.
      2=>abc  3=>def  4=>ghi
      5=>jkl  6=>mno  7=>pqrs
      8=>tuv  9=>wxyz   
  Input:Digit string "23"
  Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
  
  Note:
  Although the above answer is in lexicographical order, your answer could be in any order you want. 
  */

  String[] map = new String[] { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

  public List<String> letterCombinations(String s) {
    if (s == null || s.length() == 0) return new ArrayList<>();
    int length = getLength(s);
    char[][] result = new char[length][s.length()];
    for (int i = 0, k = 0; i < s.length(); i++)
      for (length /= map[s.charAt(i) - '2'].length(), k = 0; k < result.length;)
        for (int j = 0; j < map[s.charAt(i) - '2'].length(); j++)
          for (int l = 0; l < length; l++)
            result[k++][i] = map[s.charAt(i) - '2'].charAt(j);
    return Arrays.stream(result).map(a -> new String(a)).collect(Collectors.toList());
  }

  private int getLength(String s) {
    int result = 1;
    for (char c : s.toCharArray())
      result *= map[c - '2'].length();
    return result;
  }

  @Test
  public void test() {
    assertEquals(Sets.newHashSet("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"),
        new HashSet<>(letterCombinations("23")));
  }
}
