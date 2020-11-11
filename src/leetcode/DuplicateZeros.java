package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class DuplicateZeros {
    /*
    Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.
    Note that elements beyond the length of the original array are not written.
    Do the above modifications to the input array in place, do not return anything from your function.

    Example 1:
    Input: [1,0,2,3,0,4,5,0]
    Output: null
    Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]

    Example 2:
    Input: [1,2,3]
    Output: null
    Explanation: After calling your function, the input array is modified to: [1,2,3]

    Note:
    1 <= arr.length <= 10000
    0 <= arr[i] <= 9
    */

    public void duplicateZeros(int[] array) {
        for (int countZero = (int) Arrays.stream(array).filter(n -> n == 0).count(), i = array.length - 1, j = i + countZero; i < j; i--, j--)
            if (array[i] != 0) copy(array, i, j);
            else {
                copy(array, i, j--);
                copy(array, i, j);
            }
    }

    void copy(int[] array, int idx1, int idx2) {
        if (idx2 < array.length) array[idx2] = array[idx1];
    }

    @Test
    public void test() {
        int[] array = new int[]{1, 0, 2, 3, 0, 4, 5, 0};
        duplicateZeros(array);
        Assert.assertArrayEquals(new int[]{1, 0, 0, 2, 3, 0, 0, 4}, array);
    }
}
