package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

public class SortIntegersByBinaryReflection {
    /*
    You are given an integer array nums.
    The binary reflection of a positive integer is defined as the number obtained by reversing the order of its binary digits (ignoring any leading zeros) and interpreting the resulting binary number as a decimal.
    Sort the array in ascending order based on the binary reflection of each element. If two different numbers have the same binary reflection, the smaller original number should appear first.
    Return the resulting sorted array.

    Example 1:
    Input: nums = [4,5,4]
    Output: [4,4,5]
    Explanation:
    Binary reflections are:
        4 -> (binary) 100 -> (reversed) 001 -> 1
        5 -> (binary) 101 -> (reversed) 101 -> 5
        4 -> (binary) 100 -> (reversed) 001 -> 1
    Sorting by the reflected values gives [4, 4, 5].

    Example 2:
    Input: nums = [3,6,5,8]
    Output: [8,3,6,5]
    Explanation:
    Binary reflections are:
        3 -> (binary) 11 -> (reversed) 11 -> 3
        6 -> (binary) 110 -> (reversed) 011 -> 3
        5 -> (binary) 101 -> (reversed) 101 -> 5
        8 -> (binary) 1000 -> (reversed) 0001 -> 1
    Sorting by the reflected values gives [8, 3, 6, 5].
    Note that 3 and 6 have the same reflection, so we arrange them in increasing order of original value.

    Constraints:
        1 <= nums.length <= 100
        1 <= nums[i] <= 10^9
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{2, 8}, new int[]{8, 2}},
                {new int[]{4, 4, 5}, new int[]{4, 5, 4}},
                {new int[]{8, 3, 6, 5}, new int[]{3, 6, 5, 8}},
        });
    }

    public int[] sortByReflection(int[] nums) {
        int length = nums.length, map[][] = new int[length][2];
        for (int i = 0; i < length; i++) map[i][1] = reverse(map[i][0] = nums[i]);
        Arrays.sort(map, (a, b) -> a[1] - b[1] == 0 ? a[0] - b[0] : a[1] - b[1]);
        for (int i = 0; i < length; i++) nums[i] = map[i][0];
        return nums;
    }

    private int reverse(int n) {
        int result = 0, size = 0, digits[] = new int[32];
        while (n != 0) {
            digits[size++] = (n & 1);
            n >>= 1;
        }
        for (int i = 0; i < size; i++) result = (result << 1) | digits[i];
        return result;
    }
}
