package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class FruitsIntoBasketsIII {
    /*
    You are given two arrays of integers, fruits and baskets, each of length n, where fruits[i] represents the quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
    From left to right, place the fruits according to these rules:
        Each fruit type must be placed in the leftmost available basket with a capacity greater than or equal to the quantity of that fruit type.
        Each basket can hold only one type of fruit.
        If a fruit type cannot be placed in any basket, it remains unplaced.
    Return the number of fruit types that remain unplaced after all possible allocations are made.

    Example 1:
    Input: fruits = [4,2,5], baskets = [3,5,4]
    Output: 1
    Explanation:
        fruits[0] = 4 is placed in baskets[1] = 5.
        fruits[1] = 2 is placed in baskets[0] = 3.
        fruits[2] = 5 cannot be placed in baskets[2] = 4.
    Since one fruit type remains unplaced, we return 1.

    Example 2:
    Input: fruits = [3,6,1], baskets = [6,4,7]
    Output: 0
    Explanation:
        fruits[0] = 3 is placed in baskets[0] = 6.
        fruits[1] = 6 cannot be placed in baskets[1] = 4 (insufficient capacity) but can be placed in the next available basket, baskets[2] = 7.
        fruits[2] = 1 is placed in baskets[1] = 4.
    Since all fruits are successfully placed, we return 0.

    Constraints:
        n == fruits.length == baskets.length
        1 <= n <= 10^5
        1 <= fruits[i], baskets[i] <= 10^9

    Hint 1
    Sort the baskets by the pair of (basket[i], i) in the array.
    Hint 2
    For each fruit from left to right, use binary search to find the first index in the sorted array such that basket[i] >= fruit.
    Hint 3
    Use a segment tree to maintain the smallest original indices where basket[i] >= fruit.
    Hint 4
    When a valid index is found, set the corresponding point to infinity to mark it as used.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {1, new int[]{4, 2, 5}, new int[]{3, 5, 4}},
                {0, new int[]{3, 6, 1}, new int[]{6, 4, 7}},
        });
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int result = 0;
        SegmentTree segmentTree = new SegmentTree(baskets);
        for (int fruit : fruits)
            if (segmentTree.getMax() >= fruit) segmentTree.findGreaterAndUpdate(fruit);
            else result++;
        return result;
    }

    public static class SegmentTree {
        int length, segmentTree[];

        SegmentTree(int[] baskets) {
            segmentTree = new int[(length = baskets.length) << 2];
            build(0, 0, baskets.length - 1, baskets);
        }

        void build(int index, int low, int high, int[] baskets) {
            if (low == high) {
                segmentTree[index] = baskets[low];
                return;
            }
            int mid = (low + high) >> 1, left = (index << 1) + 1;
            build(left, low, mid, baskets);
            build(left + 1, mid + 1, high, baskets);
            segmentTree[index] = Math.max(segmentTree[left], segmentTree[left + 1]);
        }

        public void findGreaterAndUpdate(int fruit) {
            findGreaterAndUpdate(0, 0, length - 1, fruit);
        }

        void findGreaterAndUpdate(int idx, int low, int high, int value) {
            if (low == high) {
                segmentTree[idx] = 0;
                return;
            }
            int mid = (low + high) >> 1, left = (idx << 1) + 1;
            if (segmentTree[left] >= value) findGreaterAndUpdate(left, low, mid, value);
            else findGreaterAndUpdate(left + 1, mid + 1, high, value);
            segmentTree[idx] = Math.max(segmentTree[left], segmentTree[left + 1]);
        }

        int getMax() {
            return segmentTree[0];
        }
    }

    public int numOfUnplacedFruitsII(int[] fruits, int[] baskets) {
        int result = 0, length = baskets.length, min2PowGreaterThanLength = 1;
        while (min2PowGreaterThanLength <= length) min2PowGreaterThanLength <<= 1;
        // Build the segment tree
        int[] segmentTree = new int[min2PowGreaterThanLength << 1];
        for (int i = 0; i < length; ) segmentTree[min2PowGreaterThanLength + i] = baskets[i++];
        for (int i = min2PowGreaterThanLength - 1; i > 0; i--)
            segmentTree[i] = Math.max(segmentTree[i << 1], segmentTree[(i << 1) + 1]);
        for (int fruit : fruits) {
            int index = 1; // Start from the root of the segment tree
            if (segmentTree[index] < fruit) {
                result++;
                continue;
            }
            // Query the first valid basket
            while (index < min2PowGreaterThanLength) if (segmentTree[index <<= 1] < fruit) index++;
            // Mark the found basket as used and update the segment tree
            segmentTree[index] = -1;
            while (index > 1) segmentTree[index >>= 1] = Math.max(segmentTree[index << 1], segmentTree[(index << 1) + 1]);
        }
        return result;
    }
}
