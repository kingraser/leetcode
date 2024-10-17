package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FindXSumOfAllKLongSubarraysI {
    /*
    You are given an array nums of n integers and two integers k and x.
    The x-sum of an array is calculated by the following procedure:
        Count the occurrences of all elements in the array.
        Keep only the occurrences of the top x most frequent elements. If two elements have the same number of occurrences, the element with the bigger value is considered more frequent.
        Calculate the sum of the resulting array.
    Note that if an array has less than x distinct elements, its x-sum is the sum of the array.
    Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].

    Example 1:
    Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2
    Output: [6,10,12]
    Explanation:
        For subarray [1, 1, 2, 2, 3, 4], only elements 1 and 2 will be kept in the resulting array. Hence, answer[0] = 1 + 1 + 2 + 2.
        For subarray [1, 2, 2, 3, 4, 2], only elements 2 and 4 will be kept in the resulting array. Hence, answer[1] = 2 + 2 + 2 + 4. Note that 4 is kept in the array since it is bigger than 3 and 1 which occur the same number of times.
        For subarray [2, 2, 3, 4, 2, 3], only elements 2 and 3 are kept in the resulting array. Hence, answer[2] = 2 + 2 + 2 + 3 + 3.

    Example 2:
    Input: nums = [3,8,7,8,7,5], k = 2, x = 2
    Output: [11,15,15,15,12]
    Explanation:
    Since k == x, answer[i] is equal to the sum of the subarray nums[i..i + k - 1].

    Constraints:
        1 <= n == nums.length <= 50
        1 <= nums[i] <= 50
        1 <= x <= k <= nums.length
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{6, 10, 12}, new int[]{1, 1, 2, 2, 3, 4, 2, 3}, 6, 2},
                {new int[]{11, 15, 15, 15, 12}, new int[]{3, 8, 7, 8, 7, 5}, 2, 2},
        });
    }

    public int[] findXSum(int[] nums, int k, int x) {
        int length = nums.length, size = 0, result[] = new int[length - k + 1];
        TopKFreqMap topKFreqMap = new TopKFreqMap(x);
        for (int i = 0; i < k; i++) topKFreqMap.add(nums[i]);
        result[size++] = (int) topKFreqMap.getTopKSum();
        for (int right = k, left = 0; right < length; ) {
            topKFreqMap.remove(nums[left++]);
            topKFreqMap.add(nums[right++]);
            result[size++] = (int) topKFreqMap.getTopKSum();
        }
        return result;
    }

    public static class TopKFreqMap {
        int k;

        public TopKFreqMap(int k) {
            this.k = k;
        }

        long sum;

        Map<Integer, Integer> freqMap = new HashMap<>();

        TreeSet<long[]> top = new TreeSet<>(this::compare), bottom = new TreeSet<>(this::compare);

        public void add(int n) {
            long[] pair = new long[]{freqMap.merge(n, 1, Integer::sum) - 1, n};
            if (top.remove(pair) || top.size() < k) {
                pair[0]++;
                sum += n;
                top.add(pair);
                return;
            }
            bottom.remove(pair);
            pair[0]++;
            bottom.add(pair);
            balance();
        }

        /**
         * @noinspection DataFlowIssue
         */
        void balance() {
            if (bottom.isEmpty()) return;
            if (top.size() < k) {
                topAdd(bottom.pollFirst());
                return;
            }
            if (compare(top.last(), bottom.first()) < 0) return;
            long[] topPair = top.pollLast(), bottomPair = bottom.pollFirst();
            bottom.add(topPair);
            sum -= getSum(topPair);
            topAdd(bottomPair);
        }

        void topAdd(long[] a) {
            sum += getSum(a);
            top.add(a);
        }

        long getSum(long[] a) {
            return a[0] * a[1];
        }

        int compare(long[] a, long[] b) {
            int result = (int) (b[0] - a[0]);
            return result != 0 ? result : (int) (b[1] - a[1]);
        }

        public void remove(int n) {
            long[] pair = new long[]{freqMap.merge(n, -1, Integer::sum) + 1, n};
            if (bottom.remove(pair)) {
                if (--pair[0] > 0) bottom.add(pair);
                return;
            }
            top.remove(pair);
            sum -= getSum(pair);
            if (--pair[0] > 0) bottom.add(pair);
            balance();
        }

        public long getTopKSum() {
            return sum;
        }
    }
}
