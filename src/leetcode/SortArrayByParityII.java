package leetcode;

import leetcode.util.ArrayUtil;

/**
 * @author Wit
 */
public class SortArrayByParityII {
    /*
    Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
    Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
    You may return any answer array that satisfies this condition.

    Example 1:
    Input: [4,2,5,7]
    Output: [4,5,2,7]
    Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.

    Note:
    2 <= A.length <= 20000
    A.length % 2 == 0
    0 <= A[i] <= 1000
    */
    public int[] sortArrayByParityII(int[] array) {
        for (int i = 0, j = 1, n = array.length; i < n && j < n; ) {
            while (i < n && (array[i] & 1) == 0) i += 2;
            while (j < n && (array[j] & 1) == 0) j += 2;
            if (i < n && j < n) ArrayUtil.swap(array, i, j);
        }
        return array;
    }
}
