package leetcode;

import leetcode.util.ArrayUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class SortArrayByParity {
    /*
    Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
    You may return any answer array that satisfies this condition.

    Example 1:
    Input: [3,1,2,4]
    Output: [2,4,3,1]
    The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

    Note:
    1 <= A.length <= 5000
    0 <= A[i] <= 5000
    */

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{0, 2}, sortArrayByParity(new int[]{0, 2}));
        Assert.assertArrayEquals(new int[]{4, 2, 1, 3}, sortArrayByParity(new int[]{3, 1, 2, 4}));
    }

    public int[] sortArrayByParity(int[] array) {
        for (int i = 0, j = array.length - 1; i < j; ) {
            while (i < j && (array[i] & 1) == 0) i++;
            while (i < j && (array[j] & 1) == 1) j--;
            ArrayUtil.swap(array, i++, j--);
        }
        return array;
    }
}
