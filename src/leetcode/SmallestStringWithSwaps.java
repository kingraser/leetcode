package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wit
 */
public class SmallestStringWithSwaps {
    /*
    You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
    You can swap the characters at any pair of indices in the given pairs any number of times.
    Return the lexicographically-smallest string that s can be changed to after using the swaps.

    Example 1:
    Input: s = "dcab", pairs = [[0,3],[1,2]]
    Output: "bacd"
    Explanation:
    Swap s[0] and s[3], s = "bcad"
    Swap s[1] and s[2], s = "bacd"

    Example 2:
    Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
    Output: "abcd"
    Explanation:
    Swap s[0] and s[3], s = "bcad"
    Swap s[0] and s[2], s = "acbd"
    Swap s[1] and s[2], s = "abcd"

    Example 3:
    Input: s = "cba", pairs = [[0,1],[1,2]]
    Output: "abc"
    Explanation:
    Swap s[0] and s[1], s = "bca"
    Swap s[1] and s[2], s = "bac"
    Swap s[0] and s[1], s = "abc"

    Constraints:
    1 <= s.length <= 10^5
    0 <= pairs.length <= 10^5
    0 <= pairs[i][0], pairs[i][1] < s.length
    s only contains lower case English letters.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"bacd", "dcab", List.of(List.of(0, 3), List.of(1, 2))},
                {"abcd", "dcab", List.of(List.of(0, 3), List.of(1, 2), List.of(0, 2))},
                {"abc", "cba", List.of(List.of(0, 1), List.of(1, 2))}
        });
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] chars = s.toCharArray();
        int len = chars.length, graph[] = new int[len];
        for (int i = 1; i < len; ) graph[i] = i++;
        for (List<Integer> pair : pairs) union(pair.get(0), pair.get(1), graph);
        Map<Integer, Heap> map = new HashMap<>(len << 1);
        for (int i = 0; i < len; ) map.computeIfAbsent(getRoot(i, graph), k -> new Heap()).letters[chars[i++]]++;
        for (int i = 0; i < len; ) chars[i] = map.get(getRoot(i++, graph)).poll();
        return new String(chars);
    }

    void union(int a, int b, int[] graph) {
        if (a > b) union(b, a, graph);
        else graph[getRoot(b, graph)] = getRoot(a, graph);
    }

    int getRoot(int num, int[] graph) {
        int root = num;
        while (root != graph[root]) root = graph[root];
        return graph[num] = root;
    }

    public static class Heap {
        int letters[] = new int[128];
        char index = 'a';

        char poll() {
            while (index <= 'z' && letters[index] == 0) index++;
            letters[index]--;
            return index;
        }
    }
}
