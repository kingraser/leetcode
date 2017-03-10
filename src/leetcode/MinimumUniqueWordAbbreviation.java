package leetcode;

import static java.lang.Character.isDigit;
import static leetcode.GeneralizedAbbreviation.generateAbbreviations;
import static leetcode.ValidWordAbbreviation.validWordAbbreviation;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class MinimumUniqueWordAbbreviation {

  /*
  A string such as "word" contains the following abbreviations:  
  ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
  Given a target string and a set of strings in a dictionary, 
  find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.
  Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.
  
  Note:  
    In the case of multiple answers as shown in the second example below, you may return any one of them.
    Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
  
  Examples:  
  "apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")  
  "apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").
  */

  @Test
  public void test() {
    assertEquals(2, minAbbreviation("apple", new String[] { "blade" }).length());
    assertEquals(3, minAbbreviation("apple", new String[] { "plain", "amber", "blade" }).length());
  }

  String minAbbreviation(String target, String[] dictionary) {
    return generateAbbreviations(target).stream().sorted((s1, s2) -> getLength(s1) - getLength(s2))
        .filter(s -> isUnique(s, dictionary)).findFirst().orElse("");
  }

  private boolean isUnique(String s, String[] dictionary) {
    return Arrays.stream(dictionary).noneMatch(word -> validWordAbbreviation(word, s));
  }

  private int getLength(String s) {
    int length = 0;
    for (int i = 0; i < s.length(); i++)
      if (!isDigit(s.charAt(i)) || i == 0 || !isDigit(s.charAt(i - 1))) length++;
    return length;
  }

}
