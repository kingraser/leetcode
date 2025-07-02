package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class SwapAdjacentInLRString {
    /*
    In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL",
    a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR".
    Given the starting string start and the ending string result,
    return True if and only if there exists a sequence of moves to transform start to result.

    Example 1:
    Input: start = "RXXLRXRXL", result = "XRLXXRRLX"
    Output: true
    Explanation: We can transform start to result following these steps:
    RXXLRXRXL ->
    XRXLRXRXL ->
    XRLXRXRXL ->
    XRLXXRRXL ->
    XRLXXRRLX

    Example 2:
    Input: start = "X", result = "L"
    Output: false

    Constraints:
        1 <= start.length <= 10^4
        start.length == result.length
        Both start and result will only consist of characters in 'L', 'R', and 'X'.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {false, "RXR", "XXR"},
                {true, "RXXLRXRXL", "XRLXXRRLX"},
                {false, "X", "L"},
        });
    }

    public boolean canTransform(String start, String result) {
        for (int startIndex = 0, resultIndex = 0, length = start.length(), cStart = 0, cResult = 1; ; startIndex++, resultIndex++) {
            // get the non-X positions of 2 strings
            while (startIndex < length && (cStart = start.charAt(startIndex)) == 'X') startIndex++;
            while (resultIndex < length && (cResult = result.charAt(resultIndex)) == 'X') resultIndex++;
            //if both of the pointers reach the end the strings are transformable
            if (startIndex == length) return resultIndex == length;
            // if only one of the pointer reach the end they are not transformable
            if (resultIndex == length) return false;
            if (cStart != cResult) return false;
            // if the character is 'L', it can only be moved to the left. startIndex should be greater or equal to resultIndex.
            if (cStart == 'L' && resultIndex > startIndex) return false;
            // if the character is 'R', it can only be moved to the right. resultIndex should be greater or equal to startIndex.
            if (cStart == 'R' && startIndex > resultIndex) return false;
        }
    }
}
