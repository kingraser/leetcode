package leetcode;

/**
 * @author Wit
 */
public class XOROperationinanArray {
    /*
    Given an integer n and an integer start.
    Define an array nums where nums[i] = start + 2*i (0-indexed) and n == nums.length.
    Return the bitwise XOR of all elements of nums.

    Example 1:
    Input: n = 5, start = 0
    Output: 8
    Explanation: Array nums is equal to [0, 2, 4, 6, 8] where (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8.
    Where "^" corresponds to bitwise XOR operator.

    Example 2:
    Input: n = 4, start = 3
    Output: 8
    Explanation: Array nums is equal to [3, 5, 7, 9] where (3 ^ 5 ^ 7 ^ 9) = 8.

    Example 3:
    Input: n = 1, start = 7
    Output: 7

    Example 4:
    Input: n = 10, start = 5
    Output: 2

    Constraints:
    1 <= n <= 1000
    0 <= start <= 1000
    n == nums.length
    */

    /**
     * calculate start^(start+1)^(start+2)^……^(start+n-1) where start is even
     *
     * @param n     n numbers
     * @param start start number
     *              must be even
     * @return xor result
     */
    int xorOperationB(int n, int start) {
        /**
         * Easy to find out 2a^(2a+1) = 1
         * If n is even, then the result is 1^1^1……^1 where the 1's count is n/2
         * the result is 1 when n/2 is odd or 0 when n/2 is even
         */
        if ((n & 1) == 0) return (n >> 1) & 1;
        /**
         * If n is odd, then the result is 1^1^1……^1^(start+n-1) where the 1's count is (n-1)/2
         */
        else return ((n >> 1) & 1) ^ (start + n - 1);
    }

    /**
     * calculate start^(start+1)^(start+2)^……^(start+n-1)
     *
     * @param n     n numbers
     * @param start start number
     * @return xor result
     */
    int xorOperationA(int n, int start) {
        /**
         * If start is odd, then (start-1) is even
         * Notice we get (start-1)^result when we use xorOperationB function with parameter ((n+1), (start-1))
         * We cant get the result by xor the xorOperationB return with (start-1) to avoid the redundant (start-1)'s influence
         */
        if ((start & 1) == 1) return (start - 1) ^ xorOperationB(n + 1, start - 1);
        /**
         * If start is even, just use xorOperationB function
         */
        else return xorOperationB(n, start);
    }

    /**
     * calculate start^(start+2)^(start+4)^……^(start+2*(n-1))
     * Easy to find out all the number's have the same last bit
     * So the xor result's last bit is 0 when start is even or n is even, otherwise is 1
     * Shift all numbers by 1 to the right and get the new sequence (start/2), (start/2)+1, ..., (start/2)+(n-1)
     * Use xorOperationA to get the result of new sequence XOR operation
     * Then shift the result by 1 to the left and add the last bit to get the final result
     *
     * @param n     n numbers
     * @param start start number
     * @return xor result
     */
    public int xorOperation(int n, int start) {
        int result = xorOperationA(n, start >> 1) << 1;
        return ((n & start & 1) == 1) ? ++result : result;
    }
}
