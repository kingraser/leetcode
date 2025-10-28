package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

public class ConstructtheLexicographicallyLargestValidSequence {
    /*
    Given an integer n, find a sequence with elements in the range [1, n] that satisfies all the following:
        The integer 1 occurs once in the sequence.
        Each integer between 2 and n occurs twice in the sequence.
        For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
    The distance between two numbers on the sequence, a[i] and a[j], is the absolute difference of their indices, |j - i|.
    Return the lexicographically-largest sequence. It is guaranteed that under the given constraints, there is always a solution.
    A sequence `a` is lexicographically larger than a sequence b (of the same length) if in the first position where a and b differ, sequence a has a number greater than the corresponding number in b.
    For example, [0,1,9,0] is lexicographically larger than [0,1,5,6] because the first position they differ is at the third number, and 9 is greater than 5.

    Example 1:
    Input: n = 3
    Output: [3,1,2,3,2]
    Explanation: [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the lexicographically largest valid sequence.

    Example 2:
    Input: n = 5
    Output: [5,3,1,4,3,5,2,4,2]

    Constraints:
        1 <= n <= 20
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {new int[]{3, 1, 2, 3, 2}, 3},
                {new int[]{5, 3, 1, 4, 3, 5, 2, 4, 2}, 5},
        });
    }

    public int[] constructDistancedSequence(int n) {
        int[] ans = new int[(n << 1) - 1];
        boolean[] visited = new boolean[n + 1];
        calc(0, ans, visited, n);
        return ans;
    }

    private boolean calc(int index, int[] ans, boolean[] visited, int n) {
        if (index == ans.length) return true;
        if (ans[index] > 0) return calc(index + 1, ans, visited, n);
        for (int digit = n; digit > 0; digit--) {
            if (visited[digit] || (digit > 1 && (index + digit >= ans.length || ans[index + digit] > 0))) continue;
            visited[ans[index] = digit] = true;
            if (digit > 1) ans[digit + index] = digit;
            if (calc(index + 1, ans, visited, n)) return true;
            ans[index] = 0;
            if (digit > 1) ans[index + digit] = 0;
            visited[digit] = false;
        }
        return false;
    }
}

