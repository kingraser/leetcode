package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class CheckIfDigitsAreEqualInStringAfterOperationsII {
    /*
    You are given a string s consisting of digits. Perform the following operation repeatedly until the string has exactly two digits:
        For each pair of consecutive digits in s, starting from the first digit, calculate a new digit as the sum of the two digits modulo 10.
        Replace s with the sequence of newly calculated digits, maintaining the order in which they are computed.
    Return true if the final two digits in s are the same; otherwise, return false.

    Example 1:
    Input: s = "3902"
    Output: true
    Explanation:
        Initially, s = "3902"
        First operation:
            (s[0] + s[1]) % 10 = (3 + 9) % 10 = 2
            (s[1] + s[2]) % 10 = (9 + 0) % 10 = 9
            (s[2] + s[3]) % 10 = (0 + 2) % 10 = 2
            s becomes "292"
        Second operation:
            (s[0] + s[1]) % 10 = (2 + 9) % 10 = 1
            (s[1] + s[2]) % 10 = (9 + 2) % 10 = 1
            s becomes "11"
        Since the digits in "11" are the same, the output is true.

    Example 2:
    Input: s = "34789"
    Output: false
    Explanation:
        Initially, s = "34789".
        After the first operation, s = "7157".
        After the second operation, s = "862".
        After the third operation, s = "48".
        Since '4' != '8', the output is false.

    Constraints:
        3 <= s.length <= 10^5
        s consists of only digits.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, "3902"},
                {false, "34789"},
        });
    }
    /*
    以3902为例
    第一轮 3+9 9+0 0+2
    第二轮 3+9+9+0 9+0+0+2
    数字出现次数1 2 1

    34789
    第一轮 3+4 4+7 7+8 8+9
    第二轮 3+4+4+7 4+7+7+8 7+8+8+9
    第三轮 3+4+4+7+4+7+7+8  4+7+7+8+7+8+8+9
    整理可得, 数字出现次数 1 3 3 1

    容易发现这是杨辉三角数
    通项公式C(n,m)

    问题是C(n,m)很大

    引入Lucas定理
    对于质数p
    有C(n,m）mod p == C(n/p, m/p) * C(n mod p, m mod p) mod p 当m==1时，返回1
    */

    int[][] t = {
            {1}, {1, 1}, {1, 2, 1}, {1, 3, 3, 1}, {1, 4, 1, 4, 1}
    };

    public boolean hasSameDigits(String s) {
        int length = s.length(), N = length - 2, f0 = 0, f1 = 0;
        for (int j = 0, prev = s.charAt(0) - '0', c; j <= N; f1 = (f1 + c * (prev = s.charAt(++j) - '0')) % 10)
            f0 = (f0 + (c = binomMod10(N, j)) * prev) % 10;
        return f0 == f1;
    }

    private int binomMod10(int n, int k) {
        int r2 = binomMod2(n, k), r5 = binomMod5(n, k);
        for (int x = 0; x < 10; x++) if (x % 2 == r2 && x % 5 == r5) return x;
        return 0;
    }

    int binomMod2(int n, int k) {
        return ((n & k) == k) ? 1 : 0;
    }

    int binomMod5(int n, int k) {
        int result = 1;
        for (int nd, kd; n > 0 || k > 0; n /= 5, k /= 5)
            if ((kd = k % 5) > (nd = n % 5)) return 0;
            else result = (result * t[nd][kd]) % 5;
        return result;
    }
}
