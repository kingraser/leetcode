/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年8月31日;
//-------------------------------------------------------
public class GuessNumberHigherorLower {

  /*
  We are playing the Guess Game. The game is as follows:  
  I pick a number from 1 to n. You have to guess which number I picked.  
  Every time you guess wrong, I'll tell you whether the number is higher or lower.  
  You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
  
  -1 : My number is lower
   1 : My number is higher
   0 : Congrats! You got it!
  
  Example:  
  n = 10, I pick 6.  
  Return 6.  
  */

  @Test
  public void test() {
    GuessGame game = new GuessGame(10);
    assertEquals(game.num, game.guessNumber(game.size));
  }

  public class GuessGame {
    public int num, size;

    public GuessGame(int size) {
      this.size = size;
      this.num = new Random().nextInt(this.size) + 1;
    }

    public int guessNumber(int n) {
      for (int left = 1, mid, res;;)
        if ((res = Integer.compare(num, mid = left + ((n - left) >> 1))) == 0) return mid;
        else if (res > 0) left = mid + 1;
        else n = mid;
    }
  }
}
