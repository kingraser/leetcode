package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidNumber {

  /*
  Validate if a given string is numeric.
  
  Some examples:
  "0" => true
  " 0.1 " => true
  "abc" => false
  "1 a" => false
  "2e10" => true
  
  Note: It is intended for the problem statement to be ambiguous. 
  You should gather all requirements up front before implementing one. 
  */

  public boolean isNumber(String s) {
    return s.trim().matches("^[+-]?((\\d+(\\.\\d*)?)|(\\.\\d+))([Ee][+-]?\\d+)?$");
  }

  @Test
  public void test() {
    assertTrue(isNumber("0"));
    assertTrue(isNumber(" 0.1 "));
    assertTrue(isNumber("+2.e10"));
    assertTrue(isNumber("+.2e10"));
    assertFalse(isNumber("abc"));
    assertFalse(isNumber("1 a"));
  }

}
