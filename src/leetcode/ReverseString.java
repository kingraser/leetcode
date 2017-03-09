package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseString {

  /*
  Write a function that takes a string as input and returns the string reversed.
  Example: Given s = "hello", return "olleh". 
  */

  @Test
  public void test() {
    assertEquals("olleh", reverseString("hello"));
  }

  public String reverseString(String s) {
    return new StringBuilder(s).reverse().toString();
  }

}
