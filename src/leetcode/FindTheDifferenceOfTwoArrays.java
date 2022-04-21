package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class FindTheDifferenceOfTwoArrays {
    /*
    Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:

    answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
    answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
    Note that the integers in the lists may be returned in any order.

    Example 1:
    Input: nums1 = [1,2,3], nums2 = [2,4,6]
    Output: [[1,3],[4,6]]
    Explanation:
    For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
    For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums2. Therefore, answer[1] = [4,6].

    Example 2:
    Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
    Output: [[3],[]]
    Explanation:
    For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
    Every integer in nums2 is present in nums1. Therefore, answer[1] = [].

    Constraints:
    1 <= nums1.length, nums2.length <= 1000
    -1000 <= nums1[i], nums2[i] <= 1000
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(List.of(1, 3), List.of(4, 6)), new int[]{1, 2, 3}, new int[]{2, 4, 6}},
                {List.of(List.of(3), List.of()), new int[]{1, 2, 3, 3}, new int[]{1, 1, 2, 2}},
        });
    }

    static final int MAX = 1000, SIZE = (MAX << 1) + 1;

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        int maxSize = Integer.max(nums1.length, nums2.length), map1[] = new int[SIZE], map2[] = new int[SIZE];
        List<Integer> first = new ArrayList<>(maxSize), second = new ArrayList<>(maxSize);
        for (int i : nums1) map1[i + MAX]++;
        for (int i : nums2) map2[i + MAX]++;
        for (int i : nums1)
            if (map2[i += MAX] == 0 && map1[i] > 0) {
                map1[i] = -1;
                first.add(i - MAX);
            }
        for (int i : nums2)
            if (map1[i += MAX] == 0 && map2[i] > 0) {
                map2[i] = -1;
                second.add(i - MAX);
            }
        List<List<Integer>> result = new ArrayList<>(2);
        result.add(first);
        result.add(second);
        return result;
    }
}
