package leetcode;

/**
 * @author Wit
 */
public class CountLargestGroup {
    /*
    Given an integer n. Each number from 1 to n is grouped according to the sum of its digits.
    Return how many groups have the largest size.

    Example 1:
    Input: n = 13
    Output: 4
    Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
    [1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9]. There are 4 groups with largest size.

    Example 2:
    Input: n = 2
    Output: 2
    Explanation: There are 2 groups [1], [2] of size 1.

    Example 3:
    Input: n = 15
    Output: 6

    Example 4:
    Input: n = 24
    Output: 5

    Constraints:
    1 <= n <= 10^4
    */

    int countDigitSum(int n) {
        int result = 0;
        for (; n != 0; n /= 10) result += n % 10;
        return result;
    }

    public int countLargestGroup(int n) {
        int max = 0, count[] = new int[37], result = 0;
        for (int i = 1, value; i <= n; )
            if (max < (value = ++count[countDigitSum(i++)])) {
                max = value;
                result = 1;
            } else if (max == value) result++;
        return result;
    }
}
