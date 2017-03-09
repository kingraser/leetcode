package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SingleNumberII {

  /*    
  Given an array of integers, every element appears three times except for one. Find that single one.  
  Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory? 
  */

  @Test
  public void test() {
    assertEquals(2, singleNumber(new int[] { 1, 1, 1, 2, 3, 3, 3 }));
  }

  public int singleNumber(int[] A) {
    int ones = 0, twos = 0;
    for (int i = 0; i < A.length; i++) {
      ones = (ones ^ A[i]) & ~twos;
      twos = (twos ^ A[i]) & ~ones;
    }
    return ones;
  }

}
