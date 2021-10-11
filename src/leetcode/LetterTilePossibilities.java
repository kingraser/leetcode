package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class LetterTilePossibilities {
    /*
    You have n  tiles, where each tile has one letter tiles[i] printed on it.
    Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.

    Example 1:
    Input: tiles = "AAB"
    Output: 8
    Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".

    Example 2:
    Input: tiles = "AAABBC"
    Output: 188

    Example 3:
    Input: tiles = "V"
    Output: 1

    Constraints:
    1 <= tiles.length <= 7
    tiles consists of uppercase English letters.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {8, "AAB"},
                {188, "AAABBC"},
                {1, "V"},
        });

    }

    public int numTilePossibilities(String tiles) {
        int[] counts = new int[128];
        for (int i = 0, len = tiles.length(); i < len; ) counts[tiles.charAt(i++)]++;
        return dfs(counts);
    }

    int dfs(int[] arr) {
        int sum = 0;
        for (int i = 65; i < 91; arr[i++]++) if (arr[i]-- > 0) sum += dfs(arr) + 1;
        return sum;
    }
}
