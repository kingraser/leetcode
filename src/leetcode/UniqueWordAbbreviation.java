package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class UniqueWordAbbreviation {

  /*
  An abbreviation of a word follows the form <first letter><number><last letter>. 
  Below are some examples of word abbreviations:  
  a) it --> it    (no abbreviation)
  b) d|o|g --> d1g
  c) i|nternationalizatio|n --> i18n
  d) l|ocalizatio|n --> l10n  
  Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. 
  A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
  Example: Given dictionary = [ "deer", "door", "cake", "card" ]  
  isUnique("dear") -> false
  isUnique("cart") -> true
  isUnique("cane") -> false
  isUnique("make") -> true
  */

  @Test
  public void test() {
    ValidWordAbbr validWordAbbr = new ValidWordAbbr(new String[] { "deer", "door", "cake", "card" });
    assertFalse(validWordAbbr.isUnique("dear"));
    assertTrue(validWordAbbr.isUnique("cart"));
    assertFalse(validWordAbbr.isUnique("cane"));
    assertTrue(validWordAbbr.isUnique("make"));
  }

  public class ValidWordAbbr {
    Map<String, String> map = new HashMap<String, String>();

    public ValidWordAbbr(String[] dictionary) {
      Arrays.stream(dictionary)
          .forEach(s -> map.compute(getAbbreviation(s), (k, v) -> v == null || v.equals(s) ? s : ""));
    }

    public boolean isUnique(String word) {
      return map.getOrDefault(getAbbreviation(word), word).equals(word);
    }

    private String getAbbreviation(String s) {
      return s.length() < 3 ? s : s.charAt(0) + Integer.toString(s.length() - 2) + s.charAt(s.length() - 1);
    }
  }
}
