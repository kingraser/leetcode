package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KthSmallestPrimeFraction {
    /*
    You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique.
    You are also given an integer k.
    For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].
    Return the kth-smallest fraction considered.
    Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].

    Example 1:
    Input: arr = [1,2,3,5], k = 3
    Output: [2,5]
    Explanation: The fractions to be considered in sorted order are:
    1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
    The third fraction is 2/5.

    Example 2:
    Input: arr = [1,7], k = 1
    Output: [1,7]

    Constraints:
        2 <= arr.length <= 1000
        1 <= arr[i] <= 3 * 10^4
        arr[0] == 1
        arr[i] is a prime number for i > 0.
        All the numbers of arr are unique and sorted in strictly increasing order.
        1 <= k <= arr.length * (arr.length - 1) / 2
    Follow up: Can you solve the problem with better than O(n2) complexity?
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{2, 5}, new int[]{1, 2, 3, 5}, 3}, {new int[]{1, 7}, new int[]{1, 7}, 1},
                });
    }

    public int[] kthSmallestPrimeFractionI(int[] arr, int k) {
        List<int[]> frac = new ArrayList<>();
        for (int i = 0, length = arr.length; i < length; i++)
            for (int j = i + 1; j < length; j++) frac.add(new int[]{arr[i], arr[j]});
        frac.sort((x, y) -> x[0] * y[1] - y[0] * x[1]);
        return frac.get(k - 1);
    }


    /**
     * @noinspection DataFlowIssue
     */
    public int[] kthSmallestPrimeFractionII(int[] arr, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> arr[a[0]] * arr[b[1]] - arr[b[0]] * arr[a[1]]);
        for (int i = 1, length = arr.length; i < length; ) pq.offer(new int[]{0, i++});
        for (int frac[], numerator, denominator; --k > 0; )
            if ((numerator = (frac = pq.poll())[0]) + 1 < (denominator = frac[1]))
                pq.offer(new int[]{numerator + 1, denominator});
        return new int[]{arr[pq.peek()[0]], arr[pq.peek()[1]]};
    }

    public int[] kthSmallestPrimeFractionIII(int[] arr, int k) {
        for (double left = 0.0, right = 1.0, mid = 0.5; ; mid = (left + right) / 2) {
            // 记录最大的分数
            int numerator = 0, denominator = 1, count = 0;
            for (int i = 0, j = 1; j < arr.length; count += i, j++) {
                while (arr[i] <= arr[j] * mid) i++;
                if (i > 0 && arr[i - 1] * denominator > arr[j] * numerator) {
                    numerator = arr[i - 1];
                    denominator = arr[j];
                }
            }
            if (count == k) return new int[]{numerator, denominator};
            if (count < k) left = mid;
            else right = mid;
        }
    }

}
