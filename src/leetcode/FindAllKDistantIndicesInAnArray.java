package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class FindAllKDistantIndicesInAnArray {
    /*
    You are given a 0-indexed integer array nums and two integers key and k. A k-distant index is an index i of nums for which there exists at least one index j such that |i - j| <= k and nums[j] == key.
    Return a list of all k-distant indices sorted in increasing order.

    Example 1:
    Input: nums = [3,4,9,1,3,9,5], key = 9, k = 1
    Output: [1,2,3,4,5,6]
    Explanation: Here, nums[2] == key and nums[5] == key.
    - For index 0, |0 - 2| > k and |0 - 5| > k, so there is no j where |0 - j| <= k and nums[j] == key. Thus, 0 is not a k-distant index.
    - For index 1, |1 - 2| <= k and nums[2] == key, so 1 is a k-distant index.
    - For index 2, |2 - 2| <= k and nums[2] == key, so 2 is a k-distant index.
    - For index 3, |3 - 2| <= k and nums[2] == key, so 3 is a k-distant index.
    - For index 4, |4 - 5| <= k and nums[5] == key, so 4 is a k-distant index.
    - For index 5, |5 - 5| <= k and nums[5] == key, so 5 is a k-distant index.
    - For index 6, |6 - 5| <= k and nums[5] == key, so 6 is a k-distant index.
    Thus, we return [1,2,3,4,5,6] which is sorted in increasing order.

    Example 2:
    Input: nums = [2,2,2,2,2], key = 2, k = 2
    Output: [0,1,2,3,4]
    Explanation: For all indices i in nums, there exists some index j such that |i - j| <= k and nums[j] == key, so every index is a k-distant index.
    Hence, we return [0,1,2,3,4].

    Constraints:
    1 <= nums.length <= 1000
    1 <= nums[i] <= 1000
    key is an integer from the array nums.
    1 <= k <= nums.length
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(0, 1, 2, 3, 4, 5, 6), new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 2, 5},
                {List.of(1, 2, 3, 4, 5, 6), new int[]{3, 4, 9, 1, 3, 9, 5}, 9, 1},
                {List.of(0, 1, 2, 3, 4), new int[]{2, 2, 2, 2, 2}, 2, 2}
        });
    }

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int flags[] = new int[nums.length];
        for (int i = 0, reached = -1, left, right, last = nums.length - 1; reached < last && i < nums.length; i++)
            if (nums[i] == key && reached < (right = Math.min(last, i + k)))
                for (left = Math.max(reached, Math.max(0, i - k)), reached = right; left <= right; ) flags[left++]++;
        return IntStream.range(0, flags.length).filter(i -> flags[i] > 0).boxed().collect(Collectors.toList());
    }
}
