package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RepeatedStringMatch {

  /*
  Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. 
  If no such solution, return -1.
  
  For example, with A = "abcd" and B = "cdabcdab".
  
  Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; 
  and B is not a substring of A repeated two times ("abcdabcd").
  
  Note: The length of A and B will be between 1 and 10000. 
  */

  @Test
  public void test() {
    assertEquals(3, repeatedStringMatch("abcd", "cdabcdab"));
  }

  public int repeatedStringMatch(String A, String B) {
    int q = 1;
    StringBuilder s = new StringBuilder(A);
    for (; s.length() < B.length(); s.append(A), q++);
    return s.indexOf(B) >= 0 ? q : s.append(A).indexOf(B) >= 0 ? q + 1 : -1;
  }
}
