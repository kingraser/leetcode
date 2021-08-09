package leetcode;

/**
 * @author Wit
 */
public class ThreeDivisors {
    /*
    Given an integer n, return true if n has exactly three positive divisors. Otherwise, return false.
    An integer m is a divisor of n if there exists an integer k such that n = k * m.

    Example 1:
    Input: n = 2
    Output: false
    Explanation: 2 has only two divisors: 1 and 2.

    Example 2:
    Input: n = 4
    Output: true
    Explanation: 4 has three divisors: 1, 2, and 4.

    Constraints:
    1 <= n <= 10^4
    */
    public boolean isThree(int n) {
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return i * i == n;
        return false;
    }
}
