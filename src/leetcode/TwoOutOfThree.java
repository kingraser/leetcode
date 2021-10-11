package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class TwoOutOfThree {
    /*
    Given three integer arrays nums1, nums2, and nums3, return a distinct array containing all the values that are present in at least two out of the three arrays. You may return the values in any order.

    Example 1:
    Input: nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
    Output: [3,2]
    Explanation: The values that are present in at least two arrays are:
    - 3, in all three arrays.
    - 2, in nums1 and nums2.

    Example 2:
    Input: nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
    Output: [2,3,1]
    Explanation: The values that are present in at least two arrays are:
    - 2, in nums2 and nums3.
    - 3, in nums1 and nums2.
    - 1, in nums1 and nums3.

    Example 3:
    Input: nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
    Output: []
    Explanation: No value is present in at least two arrays.

    Constraints:
    1 <= nums1.length, nums2.length, nums3.length <= 100
    1 <= nums1[i], nums2[j], nums3[k] <= 100
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(2, 3), new int[]{1, 1, 3, 2}, new int[]{2, 3}, new int[]{3}},
                {List.of(1, 2, 3), new int[]{3, 1}, new int[]{2, 3}, new int[]{1, 2}},
                {new ArrayList<>(), new int[]{1, 2, 2}, new int[]{4, 3, 3}, new int[]{5}},
        });
    }

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> result = new ArrayList<>(100);
        int[] nums = new int[101];
        for (int num : nums1) nums[num] |= 1;
        for (int num : nums2) nums[num] |= 2;
        for (int num : nums3) nums[num] |= 4;
        for (int i = 1; i < nums.length; i++) if (nums[i] > 2 && nums[i] != 4) result.add(i);
        return result;
    }
}
