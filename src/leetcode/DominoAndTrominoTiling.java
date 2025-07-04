package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class DominoAndTrominoTiling {
    /*
    You have two types of tiles: a 2 x 1 domino shape and a tromino shape.
    You may rotate these shapes.
    Given an integer n, return the number of ways to tile an 2 x n board.
    Since the answer may be very large, return it modulo 10^9 + 7.
    In a tiling, every square must be covered by a tile.
    Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

    Example 1:
    Input: n = 3
    Output: 5
    Explanation: The five different ways are shown above.

    Example 2:
    Input: n = 1
    Output: 1

    Constraints:
        1 <= n <= 1000
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, 1}, {5, 3}, {11, 4}, {312342182, 30}
        });
    }

    /*
    dp[0]=1
    dp[1]=1
    dp[2]=2
    dp[3]=5

    dp[4]=dp[3]+dp[2]+2*(dp[1]+dp[0])
    dp[n]=dp[n-1]+dp[n-2]+2*(dp[n-3]+dp[n-4]+...+dp[0])
    dp[n]=dp[n-1]+dp[n-2]+dp[n-3]+dp[n-3]+2*(dp[n-4]+...+dp[0])
    dp[n]=dp[n-1]+dp[n-3]+{dp[n-2]+dp[n-3]+2*(dp[n-4]+...+dp[0])}
    dp[n]=dp[n-1]+dp[n-3]+dp[n-1]
    dp[n]=(dp[n-1] * 2) + dp[n-3]
    */
    public int numTilings(int n) {
        if (n < 3) return n;
        long[] dp = new long[n + 1];
        dp[1] = dp[0] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) dp[i] = ((dp[i - 1] << 1) + dp[i - 3]) % 1_000_000_007;
        return (int) dp[n];
    }
}
