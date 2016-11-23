/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年10月18日;
//-------------------------------------------------------
public class FizzBuzz {

  /*
  Write a program that outputs the string representation of numbers from 1 to n.  
  But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. 
  For numbers which are multiples of both three and five output “FizzBuzz”.
  
  Example:
  
  n = 15,
  
  Return:
  [
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
  ]
  */

  @Test
  public void test() {
    Assert.assertEquals(Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz",
        "13", "14", "FizzBuzz"), fizzBuzz(15));
  }

  public List<String> fizzBuzz(int n) {
    List<String> result = new ArrayList<>(n);
    for (int i = 1; i <= n; result.add(get(i++)));
    return result;
  }

  private String get(int i) {
    String result = String.join("", 0 == i % 3 ? "Fizz" : "", 0 == i % 5 ? "Buzz" : "");
    return result.length() > 0 ? result : Integer.toString(i);
  }

}
