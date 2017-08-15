package leetcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DesignCompressedStringIterator {

  /*
  Design and implement a data structure for a compressed string iterator. 
  It should support the following operations: next and hasNext.  
  The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.
  
  next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
  hasNext() - Judge whether there is any letter needs to be uncompressed.
  
  Note:
  Please remember to RESET your class variables declared in StringIterator, 
  as static/class variables are persisted across multiple test cases. 
  Please see here for more details.
    
  Example:  
  StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");  
  iterator.next(); // return 'L'
  iterator.next(); // return 'e'
  iterator.next(); // return 'e'
  iterator.next(); // return 't'
  iterator.next(); // return 'C'
  iterator.next(); // return 'o'
  iterator.next(); // return 'd'
  iterator.hasNext(); // return true
  iterator.next(); // return 'e'
  iterator.hasNext(); // return false
  iterator.next(); // return ' '
  */

  @Test
  public void test() {
    StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
    assertEquals('L', iterator.next());
    assertEquals('e', iterator.next());
    assertEquals('e', iterator.next());
    assertEquals('t', iterator.next());
    assertEquals('C', iterator.next());
    assertEquals('o', iterator.next());
    assertEquals('d', iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals('e', iterator.next());
    assertFalse(iterator.hasNext());
    assertEquals(' ', iterator.next());
  }

  class StringIterator {
    int idx = 0, val = 0;
    char digit, c;
    String s;

    public StringIterator(String s) {
      this.s = s;
    }

    public char next() {
      if (val == 0) {
        if (idx < s.length()) for (c = s.charAt(idx++); idx < s.length()
            && isDigit(digit = s.charAt(idx)); val = val * 10 + digit - '0', idx++);
        else return ' ';
      }
      val--;
      return c;
    }

    private boolean isDigit(char c) {
      return c >= '0' && c <= '9';
    }

    public boolean hasNext() {
      return val > 0 || idx < s.length();
    }
  }

}
