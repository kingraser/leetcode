package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class ArithmeticSubarrays {
    /*
    A sequence of numbers is called arithmetic if it consists of at least two elements, and the difference between every two consecutive elements is the same. More formally, a sequence s is arithmetic if and only if s[i+1] - s[i] == s[1] - s[0] for all valid i.
    For example, these are arithmetic sequences:
    1, 3, 5, 7, 9
    7, 7, 7, 7
    3, -1, -5, -9
    The following sequence is not arithmetic:
    1, 1, 2, 5, 7
    You are given an array of n integers, nums, and two arrays of m integers each, l and r, representing the m range queries, where the ith query is the range [l[i], r[i]]. All the arrays are 0-indexed.
    Return a list of boolean elements answer, where answer[i] is true if the subarray nums[l[i]], nums[l[i]+1], ... , nums[r[i]] can be rearranged to form an arithmetic sequence, and false otherwise.

    Example 1:
    Input: nums = [4,6,5,9,3,7], l = [0,0,2], r = [2,3,5]
    Output: [true,false,true]
    Explanation:
    In the 0th query, the subarray is [4,6,5]. This can be rearranged as [6,5,4], which is an arithmetic sequence.
    In the 1st query, the subarray is [4,6,5,9]. This cannot be rearranged as an arithmetic sequence.
    In the 2nd query, the subarray is [5,9,3,7]. This can be rearranged as [3,5,7,9], which is an arithmetic sequence.

    Example 2:
    Input: nums = [-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10], l = [0,1,6,4,8,7], r = [4,4,9,7,9,10]
    Output: [false,true,false,false,true,true]

    Constraints:
    n == nums.length
    m == l.length
    m == r.length
    2 <= n <= 500
    1 <= m <= 500
    0 <= l[i] < r[i] < n
    -10^5 <= nums[i] <= 10^5
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(true, false, true),
                        new int[]{4, 6, 5, 9, 3, 7},
                        new int[]{0, 0, 2},
                        new int[]{2, 3, 5}},
                {List.of(false, true, false, false, true, true),
                        new int[]{-12, -9, -3, -12, -6, 15, 20, -25, -20, -15, -10},
                        new int[]{0, 1, 6, 4, 8, 7},
                        new int[]{4, 4, 9, 7, 9, 10}}
        });
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>(l.length);
        for (int i = 0; i < l.length; ) res.add(isArithmeticSeq(nums, l[i], r[i++]));
        return res;
    }

    private boolean isArithmeticSeq(int[] nums, int start, int end) {
        if (end - start < 2) return true;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = start; i <= end; max = Math.max(max, nums[i++])) min = Math.min(min, nums[i]);
        if (min == max) return true;
        if ((max - min) % (end - start) != 0) return false;
        for (int i = start, interval = (max - min) / (end - start), diff, reached[] = new int[end - start + 1]; i <= end; )
            if ((diff = nums[i++] - min) % interval != 0 || reached[diff / interval]++ > 0) return false;
        return true;
    }
}
