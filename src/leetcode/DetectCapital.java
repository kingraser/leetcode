package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DetectCapital {

  /*
  Given a word, you need to judge whether the usage of capitals in it is right or not.  
  We define the usage of capitals in a word to be right when one of the following cases holds:  
    All letters in this word are capitals, like "USA".
    All letters in this word are not capitals, like "leetcode".
    Only the first letter in this word is capital if it has more than one letter, like "Google".  
  Otherwise, we define that this word doesn't use capitals in a right way.
  
  Example 1:  
  Input: "USA"
  Output: True
  
  Example 2:  
  Input: "FlaG"
  Output: False
  
  Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters. 
  */

  @Test
  public void test() {
    assertTrue(detectCapitalUse("USA"));
    assertFalse(detectCapitalUse("FlaG"));
  }

  public boolean detectCapitalUseZero(String word) {
    return word.matches("[A-Z]+|[a-z]+|[A-Z][a-z]+");
  }

  public boolean detectCapitalUse(String word) {
    return word.chars().skip(1).allMatch(c -> isLowerCase(c)) || word.chars().noneMatch(c -> isLowerCase(c));
  }

  private boolean isLowerCase(int c) {
    return c <= 'z' && c >= 'a';
  }
}
