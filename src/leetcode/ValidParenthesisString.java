package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ValidParenthesisString {
  /*
  Given a string containing only three types of characters: '(', ')' and '*', 
  write a function to check whether this string is valid. 
  We define the validity of a string by these rules:  
    Any left parenthesis '(' must have a corresponding right parenthesis ')'.
    Any right parenthesis ')' must have a corresponding left parenthesis '('.
    Left parenthesis '(' must go before the corresponding right parenthesis ')'.
    '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
    An empty string is also valid.
  
  Example 1:  
  Input: "()"
  Output: True
  
  Example 2:  
  Input: "(*)"
  Output: True
  
  Example 3:
  
  Input: "(*))"
  Output: True
  
  Note:  
    The string size will be in the range [1, 100].  
  */

  @Test
  public void test() {
    assertTrue(checkValidString("()"));
    assertTrue(checkValidString("(*)"));
    assertTrue(checkValidString("(*))"));
  }

  public boolean checkValidString(String s) {
    int leftWithoutStar = 0, leftWithStar = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        leftWithoutStar++;
        leftWithStar++;
      } else if (s.charAt(i) == ')') {
        if (leftWithoutStar > 0) leftWithoutStar--;
        leftWithStar--;
      } else {
        if (leftWithoutStar > 0) leftWithoutStar--;
        leftWithStar++;
      }
      if (leftWithStar < 0) return false;
    }
    return leftWithoutStar == 0;
  }

}
