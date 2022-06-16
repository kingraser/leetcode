package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class ThousandSeparator {
    /*
    Given an integer n, add a dot (".") as the thousands separator and return it in string format.

    Example 1:
    Input: n = 987
    Output: "987"

    Example 2:
    Input: n = 1234
    Output: "1.234"

    Example 3:
    Input: n = 123456789
    Output: "123.456.789"

    Example 4:
    Input: n = 0
    Output: "0"

    Constraints:
    0 <= n < 2^31
    */

    public String thousandSeparator(int n) {
        char[] result = new char[13];
        int idx = result.length - 1;
        do {
            if (((result.length - idx) & 3) == 0) result[idx--] = '.';
            result[idx--] = (char) ('0' + n % 10);
        } while ((n /= 10) != 0);
        return new String(result, ++idx, result.length - idx);
    }

    @Test
    public void test() {
        TestUtil.testEquals( new Object[][]{
                {"987", 987},
                {"1.234", 1234},
                {"123.456.789", 123456789},
                {"0", 0}
        });
    }
}
