package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import leetcode.util.MathUtil;

public class SuperWashingMachines {
  /*
  You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.  
  For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .
  Given an integer array representing the number of dresses in each washing machine from left to right on the line, 
  you should find the minimum number of moves to make all the washing machines have the same number of dresses. 
  If it is not possible to do it, return -1.
  
  Example1  
  Input: [1,0,5]  
  Output: 3  
  Explanation: 
  1st move:    1     0 <-- 5    =>    1     1     4
  2nd move:    1 <-- 1 <-- 4    =>    2     1     3    
  3rd move:    2     1 <-- 3    =>    2     2     2   
  
  Example2  
  Input: [0,3,0]  
  Output: 2  
  Explanation: 
  1st move:    0 <-- 3     0    =>    1     2     0    
  2nd move:    1     2 --> 0    =>    1     1     1     
  
  Example3  
  Input: [0,2,0]  
  Output: -1  
  Explanation: 
  It's impossible to make all the three washing machines have the same number of dresses. 
  
  Note:  
    The range of n is [1, 10000].
    The range of dresses number in a super washing machine is [0, 1e5].
  */

  @Test
  public void test() {
    assertEquals(8, findMinMoves(new int[] { 0, 0, 11, 5 }));
  }

  /*
  For [0,0,11,5], we can easily find the average is 4.
  So we can get the lose array [-4,-4,7,1].
  The sum of lose array is [-4,-8,-1,0], so the peak of lose sum is 8.
  And the peak of lose is 7, so the answer is 8. 
  */
  public int findMinMoves(int[] machines) {
    int sum = Arrays.stream(machines).sum();
    if (sum % machines.length != 0) return -1;
    int avg = sum / machines.length, lose = 0, max = 0;
    for (int load : machines)
      max = MathUtil.max(max, Math.abs(lose += load - avg), load - avg);
    return max;
  }

}
