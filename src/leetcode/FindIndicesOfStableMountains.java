package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FindIndicesOfStableMountains {
    /*
    There are n mountains in a row, and each mountain has a height. You are given an integer array height where height[i] represents the height of mountain i, and an integer threshold.
    A mountain is called stable if the mountain just before it (if it exists) has a height strictly greater than threshold. Note that mountain 0 is not stable.
    Return an array containing the indices of all stable mountains in any order.

    Example 1:
    Input: height = [1,2,3,4,5], threshold = 2
    Output: [3,4]
    Explanation:
        Mountain 3 is stable because height[2] == 3 is greater than threshold == 2.
        Mountain 4 is stable because height[3] == 4 is greater than threshold == 2.

    Example 2:
    Input: height = [10,1,10,1,10], threshold = 3
    Output: [1,3]

    Example 3:
    Input: height = [10,1,10,1,10], threshold = 10
    Output: []

    Constraints:
        2 <= n == height.length <= 100
        1 <= height[i] <= 100
        1 <= threshold <= 100
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(3, 4), new int[]{1, 2, 3, 4, 5}, 2},
                {List.of(1, 3), new int[]{10, 1, 10, 1, 10}, 3},
                {List.of(), new int[]{10, 1, 10, 1, 10}, 10},
        });
    }

    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> result = new ArrayList<>(height.length);
        for (int i = 0, last = height.length - 1; i < last; ) if (height[i++] > threshold) result.add(i);
        return result;
    }
}
