package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

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

  @Test
  public void test() {
    assertEquals(Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"), letterCombinations("23"));
  }

  String[] map = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

  public List<String> letterCombinations(String s) {
    if (s == null || s.length() == 0) return new ArrayList<>();
    char[][] result = new char[getCombinationCounts(s)][s.length()];
    for (int sIdx = 0, combinationCounts = result.length, row; sIdx < s.length(); sIdx++)
      for (combinationCounts /= map[digit(s.charAt(sIdx))].length(), row = 0; row < result.length;)
        for (int digitIdx = 0; digitIdx < map[digit(s.charAt(sIdx))].length(); digitIdx++)
          for (int i = 0; i < combinationCounts; i++)
            result[row++][sIdx] = map[digit(s.charAt(sIdx))].charAt(digitIdx);
    return Arrays.stream(result).map(a -> new String(a)).collect(Collectors.toList());
  }

  private int getCombinationCounts(String s) {
    int result = 1;
    for (char c : s.toCharArray())
      result *= map[digit(c)].length();
    return result;
  }

  private int digit(char c) {
    return c - '0';
  }

}
