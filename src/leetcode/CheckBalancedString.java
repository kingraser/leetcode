package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class CheckBalancedString {
    /*
    You are given a string num consisting of only digits.
    A string of digits is called balanced if the sum of the digits at even indices is equal to the sum of digits at odd indices.
    Return true if num is balanced, otherwise return false.

    Example 1:
    Input: num = "1234"
    Output: false
    Explanation:
        The sum of digits at even indices is 1 + 3 == 4, and the sum of digits at odd indices is 2 + 4 == 6.
        Since 4 is not equal to 6, num is not balanced.

    Example 2:
    Input: num = "24123"
    Output: true
    Explanation:
        The sum of digits at even indices is 2 + 1 + 3 == 6, and the sum of digits at odd indices is 4 + 2 == 6.
        Since both are equal the num is balanced.

    Constraints:
        2 <= num.length <= 100
        num consists of digits only
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {false, "1234"},
                {true, "24123"},
        });
    }

    public boolean isBalanced(String num) {
        int even = 0, odd = 0, length = num.length();
        boolean isEven = true;
        for (int i = 0; i < length; isEven = !isEven)
            if (isEven) even += num.charAt(i++) - '0';
            else odd += num.charAt(i++) - '0';
        return even == odd;
    }
}
