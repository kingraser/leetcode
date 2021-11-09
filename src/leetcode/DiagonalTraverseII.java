package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author Wit
 */
public class DiagonalTraverseII {
    /*
    Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.

    Example 1:
    Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [1,4,2,7,5,3,8,6,9]

    Example 2:
    Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
    Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]

    Example 3:
    Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
    Output: [1,4,2,5,3,8,6,9,7,10,11]

    Example 4:
    Input: nums = [[1,2,3,4,5,6]]
    Output: [1,2,3,4,5,6]

    Constraints:
    1 <= nums.length <= 10^5
    1 <= nums[i].length <= 10^5
    1 <= nums[i][j] <= 10^9
    There at most 10^5 elements in nums.
    */
    @Test
    public void test() {
        TestUtil.testArrayEquals(new Object[][]{
                {
                        new int[]{1, 4, 2, 7, 5, 3, 8, 6, 9},
                        List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9))
                },
                {
                        new int[]{1, 6, 2, 8, 7, 3, 9, 4, 12, 10, 5, 13, 11, 14, 15, 16},
                        List.of(List.of(1, 2, 3, 4, 5), List.of(6, 7), List.of(8), List.of(9, 10, 11), List.of(12, 13, 14, 15, 16))
                },
                {
                        new int[]{1, 4, 2, 5, 3, 8, 6, 9, 7, 10, 11},
                        List.of(List.of(1, 2, 3), List.of(4), List.of(5, 6, 7), List.of(8), List.of(9, 10, 11))
                },
                {
                        new int[]{1, 2, 3, 4, 5, 6},
                        List.of(List.of(1, 2, 3, 4, 5, 6))
                }
        });
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int res[], total = 0, keys[] = new int[200_000], rowSize = nums.size(), globalMaxKey = 0, key;
        for (int row = 0, curRowSize, maxKey; row < rowSize; total += curRowSize, globalMaxKey = Math.max(globalMaxKey, maxKey))
            for (key = row, maxKey = row + (curRowSize = nums.get(row++).size()); key < maxKey; ) keys[key++]++;
        for (res = new int[total], key = 1; key <= globalMaxKey; ) keys[key] += keys[key++ - 1];
        for (int row = 0; (key = row) < rowSize; ) for (int num : nums.get(row++)) res[--keys[key++]] = num;
        return res;
    }
}
