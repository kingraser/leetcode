package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Wit
 */
public class NumberofEquivalentDominoPairs {
    /*
    Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.
    Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].

    Example 1:
    Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
    Output: 1

    Constraints:
    1 <= dominoes.length <= 40000
    1 <= dominoes[i][j] <= 9
    */

    public int numEquivDominoPairs(int[][] dominoes) {
        return Arrays.stream(dominoes).collect(Collectors.toMap(d -> 1 << d[0] | 1 << d[1], d -> 1, Integer::sum)).values().stream().mapToInt(v -> v * (v - 1) >> 1).sum();
    }

    @Test
    public void test() {
        Assert.assertEquals(1, numEquivDominoPairs(new int[][]{{1, 2}, {2, 1}, {3, 4}, {5, 6}}));
    }

}
