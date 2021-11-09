package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class CountTripletsThatCanFormTwoArraysofEqualXOR {
    /*
    Given an array of integers arr.
    We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
    Let's define a and b as follows:
    a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
    b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
    Note that ^ denotes the bitwise-xor operation.
    Return the number of triplets (i, j and k) Where a == b.

    Example 1:
    Input: arr = [2,3,1,6,7]
    Output: 4
    Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)

    Example 2:
    Input: arr = [1,1,1,1,1]
    Output: 10

    Example 3:
    Input: arr = [2,3]
    Output: 0

    Example 4:
    Input: arr = [1,3,5,7,9]
    Output: 3

    Example 5:
    Input: arr = [7,11,12,9,5,2,7,17,22]
    Output: 8

    Constraints:
    1 <= arr.length <= 300
    1 <= arr[i] <= 10^8
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {4, new int[]{2, 3, 1, 6, 7}},
                {10, new int[]{1, 1, 1, 1, 1}},
                {0, new int[]{2, 3}},
                {3, new int[]{1, 3, 5, 7, 9}},
                {8, new int[]{7, 11, 12, 9, 5, 2, 7, 17, 22}}
        });
    }

    public int countTriplets(int[] arr) {
        int res = 0;
        Map<Integer, Integer> counts = new HashMap<>() {{put(0, 1);}}, idxSumMap = new HashMap<>();
        for (int i = 0, prefix = 0; i < arr.length; )
            res += i * counts.merge(prefix ^= arr[i], 1, Integer::sum) - idxSumMap.merge(prefix, ++i, Integer::sum) + 1;
        return res;
    }
    /*
    Solution 1 brute force
        find all possible combination

    complexity O(n^4)

    Solution 2 brute force with prefix
        calculate all prefixes of bitwise-xor operation.
        prefix[0] = 0
        prefix[i] = A[0]^A[1]^...^A[i - 1]
        So that for each [i, j],
        we can get A[i]^A[i+1]^...^A[j] by prefix[j+1]^prefix[i]
        in O(1) time

    complexity O(n^3)

    Solution 3 prefix XOR
        a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
        b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]

        Assume a == b (so that i,j,k is one of the result), thus
        a ^ a = b ^ a, thus
        0 = b ^ a, thus
        arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] ^ arr[j] ^ arr[j + 1] ^ ... ^ arr[k] = 0
        prefix[k+1] = prefix[i]

        So we only need to find out how many pair (i, k) of prefix value are equal.
        So we can calculate the prefix array first, then brute force count the pair.
        Because once we determine the pair (i,k), j can be any number that i < j <= k, so we need to plus k - i - 1 to the result res.

    complexity O(n^2)

    Solution 4 one pass
    Assume we now get to j-th num, so we get prefix(j).
    Assume there are several indexes before j, i1, i2, i3 ... in, make prefix(i1) == prefix(i2) == prefix(i3) == ... = prefix(in) == prefix(j)
    so the count of results we can get is
    j-i1-1 + j-i2-1 + j-i3-1 + ... + j-in-1
    so (j-1)*currency-(i1+i2+i3+...+in)
    */
}
