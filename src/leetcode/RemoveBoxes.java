package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RemoveBoxes {

  /*
  Given several boxes with different colors represented by different positive numbers.
  You may experience several rounds to remove boxes until there is no box left. 
  Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
  Find the maximum points you can get.
  
  Example 1:
  Input: [1, 3, 2, 2, 2, 3, 4, 3, 1]
  Output: 23  
  Explanation:  
  [1, 3, 2, 2, 2, 3, 4, 3, 1] 
  ----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
  ----> [1, 3, 3, 3, 1] (1*1=1 points) 
  ----> [1, 1] (3*3=9 points) 
  ----> [] (2*2=4 points)  
  
  Note: The number of boxes n would not exceed 100. 
  */

  @Test
  public void test() {
    assertEquals(23, removeBoxes(new int[] { 1, 3, 2, 2, 2, 3, 4, 3, 1 }));
  }

  public int removeBoxes(int[] boxes) {
    return dfs(boxes, new int[boxes.length][boxes.length][boxes.length], 0, boxes.length - 1, 0);
  }

  // memo[l][r][k] is the largest number [l, r] with k same colored boxes as r appended at the end. 
  // memo[l][r][k] = max(memo[l][r][k], memo[l][i][k+1] + memo[i+1][r-1][0]) (l <= i < r)
  int dfs(int[] boxes, int memo[][][], int l, int r, int k) {
    if (l > r) return 0;
    if (memo[l][r][k] != 0) return memo[l][r][k];
    memo[l][r][k] = dfs(boxes, memo, l, r - 1, 0) + (k + 1) * (k + 1);
    for (int i = l; i < r; i++)
      if (boxes[i] == boxes[r])
        memo[l][r][k] = Math.max(memo[l][r][k], dfs(boxes, memo, l, i, k + 1) + dfs(boxes, memo, i + 1, r - 1, 0));
    return memo[l][r][k];
  }

}
