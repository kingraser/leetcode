package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class RelativeSortArray {
    /*
    Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
    Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.

    Example 1:
    Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
    Output: [2,2,2,1,4,3,3,9,6,7,19]

    Constraints:
    1 <= arr1.length, arr2.length <= 1000
    0 <= arr1[i], arr2[i] <= 1000
    All the elements of arr2 are distinct.
    Each arr2[i] is in arr1.
    */

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = IntStream.range(0, arr2.length).boxed().collect(Collectors.toMap(i -> arr2[i], Function.identity()));
        return Arrays.stream(arr1).boxed().sorted((a, b) -> sort(a, b, map)).mapToInt(i -> i).toArray();
    }

    int sort(int a, int b, Map<Integer, Integer> map) {
        Integer i = map.get(a), j = map.get(b);
        return Objects.isNull(i) ? Objects.isNull(j) ? Integer.compare(a, b) : 1 : Objects.isNull(j) ? -1 : Integer.compare(i, j);
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19}, relativeSortArray(new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, new int[]{2, 1, 4, 3, 9, 6}));
    }

}
