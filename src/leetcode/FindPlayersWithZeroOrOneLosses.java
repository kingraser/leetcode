package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Wit
 */
public class FindPlayersWithZeroOrOneLosses {
    /*
    You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.
    Return a list answer of size 2 where:
    answer[0] is a list of all players that have not lost any matches.
    answer[1] is a list of all players that have lost exactly one match.
    The values in the two lists should be returned in increasing order.
    Note:
    You should only consider the players that have played at least one match.
    The testcases will be generated such that no two matches will have the same outcome.
    
    Example 1:
    Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
    Output: [[1,2,10],[4,5,7,8]]
    Explanation:
    Players 1, 2, and 10 have not lost any matches.
    Players 4, 5, 7, and 8 each have lost one match.
    Players 3, 6, and 9 each have lost two matches.
    Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
    
    Example 2:
    Input: matches = [[2,3],[1,3],[5,4],[6,4]]
    Output: [[1,2,5,6],[]]
    Explanation:
    Players 1, 2, 5, and 6 have not lost any matches.
    Players 3 and 4 each have lost two matches.
    Thus, answer[0] = [1,2,5,6] and answer[1] = [].
    
    Constraints:
    1 <= matches.length <= 10^5[
    matches[i].length == 2
    1 <= winneri, loseri <= 10^5
    winneri != loseri
    All matches[i] are unique.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(List.of(1, 2, 10), List.of(4, 5, 7, 8)), new int[][]{{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {4, 9}, {10, 4}, {10, 9}}},
                {List.of(List.of(1, 2, 5, 6), List.of()), new int[][]{{2, 3}, {1, 3}, {5, 4}, {6, 4}}}
        });
    }

    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> matchMap = new TreeMap<>();
        for (int[] match : matches) {
            matchMap.merge(match[0], 0, Integer::sum);
            matchMap.merge(match[1], 1, Integer::sum);
        }
        List<List<Integer>> result = new ArrayList<>(2) {{
            add(new ArrayList<>(matchMap.size()));
            add(new ArrayList<>(matchMap.size()));
        }};
        matchMap.forEach((k, v) -> {if (v < 2) result.get(v).add(k);});
        return result;
    }
}
