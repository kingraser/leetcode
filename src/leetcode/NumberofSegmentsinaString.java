package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberofSegmentsinaString {

  /*
  Count the number of segments in a string, 
  where a segment is defined to be a contiguous sequence of non-space characters.  
  Please note that the string does not contain any non-printable characters.  
  Example:  
  Input: "Hello, my name is John"
  Output: 5  
  */

  @Test
  public void test() {
    assertEquals(5, countSegments("Hello, my name is John"));
  }

  public int countSegments(String s) {
    int result = 0, idx = 0;
    while (idx < s.length()) {
      if (s.charAt(idx++) != ' ') result++;
      while (idx < s.length() && s.charAt(idx++) != ' ');
    }
    return result;
  }

}
