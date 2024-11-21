package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumFrequencyOfAnElementAfterPerformingOperationsI {
    /*
    You are given an integer array nums and two integers k and numOperations.
    You must perform an operation numOperations times on nums, where in each operation you:
        Select an index i that was not selected in any previous operations.
        Add an integer in the range [-k, k] to nums[i].
    Return the maximum possible frequency of any element in nums after performing the operations.

    Example 1:
    Input: nums = [1,4,5], k = 1, numOperations = 2
    Output: 2
    Explanation:
    We can achieve a maximum frequency of two by:
        Adding 0 to nums[1]. nums becomes [1, 4, 5].
        Adding -1 to nums[2]. nums becomes [1, 4, 4].

    Example 2:
    Input: nums = [5,11,20,20], k = 5, numOperations = 1
    Output: 2
    Explanation:
    We can achieve a maximum frequency of two by:
        Adding 0 to nums[1].

    Constraints:
        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^5
        0 <= k <= 10^5
        0 <= numOperations <= nums.length
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, new int[]{1, 4, 5}, 1, 2},
                {2, new int[]{5, 11, 20, 20}, 5, 1},
        });
    }

    /*
    Case 1: Focusing on Existing Elements
    This case tries to maximize the frequency of a number
    that's already present in the array.
    Range: [a - k, a + k]

    Case 2: Creating a New Target Value
    This case explores the possibility of making several numbers equal to a value
    that might not initially exist in the array.
    Range: [a, a + k + k]
    */
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int result = 0, left = 0, right = 0, length = nums.length, k2 = k << 1;
        Arrays.sort(nums);
        Map<Integer, Integer> freqencyMap = new HashMap<>();
        for (int num : nums) {
            while (right < length && nums[right] <= num + k) freqencyMap.merge(nums[right++], 1, Integer::sum);
            while (left < length && nums[left] < num - k) freqencyMap.merge(nums[left++], -1, Integer::sum);
            result = Math.max(result, Math.min(right - left, freqencyMap.getOrDefault(num, 0) + numOperations));
        }
        for (left = 0, right = 0; right < length; result = Math.max(result, Math.min(right++ - left + 1, numOperations)))
            while (nums[left] + k2 < nums[right]) left++;
        return result;
    }

    //4ms
    public int maxFrequencyII(int[] nums, int k, int numOperations) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, sum = 0, k2 = k << 1, result = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int[] count = new int[max + 1];
        for (int num : nums) count[num]++;
        if (k == 0) {
            for (int num : count) result = Math.max(result, num);
            return result;
        }
        for (int i = 0, end = Math.min(max, min + k); i <= end; i++) {
            sum += count[i];
            if (i > k2) sum -= count[i - k2];
        }

        for (int i = min; i <= max; i++) {
            if (i > k) sum -= count[i - k - 1];
            if (i > min && i + k <= max) sum += count[i + k];
            result = Math.max(result, count[i] + Math.min(sum - count[i], numOperations));
        }
        return result;
    }
}
