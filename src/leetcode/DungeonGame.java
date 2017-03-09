package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DungeonGame {
  /*
  The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. 
  The dungeon consists of M x N rooms laid out in a 2D grid. 
  Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
  The knight has an initial health point represented by a positive integer. 
  If at any point his health point drops to 0 or below, he dies immediately.    
  Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; 
  other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).    
  In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.    
  Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.    
  For example, given the dungeon below, 
  the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
  -2 (K)      -3          3
  -5          -10         1
  10          30          -5 (P)
  
  Notes:    
  The knight's health has no upper bound.
  Any room can contain threats or power-ups, 
  even the first room the knight enters and the bottom-right room where the princess is imprisoned.
  */

  /*
  Let dp[i][j] represents the minimum value from i,j to bottom-right
  dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
  */

  @Test
  public void test() {
    assertEquals(7, calculateMinimumHP(new int[][] { { -2, -3, 3 }, { -5, -10, 1 }, { 10, 30, -5 } }));
  }

  public int calculateMinimumHP(int[][] dungeon) {
    int width = dungeon[0].length, height = dungeon.length;
    int[][] dp = new int[height][width];
    dp[height - 1][width - 1] = Math.max(1, 1 - dungeon[height - 1][width - 1]);
    for (int i = height - 2; i > -1; i--)
      dp[i][width - 1] = Math.max(1, dp[i + 1][width - 1] - dungeon[i][width - 1]);
    for (int i = width - 2; i > -1; i--)
      dp[height - 1][i] = Math.max(1, dp[height - 1][i + 1] - dungeon[height - 1][i]);
    for (int i = height - 2; i > -1; i--)
      for (int j = width - 2; j > -1; j--)
        dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
    return dp[0][0];
  }
}
