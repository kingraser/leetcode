package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.BitSet;

import org.junit.Test;

public class RemoveDuplicateLetters {

  /*
  Given a string which contains only lowercase letters, 
  remove duplicate letters so that every letter appear once and only once. 
  You must make sure your result is the smallest in lexicographical order among all possible results.
  
  Example:    
  Given "bcabc"
  Return "abc"
  
  Given "cbacdcbc"
  Return "acdb" 
  */

  public String removeDuplicateLetters(String s) {
    char[] result = s.toCharArray();
    int idx = -1, count[] = new int[128];
    BitSet isSelected = new BitSet();
    s.chars().forEach(c -> count[c]++);
    for (char c : result)
      if (isSelected.get(c)) count[c]--;
      else {
        for (char end; idx >= 0 && (end = result[idx]) >= c && count[end] > 0; idx--, isSelected.clear(end));
        result[++idx] = c;
        count[c]--;
        isSelected.set(c);
      }
    return new String(result, 0, ++idx);
  }

  @Test
  public void test() {
    assertEquals("acdb", removeDuplicateLetters("cbacdcbc"));
    assertEquals("abc", removeDuplicateLetters("bcabc"));
  }
}
