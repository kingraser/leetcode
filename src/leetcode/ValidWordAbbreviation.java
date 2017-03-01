package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidWordAbbreviation {

  /*
  Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.  
  A string such as "word" contains only the following valid abbreviations:  
  ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]  
  Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".
  
  Note:
  Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.  
  Example 1:  
  Given s = "internationalization", abbr = "i12iz4n":  
  Return true.  
  Example 2:  
  Given s = "apple", abbr = "a2e":  
  Return false.
  */

  @Test
  public void test() {
    assertTrue(validWordAbbreviation("internationalization", "i12iz4n"));
    assertFalse(validWordAbbreviation("apple", "a2e"));
  }

  public boolean validWordAbbreviation(String word, String abbr) {
    int wordLength = word.length(), abbrLength = abbr.length(), wordIdx = 0, abbrIdx = 0;
    for (int abbrChar, num; wordIdx < wordLength && abbrIdx < abbrLength;)
      if ((abbrChar = abbr.charAt(abbrIdx++)) > '0' && abbrChar <= '9') {
        for (num = abbrChar - '0'; abbrIdx < abbrLength && (abbrChar = abbr.charAt(abbrIdx)) >= '0'
            && abbrChar <= '9'; num = num * 10 + abbrChar - '0', abbrIdx++);
        wordIdx += num;
      } else if (abbrChar != word.charAt(wordIdx++)) return false;
    return wordIdx == wordLength && abbrIdx == abbrLength;
  }

}
