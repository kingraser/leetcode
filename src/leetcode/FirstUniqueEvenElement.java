package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class FirstUniqueEvenElement {
    /*
    You are given an integer array nums.
    Return an integer denoting the first even integer (earliest by array index) that appears exactly once in nums. If no such integer exists, return -1.
    An integer x is considered even if it is divisible by 2.

    Example 1:
    Input: nums = [3,4,2,5,4,6]
    Output: 2
    Explanation:
    Both 2 and 6 are even and they appear exactly once. Since 2 occurs first in the array, the answer is 2.

    Example 2:
    Input: nums = [4,4]
    Output: -1
    Explanation:
    No even integer appears exactly once, so return -1.

    Constraints:
    1 <= nums.length <= 100
    1 <= nums[i] <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(
                new Object[]{2, -1, 2},
                new Object[][]{
                        {new int[]{3, 4, 2, 5, 4, 6}},
                        {new int[]{4, 4}},
                        {new int[]{2}}
                }
        );
    }

    public int firstUniqueEven(int[] nums) {
        int currentIndex = 101, result = -1;
        int[] map = new int[101];
        for (int i = 0; i < nums.length; ) {
            if (map[nums[i]] == 0) map[nums[i]] = ++i;
            else map[nums[i]] = -++i;
        }
        for (int i = 2; i < map.length; i += 2) {
            if (map[i] > 0 && map[i] < currentIndex) {
                currentIndex = map[result = i];
            }
        }
        return result;
    }
}
