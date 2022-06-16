package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class StrongPasswordCheckerII {
    /*
    A password is said to be strong if it satisfies all the following criteria:
    It has at least 8 characters.
    It contains at least one lowercase letter.
    It contains at least one uppercase letter.
    It contains at least one digit.
    It contains at least one special character. The special characters are the characters in the following string: "!@#$%^&*()-+".
    It does not contain 2 of the same character in adjacent positions (i.e., "aab" violates this condition, but "aba" does not).
    Given a string password, return true if it is a strong password. Otherwise, return false.

    Example 1:
    Input: password = "IloveLe3tcode!"
    Output: true
    Explanation: The password meets all the requirements. Therefore, we return true.

    Example 2:
    Input: password = "Me+You--IsMyDream"
    Output: false
    Explanation: The password does not contain a digit and also contains 2 of the same character in adjacent positions. Therefore, we return false.

    Example 3:
    Input: password = "1aB!"
    Output: false
    Explanation: The password does not meet the length requirement. Therefore, we return false.

    Constraints:
    1 <= password.length <= 100
    password consists of letters, digits, and special characters: "!@#$%^&*()-+".
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {true, "IloveLe3tcode!"},
                {false, "Me+You--IsMyDream"},
                {false, "1aB!"}
        });
    }

    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) return false;
        // bit flag
        // 1st for lower case
        // 2ed for upper case
        // 3rd for digit
        // 4th for special character
        int result = 0, prev = 0;
        for (char c : password.toCharArray()) {
            if (c == prev) return false;
            prev = c;
            if (c >= 'a' && c <= 'z') result |= 1;
            else if (c >= 'A' && c <= 'Z') result |= 2;
            else if (c >= '0' && c <= '9') result |= 4;
            else result |= 8;
        }
        return result == 15;
    }
}
