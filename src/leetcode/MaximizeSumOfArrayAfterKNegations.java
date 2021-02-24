package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class MaximizeSumOfArrayAfterKNegations {
    /*
    Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)
    Return the largest possible sum of the array after modifying it in this way.

    Example 1:
    Input: A = [4,2,3], K = 1
    Output: 5
    Explanation: Choose indices (1,) and A becomes [4,-2,3].

    Example 2:
    Input: A = [3,-1,0,2], K = 3
    Output: 6
    Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].

    Example 3:
    Input: A = [2,-3,-1,5,-4], K = 2
    Output: 13
    Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].

    Note:
    1 <= A.length <= 10000
    1 <= K <= 10000
    -100 <= A[i] <= 100
    */

    public int largestSumAfterKNegations(int[] array, int k) {
        Arrays.sort(array);
        int i = 0;
        for (; i < array.length && array[i] < 0 && k > 0; i++, k--) array[i] = -array[i];
        if ((k & 1) == 1)
            array[i = i > array.length ? array.length - 1 : i > 0 && array[i - 1] < array[i] ? i - 1 : i] = -array[i];
        return Arrays.stream(array).sum();
    }

    @Test
    public void test() {
        Assert.assertEquals(13, largestSumAfterKNegations(new int[]{2, -3, -1, 5, -4}, 2));
        Assert.assertEquals(22, largestSumAfterKNegations(new int[]{-8, 3, -5, -3, -5, -2}, 6));
    }
}
