package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class StoneRemovalGame {
    /*
    Alice and Bob are playing a game where they take turns removing stones from a pile, with Alice going first.
        Alice starts by removing exactly 10 stones on her first turn.
        For each subsequent turn, each player removes exactly 1 fewer stone than the previous opponent.
    The player who cannot make a move loses the game.
    Given a positive integer n, return true if Alice wins the game and false otherwise.

    Example 1:
    Input: n = 12
    Output: true
    Explanation:
        Alice removes 10 stones on her first turn, leaving 2 stones for Bob.
        Bob cannot remove 9 stones, so Alice wins.

    Example 2:
    Input: n = 1
    Output: false
    Explanation:
        Alice cannot remove 10 stones, so Alice loses.

    Constraints:
        1 <= n <= 50
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, 12},
                {false, 1}
        });
    }

    int startValue = 10;

    public boolean canAliceWin(int n) {
        int countOfTurns = (int) (startValue + (1 - Math.sqrt(pow2(1 + (startValue << 1)) - (n << 3))) / 2);
        return (countOfTurns & 1) == 1;
    }

    int pow2(int a) {
        return a * a;
    }
}
