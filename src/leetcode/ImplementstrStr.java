package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ImplementstrStr {

  /*
  Implement strStr().  
  Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack. 
  */

  @Test
  public void test() {
    assertEquals(-1, strStr("mississippi", "sippia"));
    assertEquals(6, strStr("mississippia", "sippia"));
  }

  public int strStr(String haystack, String needle) {
    if (haystack != null && needle != null) A: for (int hIdx = 0; hIdx <= haystack.length() - needle.length(); hIdx++) {
      for (int nIdx = 0; nIdx < needle.length(); nIdx++)
        if (haystack.charAt(hIdx + nIdx) != needle.charAt(nIdx)) continue A;
      return hIdx;
    }
    return -1;
  }

}
