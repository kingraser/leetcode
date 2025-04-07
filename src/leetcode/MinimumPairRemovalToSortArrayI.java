package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.TreeSet;

public class MinimumPairRemovalToSortArrayI {
    /*
    Given an array nums, you can perform the following operation any number of times:
        Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
        Replace the pair with their sum.
    Return the minimum number of operations needed to make the array non-decreasing.
    An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it exists).

    Example 1:
    Input: nums = [5,2,3,1]
    Output: 2
    Explanation:
        The pair (3,1) has the minimum sum of 4. After replacement, nums = [5,2,4].
        The pair (2,4) has the minimum sum of 6. After replacement, nums = [5,6].
    The array nums became non-decreasing in two operations.

    Example 2:
    Input: nums = [1,2,2]
    Output: 0
    Explanation:
    The array nums is already sorted.

    Constraints:
        1 <= nums.length <= 50
        -1000 <= nums[i] <= 1000
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, new int[]{5, 2, 3, 1}},
                {0, new int[]{1, 2, 2}},
        });
    }

    public static int minimumPairRemoval(int[] nums) {
        int result = 0, length = nums.length, nextArray[] = new int[length], prevArray[] = new int[length], deceasingCount = 0;
        // double-linked list
        for (int i = 0; i < length; ) nextArray[i] = ++i;
        for (int i = 0; i < length; ) prevArray[i] = i++ - 1;
        long[] numsLong = new long[length];
        for (int i = 0; i < length; ) numsLong[i] = nums[i++];
        // maintain adjacent pairs {sum, index}
        TreeSet<long[]> sum = new TreeSet<>(Arrays::compare);
        // insert all pairs into set
        for (int i = 0, last = length - 1; i < last; sum.add(new long[]{numsLong[i] + numsLong[i + 1], i++}))
            if (numsLong[i] > numsLong[i + 1]) deceasingCount++;
        // simulate the process
        for (int replaceIndex, next, prev, nextOfNext; deceasingCount > 0; result++) {
            long pair[] = sum.removeFirst(), replacedSum = pair[0];
            if (numsLong[replaceIndex = (int) pair[1]] > numsLong[(next = nextArray[replaceIndex])]) deceasingCount--;
            if ((prev = prevArray[replaceIndex]) >= 0) {
                if (numsLong[prev] > numsLong[replaceIndex] && numsLong[prev] <= replacedSum) deceasingCount--;
                else if (numsLong[prev] <= numsLong[replaceIndex] && numsLong[prev] > replacedSum) deceasingCount++;
                sum.remove(new long[]{numsLong[prev] + numsLong[replaceIndex], prev});
                sum.add(new long[]{numsLong[prev] + replacedSum, prev});
            }
            if ((nextOfNext = nextArray[next]) < length) {
                if (numsLong[nextOfNext] >= numsLong[next] && numsLong[nextOfNext] < replacedSum) deceasingCount++;
                else if (numsLong[nextOfNext] < numsLong[next] && numsLong[nextOfNext] >= replacedSum) deceasingCount--;
                sum.remove(new long[]{numsLong[next] + numsLong[nextOfNext], next});
                sum.add(new long[]{replacedSum + numsLong[nextOfNext], prevArray[nextOfNext] = replaceIndex});
            }
            nextArray[replaceIndex] = nextOfNext;
            numsLong[replaceIndex] = replacedSum;
        }
        return result;
    }
}
