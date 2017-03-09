package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BulbSwitcher {

  /*
  There are n bulbs that are initially off. You first turn on all the bulbs. 
  Then, you turn off every second bulb. 
  On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). 
  For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
  
  Example:
  
  Given n = 3. 
  
  At first, the three bulbs are [off, off, off].
  After first round, the three bulbs are [on, on, on].
  After second round, the three bulbs are [on, off, on].
  After third round, the three bulbs are [on, off, off]. 
  
  So you should return 1, because there is only one bulb is on.
  */

  /*
  We know that toggle represents the bulb is times of some integer.
  For bulb one: 1*1 toggle once, off -> on
  For bulb two: 1*2, 2*1 toggle twice, off -> on -> off
  For bulb three: 1*3, 3*1 toggle twice, off -> on -> off
  For bulb four: 1*4, 2*2, 4*1 toggle 3 times, off -> on -> off -> on
  
  We can find that every time you found some times of a integer, there is a symmetrical one. 
  For example 1*2, there will be a 2*1.
  But a square is a exception, for example 2*2 occurs only once.
  Which means the on bulbs are at square number position.
  */

  @Test
  public void test() {
    assertEquals(1, bulbSwitch(3));
  }

  public int bulbSwitch(int n) {
    return (int) Math.sqrt(n);
  }

}
