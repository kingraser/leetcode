package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.PriorityQueue;

public class FinalArrayStateAfterKMultiplicationOperationsI {
    /*
    You are given an integer array nums, an integer k, and an integer multiplier.
    You need to perform k operations on nums. In each operation:
        Find the minimum value x in nums. If there are multiple occurrences of the minimum value, select the one that appears first.
        Replace the selected minimum value x with x * multiplier.
    Return an integer array denoting the final state of nums after performing all k operations.

    Example 1:
    Input: nums = [2,1,3,5,6], k = 5, multiplier = 2
    Output: [8,4,6,5,6]
    Explanation:
    Operation	Result
    After operation 1	[2, 2, 3, 5, 6]
    After operation 2	[4, 2, 3, 5, 6]
    After operation 3	[4, 4, 3, 5, 6]
    After operation 4	[4, 4, 6, 5, 6]
    After operation 5	[8, 4, 6, 5, 6]

    Example 2:
    Input: nums = [1,2], k = 3, multiplier = 4
    Output: [16,8]
    Explanation:
    Operation	Result
    After operation 1	[4, 2]
    After operation 2	[4, 8]
    After operation 3	[16, 8]

    Constraints:
        1 <= nums.length <= 100
        1 <= nums[i] <= 100
        1 <= k <= 10
        1 <= multiplier <= 5
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{8, 4, 6, 5, 6}, new int[]{2, 1, 3, 5, 6}, 5, 2},
                {new int[]{16, 8}, new int[]{1, 2}, 3, 4},
        });
    }

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int length = nums.length, result[] = new int[length], head[];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(length, (a1, a2) -> a1[0] != a2[0] ? a1[0] - a2[0] : a1[1] - a2[1]);
        for (int i = 0; i < length; ) priorityQueue.add(new int[]{nums[i], i++});
        while (k-- > 0) {
            (head = priorityQueue.poll())[0] *= multiplier;
            priorityQueue.add(head);
        }
        for (int[] element : priorityQueue) result[element[1]] = element[0];
        return result;
    }

    public int[] getFinalStateII(int[] nums, int k, int multiplier) {
        for (int minIndex = 0, length = nums.length; k-- > 0; nums[minIndex] *= multiplier, minIndex = 0)
            for (int i = 1; i < length; i++) if (nums[minIndex] > nums[i]) minIndex = i;
        return nums;
    }

}
