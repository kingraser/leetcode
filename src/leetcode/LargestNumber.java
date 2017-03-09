package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Test;

public class LargestNumber {

  /* 
  Given a list of non negative integers, arrange them such that they form the largest number.    
  For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.    
  Note: The result may be very large, so you need to return a string instead of an integer.
  */

  public String largestNumber(int[] num) {
    return String.join("", Arrays.stream(num).boxed().map(i -> i.toString())
        .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2)).collect(Collectors.toList()));
  }

  @Test
  public void test() {
    assertEquals("9534330", largestNumber(new int[] { 3, 30, 34, 5, 9 }));
  }

}
