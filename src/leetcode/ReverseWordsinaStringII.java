package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ReverseWordsinaStringII {

  /*
  Given an input string, reverse the string word by word. 
  A word is defined as a sequence of non-space characters.  
  The input string does not contain leading or trailing spaces and the words are always separated by a single space.
  
  For example,
  Given s = "the sky is blue",
  return "blue is sky the".
  
  Could you do it in-place without allocating extra space?
  */

  @Test
  public void test() {
    char[] input = "the sky is blue".toCharArray();
    reverseWords(input);
    assertArrayEquals("blue is sky the".toCharArray(), input);
  }

  public void reverseWords(char[] s) {
    ReverseWordsinaString.reverseWords(s);
  }

}
