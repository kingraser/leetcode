package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Wit
 */
public class FindLatestGroupOfSizeM {
    /*
    Given an array arr that represents a permutation of numbers from 1 to n.
    You have a binary string of size n that initially has all its bits set to zero. At each step i (assuming both the binary string and arr are 1-indexed) from 1 to n, the bit at position arr[i] is set to 1.
    You are also given an integer m. Find the latest step at which there exists a group of ones of length m. A group of ones is a contiguous substring of 1's such that it cannot be extended in either direction.
    Return the latest step at which there exists a group of ones of length exactly m. If no such group exists, return -1.

    Example 1:
    Input: arr = [3,5,1,2,4], m = 1
    Output: 4
    Explanation:
    Step 1: "00100", groups: ["1"]
    Step 2: "00101", groups: ["1", "1"]
    Step 3: "10101", groups: ["1", "1", "1"]
    Step 4: "11101", groups: ["111", "1"]
    Step 5: "11111", groups: ["11111"]
    The latest step at which there exists a group of size 1 is step 4.

    Example 2:
    Input: arr = [3,1,5,4,2], m = 2
    Output: -1
    Explanation:
    Step 1: "00100", groups: ["1"]
    Step 2: "10100", groups: ["1", "1"]
    Step 3: "10101", groups: ["1", "1", "1"]
    Step 4: "10111", groups: ["1", "111"]
    Step 5: "11111", groups: ["11111"]
    No group of size 2 exists during any step.

    Constraints:
    n == arr.length
    1 <= m <= n <= 10^5
    1 <= arr[i] <= n
    All integers in arr are distinct.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {-1, new int[]{8, 16, 10, 4, 7, 5, 1, 11, 14, 12, 13, 6, 3, 2, 9, 17, 15, 19, 18}, 7},
                {1, new int[]{4, 3, 2, 1}, 1},
                {-1, new int[]{3, 1, 2}, 2},
                {1, new int[]{1, 2}, 1},
                {4, TestUtil.getArray("[3,5,1,2,4]"), 1},
                {-1, TestUtil.getArray("[3,1,5,4,2]"), 2}
        });
    }

    public int findLatestStep(int[] arr, int m) {
        if (arr.length == m) return m;
        TreeMap<Integer, Integer> map = new TreeMap<>() {{put(0, arr.length - 1);}};
        for (int i = arr.length - 1, left, mid, right, leftSize, rightSize; i >= 0 && !map.isEmpty(); i--) {
            Map.Entry<Integer, Integer> entry = map.floorEntry(mid = arr[i] - 1);
            if (entry == null || entry.getValue() < mid) continue;
            map.remove(left = entry.getKey());
            if ((leftSize = mid - left) == m || (rightSize = (right = entry.getValue()) - mid) == m) return i;
            if (leftSize > m) map.put(left, mid - 1);
            if (rightSize > m) map.put(mid + 1, right);
        }
        return -1;
    }
}
