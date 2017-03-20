package leetcode;

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import org.junit.Test;

public class CanIWin {

  /*
  In the "100 game," two players take turns adding, to a running total, any integer from 1..10. 
  The player who first causes the running total to reach or exceed 100 wins.  
  What if we change the game so that players cannot re-use integers?  
  For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.  
  Given an integer maxChoosableInteger and another integer desiredTotal, 
  determine if the first player to move can force a win, assuming both players play optimally.  
  You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
  
  Example  
  Input: maxChoosableInteger = 10 desiredTotal = 11  
  Output: false
  
  Explanation:
  No matter which integer the first player choose, the first player will lose.
  The first player can choose an integer from 1 up to 10.
  If the first player choose 1, the second player can only choose integers from 2 up to 10.
  The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
  Same with other integers chosen by the first player, the second player will always win.  
  */

  @Test
  public void test() {
    assertFalse(canIWin(10, 11));
  }

  public boolean canIWin(int max, int sum) {
    return sum <= 0 || (((max * max + max) >> 1) >= sum && canIWin(max, sum, 0, new HashMap<>()));
  }

  private boolean canIWin(int max, int sum, int state, Map<Integer, Boolean> map) {
    return map.compute(state, (k, v) -> Objects.isNull(v) ? IntStream.range(0, max).filter(i -> (state & (1 << i)) == 0)
        .anyMatch(i -> (sum <= i + 1 || !canIWin(max, sum - (i + 1), (1 << i) | state, map))) : v);
  }
}
