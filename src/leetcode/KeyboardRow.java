package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class KeyboardRow {

  /*
  Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard.
  
  Example 1:  
  Input: ["Hello", "Alaska", "Dad", "Peace"]
  Output: ["Alaska", "Dad"]
   
  Note:  
    You may use one character in the keyboard more than once.
    You may assume the input string will only contain letters of alphabet. 
  */

  private static final Map<Character, Integer> KEY_BOARD_MAP = new HashMap<>();

  static {
    "~`!1@2#3$4%5^6&7*8(9)0_-+=".chars().forEach(c -> KEY_BOARD_MAP.put((char) c, 0));
    "qwertyuiopQWERTYUIOP{}[]|\\".chars().forEach(c -> KEY_BOARD_MAP.put((char) c, 1));
    "asdfghjklASDFGHJKL:;'\"".chars().forEach(c -> KEY_BOARD_MAP.put((char) c, 2));
    "zxcvbnmZXCVBNM,./<>?".chars().forEach(c -> KEY_BOARD_MAP.put((char) c, 3));
  }

  @Test
  public void test() {
    assertArrayEquals(new String[] { "Alaska", "Dad" }, findWords(new String[] { "Hello", "Alaska", "Dad", "Peace" }));
  }

  public String[] findWords(String[] words) {
    return Arrays.stream(words).filter(word -> inOneRow(word)).toArray(size -> new String[size]);
  }

  private boolean inOneRow(String word) {
    if (word.length() < 2) return true;
    for (int i = 1, base = KEY_BOARD_MAP.get(word.charAt(0)); i < word.length(); i++)
      if (KEY_BOARD_MAP.get(word.charAt(i)).intValue() != base) return false;
    return true;
  }

}
