package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JumpGame {

  /*
  Given an array of non-negative integers, you are initially positioned at the first index of the array.    
  Each element in the array represents your maximum jump length at that position.    
  Determine if you are able to reach the last index.
  
  For example:
  A = [2,3,1,1,4], return true.    
  A = [3,2,1,0,4], return false. 
  */

  @Test
  public void test() {
    assertTrue(canJump(new int[] { 2, 3, 1, 1, 4 }));
    assertFalse(canJump(new int[] { 3, 2, 1, 0, 4 }));
  }

  public boolean canJump(int A[]) {
    int reach = 1;
    for (int i = 0; i < reach && reach < A.length; reach = Math.max(reach, A[i] + ++i));
    return reach >= A.length;
  }

}
