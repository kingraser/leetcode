package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JudgeRouteCircle {

  /*
  Initially, there is a Robot at position (0, 0). 
  Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.
  
  The move sequence is represented by a string. And each move is represent by a character. 
  The valid robot moves are R (Right), L (Left), U (Up) and D (down). 
  The output should be true or false representing whether the robot makes a circle.
  
  Example 1:  
  Input: "UD"
  Output: true
  
  Example 2:  
  Input: "LL"
  Output: false 
  */

  @Test
  public void test() {
    assertTrue(judgeCircle("UD"));
    assertFalse(judgeCircle("LL"));
  }

  public boolean judgeCircle(String moves) {
    int[] x = new int[1], y = new int[1];
    moves.chars().forEach(c -> move(c, x, y));
    return x[0] == 0 && y[0] == 0;
  }

  public static void move(int move, int[] x, int[] y) {
    if (move == 'U') y[0]++;
    else if (move == 'D') y[0]--;
    else if (move == 'L') x[0]--;
    else x[0]++;
  }

}
