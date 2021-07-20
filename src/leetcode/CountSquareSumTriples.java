package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CountSquareSumTriples {
    /*
    A square triple (a,b,c) is a triple where a, b, and c are integers and a2 + b2 = c2.
    Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.

    Example 1:
    Input: n = 5
    Output: 2
    Explanation: The square triples are (3,4,5) and (4,3,5).

    Example 2:
    Input: n = 10
    Output: 4
    Explanation: The square triples are (3,4,5), (4,3,5), (6,8,10), and (8,6,10).

    Constraints:
    1 <= n <= 250
    */
    public int countTriples(int n) {
        int result = 0, size = n * n, map[] = new int[size + 1];
        for (int i = 1; i <= n; i++) map[i * i]++;
        for (int i = 1, minus1 = n - 1; i < minus1; i++)
            for (int j = i + 1, sum; j < n && (sum = i * i + j * j) <= size; j++)
                result += map[sum];
        return result << 1;
    }

    @Test
    public void test() {
        TestUtil.testEquals(this, new Object[][]{
                {2, 5},
                {4, 10}
        });
    }
}
