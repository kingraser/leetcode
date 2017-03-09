package leetcode;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SingleNumberIII {
  /*
  Given an array of numbers nums, 
  in which exactly two elements appear only once and all the other elements appear exactly twice. 
  Find the two elements that appear only once.
  
  For example:
  
  Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
  
  Note:
  
  The order of the result is not important. So in the above example, [5, 3] is also correct.
  Your algorithm should run in linear runtime complexity. 
  Could you implement it using only constant space complexity?
  */

  /*
  First we exclusive or all number, we get a^b (a, b are what we want to find).
  The 1 bit of result represents a and b differ at the bit.       
  Find the bit, we can split the numbers by whether the bit is 0 or 1.
  For every part the problem is transform to <leetcode.SingleNumber> 
  */

  @Test
  public void test() {
    assertArrayEquals(new int[] { 3, 5 }, singleNumber(new int[] { 1, 2, 1, 3, 2, 5 }));
  }

  public int[] singleNumber(int[] nums) {
    int aXORb = 0;
    for (int item : nums)
      aXORb ^= item;
    int last1Bit = aXORb & (-aXORb);
    int a = 0, b = 0;
    for (int item : nums)
      if ((item & last1Bit) != 0) a = a ^ item;
      else b = b ^ item;
    return new int[] { a, b };
  }
}
