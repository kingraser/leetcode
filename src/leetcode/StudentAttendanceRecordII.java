package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StudentAttendanceRecordII {

  /*
  Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable.
   The answer may be very large, return it after mod 10^9 + 7.
  
  A student attendance record is a string that only contains the following three characters:
  
      'A' : Absent.
      'L' : Late.
      'P' : Present.
  
  A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
  
  Example 1:
  Input: n = 2
  Output: 8
  Explanation:
  There are 8 records with length 2 will be regarded as rewardable:
  "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
  Only "AA" won't be regarded as rewardable owing to more than one absent times.
  
  Note: The value of n won't exceed 100,000.
  */

  @Test
  public void test() {
    assertEquals(8, checkRecord(2));
    assertEquals(8, checkRecordZero(2));
  }

  /*
  Let f[i][j][k] denote the # of valid sequences of length i where:
  
    There can be at most j A's in the entire sequence.
    There can be at most k trailing L's.
  
  We give the recurrence in the following code, which should be self-explanatory, and the final answer is f[n][1][2]
  */

  private int MOD = 10_0000_0007;

  public int checkRecordZero(int n) {
    int[][][] dp = new int[n + 1][2][3]; // dp[i][j][k] is count of records with i length, at most j A, at most k trailing L
    dp[0] = new int[][] { { 1, 1, 1 }, { 1, 1, 1 } };
    for (int i = 1; i <= n; i++)
      for (int j = 0; j < 2; j++)
        for (int k = 0; k < 3; k++)
          dp[i][j][k] = ((dp[i - 1][j][2] + (j > 0 ? dp[i - 1][j - 1][2] : 0)) % MOD
              + (k > 0 ? dp[i - 1][j][k - 1] : 0)) % MOD;
    return dp[n][1][2];
  }

  /*
  The runtime of this solution is clearly O(n), using linear space (which can be easily optimized to O(1) though). 
  Now, let's see how to further improve the runtime.
  
  In fact, if we treat f[i][][] and f[i-1][][] as two vectors, we can represent the recurrence of f[i][j][k] as follows:
  
  f[i][0][0]   | 0 0 1 0 0 0 |   f[i-1][0][0]
  f[i][0][1]   | 1 0 1 0 0 0 |   f[i-1][0][1]
  f[i][0][2] = | 0 1 1 0 0 0 | * f[i-1][0][2]
  f[i][1][0]   | 0 0 1 0 0 1 |   f[i-1][1][0]
  f[i][1][1]   | 0 0 1 1 0 1 |   f[i-1][1][1]
  f[i][1][2]   | 0 0 1 0 1 1 |   f[i-1][1][2]
  
  Let A be the matrix above, then f[n][][] = A^n * f[0][][], where f[0][][] = [1 1 1 1 1 1]. The point of this approach is that we can compute A^n using exponentiating by squaring (thanks to @StefanPochmann for the name correction), which will take O(6^3 * log n) = O(log n) time. Therefore, the runtime improves to O(log n), which suffices to handle the case for much larger n, say 10^18.
  Update: The final answer is f[n][1][2], which involves multiplying the last row of A^n and the column vector [1 1 1 1 1 1]. Interestingly, it is also equal to A^(n+1)[5][2] as the third column of A is just that vector. Credit to @StefanPochmann.
   */

  private int A[][] = { { 0, 0, 1, 0, 0, 0 }, { 1, 0, 1, 0, 0, 0 }, { 0, 1, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0, 1 },
      { 0, 0, 1, 1, 0, 1 }, { 0, 0, 1, 0, 1, 1 }, };

  private int[][] mul(int[][] A, int[][] B) {
    int[][] C = new int[6][6];
    for (int i = 0; i < 6; i++)
      for (int j = 0; j < 6; j++)
        for (int k = 0; k < 6; k++)
          C[i][j] = (int) ((C[i][j] + (long) A[i][k] * B[k][j]) % MOD);
    return C;
  }

  private int[][] pow(int[][] A, int n) {
    int[][] res = new int[6][6];
    for (int i = 0; i < 6; res[i][i++] = 1);
    for (; n > 0; A = mul(A, A), n >>= 1)
      if ((n & 1) == 1) res = mul(res, A);
    return res;
  }

  public int checkRecord(int n) {
    return pow(A, n + 1)[5][2];
  }
}