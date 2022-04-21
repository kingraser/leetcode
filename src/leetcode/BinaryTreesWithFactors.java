package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Wit
 */
public class BinaryTreesWithFactors {

    /*
    Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
    We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.
    Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.

    Example 1:
    Input: arr = [2,4]
    Output: 3
    Explanation: We can make these trees: [2], [4], [4, 2, 2]

    Example 2:
    Input: arr = [2,4,5,10]
    Output: 7
    Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].

    Constraints:
    1 <= arr.length <= 1000
    2 <= arr[i] <= 10^9
    All the values of arr are unique.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {3, new int[]{2, 4}},
                {7, new int[]{2, 4, 5, 10}}
        });
    }

    public int numFactoredBinaryTrees(int[] arr) {
        long res = 0L, mod = 1_000_000_000L + 7;
        Arrays.sort(arr);
        HashMap<Integer, Long> map = new HashMap<>(arr.length << 1);
        for (int i = 0, j; i < arr.length; res += map.get(arr[i++]), res %= mod)
            for (map.put(arr[i], 1L), j = 0; j < i; j++)
                if (arr[i] % arr[j] == 0)
                    map.merge(arr[i], map.get(arr[j]) * map.getOrDefault(arr[i] / arr[j], 0L), Long::sum);
        return (int) res;
    }
}
