package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class ValidMountainArray {
    /*
    Given an array of integers arr, return true if and only if it is a valid mountain array.
    Recall that arr is a mountain array if and only if:
    arr.length >= 3
    There exists some i with 0 < i < arr.length - 1 such that:
    arr[0] < arr[1] < ... < arr[i - 1] < A[i]
    arr[i] > arr[i + 1] > ... > arr[arr.length - 1]

    Example 1:
    Input: arr = [2,1]
    Output: false

    Example 2:
    Input: arr = [3,5,5]
    Output: false

    Example 3:
    Input: arr = [0,3,2,1]
    Output: true

    Constraints:
    1 <= arr.length <= 10^4
    0 <= arr[i] <= 10^4
    */

    @Test
    public void test() {
        Assert.assertFalse(validMountainArray(new int[]{2, 1}));
    }

    public boolean validMountainArray(int[] array) {
        int length = array.length, left = 0, right = length - 1;
        while (left < right && array[left] < array[left + 1]) left++;
        while (left < right && array[right] < array[right - 1]) right--;
        return left == right && left > 0 && right < length - 1;
    }
}
