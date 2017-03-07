package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LengthofLastWord {

  /*
  Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
  return the length of last word in the string.    
  If the last word does not exist, return 0.    
  Note: A word is defined as a character sequence consists of non-space characters only.
  
  For example,
  Given s = "Hello World",
  return 5. 
  */
  public int lengthOfLastWord(String s) {
    int len = s.length() - 1, lastLength = 0;
    for (; len > -1 && s.charAt(len) == ' '; len--);
    for (; len > -1 && s.charAt(len) != ' '; lastLength++, len--);
    return lastLength;
  }

  @Test
  public void test() {
    String s = "hello world";
    assertEquals(5, lengthOfLastWord(s));
  }

}
