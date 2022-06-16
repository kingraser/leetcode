package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class ElementAppearingMoreThan25PercentInSortedArray {
    /*
    Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.

    Example 1:
    Input: arr = [1,2,2,6,6,6,6,7,10]
    Output: 6

    Example 2:
    Input: arr = [1,1]
    Output: 1

    Constraints:
    1 <= arr.length <= 10^4
    0 <= arr[i] <= 10^5
    */
    public int findSpecialInteger(int[] arr) {
        for (int len = arr.length, quarter = len >> 2, quarters[] = new int[]{arr[quarter], arr[len >> 1], arr[(3 * len) >> 2]}, i = 0, elem, left = 0; i < quarters.length; )
            if (arr[findFirst(arr, elem = quarters[i++], left, left += quarter) + quarter] == elem) return elem;
        return -1;
    }

    /**
     * find first target in a sorted array
     *
     * @param arr    sorted array in ascending order
     * @param target target
     * @param low    low inclusive
     * @param high   high exclusive
     * @return the first target idx
     */
    int findFirst(int[] arr, int target, int low, int high) {
        int idx = Arrays.binarySearch(arr, low, high, target);
        return idx < 0 ? high : findFirst(arr, target, low, idx);
    }

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, new int[]{1, 1, 1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 12, 12, 12}}
        });
    }
}
