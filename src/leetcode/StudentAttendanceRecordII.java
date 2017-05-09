package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StudentAttendanceRecordII {

    /*
    Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable.
     The answer may be very large, return it after mod 109 + 7.

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
    }

    private final int MOD = 10_0000_0007;

    public int checkRecordZero(int n) {
        int[][][] dp = new int[n + 1][2][3]; // dp[i][j][k] is count of records with i length, at most j A, at most k trailing L
        dp[0] = new int[][] {{1, 1, 1}, {1, 1, 1}};
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = ((dp[i - 1][j][2] + (j > 0 ? dp[i - 1][j - 1][2] : 0)) % MOD + (k > 0 ? dp[i - 1][j][k - 1] : 0)) % MOD;
                }
            }
        }
        return dp[n][1][2];
    }

    int[][] mul(int[][] A, int[][] B) {
        int[][] C = new int[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) { C[i][j] = (int) ((C[i][j] + (long) A[i][k] * B[k][j]) % MOD); }
            }
        }
        return C;
    }

    int[][] pow(int[][] A, int n) {
        int[][] res = new int[6][6];
        for (int i = 0; i < 6; i++) { res[i][i] = 1; }
        for (; n > 0; A = mul(A, A), n >>= 1) { if ((n & 1) == 1) { res = mul(res, A); } }
        return res;
    }

    public int checkRecord(int n) {
        int[][] A = {
                {0, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 1},
                {0, 0, 1, 1, 0, 1},
                {0, 0, 1, 0, 1, 1},
        };
        return pow(A, n + 1)[5][2];
    }
}