package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class IntersectionOfTwoArrays {

    /*
    Given two arrays, write a function to compute their intersection.

    Example:
    Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{2}, new int[]{1, 2, 2, 1}, new int[]{2, 2}},
                {new int[]{2, 3}, new int[]{2, 1, 3}, new int[]{2, 2, 3, 4}}
        });
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2).filter(set::remove).toArray();
    }

    public int[] intersectionII(int[] nums1, int[] nums2) {
        int result[] = new int[nums1.length], resultLen = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; )
            if (nums1[i] < nums2[j]) i++;
            else if (nums1[i] == nums2[j++])
                for (int k = result[resultLen++] = nums1[i++]; i < nums1.length && nums1[i] == k; ) i++;
        return Arrays.copyOf(result, resultLen);
    }

}
