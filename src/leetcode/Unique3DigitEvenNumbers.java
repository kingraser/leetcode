package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class Unique3DigitEvenNumbers {
    /*
    You are given an array of digits called digits. Your task is to determine the number of distinct three-digit even numbers that can be formed using these digits.
    Note: Each copy of a digit can only be used once per number, and there may not be leading zeros.

    Example 1:
    Input: digits = [1,2,3,4]
    Output: 12
    Explanation: The 12 distinct 3-digit even numbers that can be formed are 124, 132, 134, 142, 214, 234, 312, 314, 324, 342, 412, and 432. Note that 222 cannot be formed because there is only 1 copy of the digit 2.

    Example 2:
    Input: digits = [0,2,2]
    Output: 2
    Explanation: The only 3-digit even numbers that can be formed are 202 and 220. Note that the digit 2 can be used twice because it appears twice in the array.

    Example 3:
    Input: digits = [6,6,6]
    Output: 1
    Explanation: Only 666 can be formed.

    Example 4:
    Input: digits = [1,3,5]
    Output: 0
    Explanation: No even 3-digit numbers can be formed.

    Constraints:
        3 <= digits.length <= 10
        0 <= digits[i] <= 9
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, new int[]{8, 8, 8, 8}},
                {0, new int[]{0, 0, 0}},
                {3, new int[]{8, 1, 0}},
                {12, new int[]{1, 2, 3, 4}},
                {2, new int[]{0, 2, 2}},
                {1, new int[]{6, 6, 6}},
                {0, new int[]{1, 3, 5}},
        });
    }

    public int totalNumbers(int[] digits) {
        int result = 0, distinctCount = 0, count[] = new int[10], countGreaterThan1 = 0;
        for (int digit : digits)
            if (count[digit]++ == 0) distinctCount++;
            else if (count[digit] == 2) countGreaterThan1++;
        // 0 needs special treatment since there may not be leading zeros
        if (count[0] > 0) {
            if (count[0] > 1) {
                //Since there may not be leading zeros，the case with the "00" prefix should be excluded.
                countGreaterThan1--;
                //Handle the "AB0" case, where A,B represents a digit not 0.
                result += (distinctCount - 1) * (distinctCount - 1);
            } else result += (distinctCount - 1) * (distinctCount - 2); //Handle the "AB0" case.
            //Handle the "AA0" case.
            result += countGreaterThan1;
        }
        for (int digit = 9; digit > 0; digit--)
            //Choose a even suffix digit(excluding 0)
            if ((digit & 1) == 0 && count[digit] > 0) {
                //Handle the AAX case where A represents a digit, X represents an even digit
                result += count[digit] == 2 ? countGreaterThan1 - 1 : countGreaterThan1;
                //Handle the "ABX" case.
                //`Total` is the count of the candidates of the first digit
                int total = count[digit] == 1 ? distinctCount - 1 : distinctCount;
                //When there is a 0 digit
                if (count[0] > 0) result += (total - 1) * (total - 1);
                //Otherwise there is no 0 digit
                else result += total * (total - 1);
            }
        return result;
    }
}
