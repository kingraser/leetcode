package leetcode;

import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class ValidSquare {

  /*
  Given the coordinates of four points in 2D space, 
  return whether the four points could construct a square.
  
  The coordinate (x,y) of a point is represented by an integer array with two integers.
  
  Example:  
  Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
  Output: True
  
  Note:  
    All the input integers are in the range [-10000, 10000].
    A valid square has four equal sides with positive length and four equal angles (90-degree angles).
    Input points have no order.  
  */

  @Test
  public void test() {
    assertTrue(validSquare(new int[] { 0, 0 }, new int[] { 1, 1 }, new int[] { 1, 0 }, new int[] { 0, 1 }));
  }

  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    Set<Integer> set = Stream.of(getDistance(p1, p2), getDistance(p1, p3), getDistance(p1, p4), getDistance(p2, p3),
        getDistance(p2, p4), getDistance(p3, p4)).collect(Collectors.toSet());
    return !set.contains(0) && set.size() == 2;
  }

  private int getDistance(int[] p1, int[] p2) {
    return (int) (Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
  }
}
