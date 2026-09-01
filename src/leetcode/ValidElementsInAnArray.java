package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidElementsInAnArray {
    /*
    You are given an integer array nums.
    An element nums[i] is considered valid if it satisfies at least one of the following conditions:
    It is strictly greater than every element to its left.
    It is strictly greater than every element to its right.
    The first and last elements are always valid.
    Return an array of all valid elements in the same order as they appear in nums.

    Example 1:
    Input: nums = [1,2,4,2,3,2]
    Output: [1,2,4,3,2]
    Explanation:
    nums[0] and nums[5] are always valid.
    nums[1] and nums[2] are strictly greater than every element to their left.
    nums[4] is strictly greater than every element to its right.
    Thus, the answer is [1, 2, 4, 3, 2].

    Example 2:
    Input: nums = [5,5,5,5]
    Output: [5,5]
    Explanation:
    The first and last elements are always valid.
    No other elements are strictly greater than all elements to their left or to their right.
    Thus, the answer is [5, 5].

    Example 3:
    Input: nums = [1]
    Output: [1]
    Explanation:
    Since there is only one element, it is always valid. Thus, the answer is [1].

    Constraints:
    1 <= nums.length <= 100
    1 <= nums[i] <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(
                new Object[]{List.of(1, 2, 4, 3, 2), List.of(5, 5), List.of(1)},
                new Object[][]{
                        {new int[]{1, 2, 4, 2, 3, 2}},
                        {new int[]{5, 5, 5, 5}},
                        {new int[]{1}},
                }
        );
    }

    public List<Integer> findValidElements(int[] nums) {
        List<Integer> result = new ArrayList<>(nums.length);
        int[] asc = new int[nums.length], desc = new int[nums.length];
        int aSize = 0, dSize = 0;
        for (int num : nums)
            if (aSize == 0 || asc[aSize - 1] < num) {
                asc[aSize++] = num;
                dSize = 0;
            } else {
                while (dSize > 0 && desc[dSize - 1] <= num) dSize--;
                desc[dSize++] = num;
            }
        for (int i = 0; i < aSize; i++) result.add(asc[i]);
        for (int i = 0; i < dSize; i++) result.add(desc[i]);
        return result;
    }
}
