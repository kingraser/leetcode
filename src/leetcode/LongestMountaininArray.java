package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LongestMountaininArray {
    /*
    You may recall that an array arr is a mountain array if and only if:
    arr.length >= 3
    There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
    arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
    arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
    Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.

    Example 1:
    Input: arr = [2,1,4,7,3,2,5]
    Output: 5
    Explanation: The largest mountain is [1,4,7,3,2] which has length 5.

    Example 2:
    Input: arr = [2,2,2]
    Output: 0
    Explanation: There is no mountain.

    Constraints:
    1 <= arr.length <= 10^4
    0 <= arr[i] <= 10^4

    Follow up:
    Can you solve it using only one pass?
    Can you solve it in O(1) space?
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {0, new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0}},
                {11, new int[]{0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0}},
                {3, new int[]{0, 0, 1, 0, 0, 1, 1, 1, 1, 1}},
                {5, new int[]{2, 1, 4, 7, 3, 2, 5}},
                {0, new int[]{2, 2, 2}},
        });
    }

    public int longestMountain(int[] arr) {
        int result = 0;
        for (int i = 1, up, down, len = arr.length; i < len; ) {
            for (up = down = 0; i < len && arr[i - 1] < arr[i]; i++) up++;
            for (; i < len && arr[i - 1] > arr[i]; i++) down++;
            if (up > 0 && down > 0) result = Math.max(result, up + down + 1);
            while (i < len && arr[i - 1] == arr[i]) i++;
        }
        return result;
    }
}
