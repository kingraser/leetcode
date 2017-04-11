package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ZumaGame {

  /*
  Think about Zuma Game. 
  You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). 
  You also have several balls in your hand.  
  Each time, you may choose a ball in your hand, 
  and insert it into the row (including the leftmost place and rightmost place). 
  Then, if there is a group of 3 or more balls in the same color touching, remove these balls. 
  Keep doing this until no more balls can be removed.  
  Find the minimal balls you have to insert to remove all the balls on the table. 
  If you cannot remove all the balls, output -1.
  
  Examples:  
  Input: "WRRBBW", "RB"
  Output: -1
  Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
  
  Input: "WWRRBBWW", "WRBRW"
  Output: 2
  Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
  
  Input:"G", "GGGGG"
  Output: 2
  Explanation: G -> G[G] -> GG[G] -> empty 
  
  Input: "RBYYBBRRB", "YRBGB"
  Output: 3
  Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty 
  
  Note:  
    You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
    The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
    The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
    Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'. 
  */

  @Test
  public void test() {
    assertEquals(2, findMinStep("WWRRBBWW", "WRBRW"));
    assertEquals(-1, findMinStep("WRRBBW", "RB"));
    assertEquals(2, findMinStep("G", "GGGGG"));
    assertEquals(3, findMinStep("RBYYBBRRB", "YRBGB"));
  }

  public int findMinStep(String board, String hand) {
    int[] map = new int[128];
    hand.chars().forEach(c -> map[c]++);
    return dfs(board, map);
  }

  private int dfs(String s, int[] hand) {
    int result = Integer.MAX_VALUE;
    for (int i = 0, count = 1, min, need; i < s.length(); hand[s.charAt(i)] += need, i += count, count = 1) {
      if (i + 1 < s.length() && s.charAt(i + 1) == s.charAt(i)) count++;
      if ((hand[s.charAt(i)] -= (need = 3 - count)) >= 0) result = Math.min(result,
          (min = dfs(remove(s.substring(0, i) + s.substring(i + count)), hand)) > -1 ? min + need : result);
    }
    return s.length() == 0 ? 0 : result == Integer.MAX_VALUE ? -1 : result;
  }

  private String remove(String s) {
    for (int start = 0, end; start < s.length(); start = end) {
      for (end = start + 1; end < s.length() && s.charAt(start) == s.charAt(end); end++);
      if (end - start > 2) return remove(s.substring(0, start) + s.substring(end));
    }
    return s;
  }
}
