package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EliminationGame {

  /*
  There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.  
  Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.  
  We keep repeating the steps again, alternating left to right and right to left, until a single number remains.  
  Find the last number that remains starting with a list of length n.  
  Example:  
  Input:
  n = 9,
  1 2 3 4 5 6 7 8 9
  2 4 6 8
  2 6
  6  
  Output:
  6
  */

  /*
  At the first time, odds are deleted, lefts are even
  If for every number we divide 2, we get a new array from 1 to n/2
  Next time we delete from right to left, the result should be mirror value of lastRemaining(n/2) in 1 ... n/2
  What is mirror value, for example 1, 2, 3, 4
  mirror(2)=3, mirror(1)=4
  */

  public int lastRemaining(int n) {
    return n == 1 ? 1 : (1 + (n >>= 1) - lastRemaining(n)) << 1;
  }

  @Test
  public void test() {
    assertEquals(6, lastRemaining(9));
  }
}
