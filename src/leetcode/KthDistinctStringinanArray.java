package leetcode;

import leetcode.util.ArrayUtil;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Wit
 */
public class KthDistinctStringinanArray {
    /*
    A distinct string is a string that is present only once in an array.
    Given an array of strings arr, and an integer k, return the kth distinct string present in arr. If there are fewer than k distinct strings, return an empty string "".
    Note that the strings are considered in the order in which they appear in the array.

    Example 1:
    Input: arr = ["d","b","c","b","c","a"], k = 2
    Output: "a"
    Explanation:
    The only distinct strings in arr are "d" and "a".
    "d" appears 1st, so it is the 1st distinct string.
    "a" appears 2nd, so it is the 2nd distinct string.
    Since k == 2, "a" is returned.

    Example 2:
    Input: arr = ["aaa","aa","a"], k = 1
    Output: "aaa"
    Explanation:
    All strings in arr are distinct, so the 1st string "aaa" is returned.

    Example 3:
    Input: arr = ["a","b","a"], k = 3
    Output: ""
    Explanation:
    The only distinct string is "b". Since there are fewer than 3 distinct strings, we return an empty string "".

    Constraints:
    1 <= k <= arr.length <= 1000
    1 <= arr[i].length <= 5
    arr[i] consists of lowercase English letters.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"a", ArrayUtil.of("d", "b", "c", "b", "c", "a"), 2},
                {"aaa", ArrayUtil.of("aaa", "aa", "a"), 1},
                {"", ArrayUtil.of("a", "b", "a"), 3}
        });
    }

    public String kthDistinct(String[] arr, int k) {
        return Arrays.stream(arr).collect(Collectors.toMap(s -> s, s -> 1, Integer::sum, LinkedHashMap::new)).entrySet().stream().filter(e -> Objects.equals(e.getValue(), 1)).map(Map.Entry::getKey).skip(--k).findFirst().orElse("");
    }
}
