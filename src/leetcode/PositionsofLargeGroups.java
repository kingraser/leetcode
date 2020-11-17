package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Wit
 */
public class PositionsofLargeGroups {
    /*
    In a string s of lowercase letters, these letters form consecutive groups of the same character.
    For example, a string like s = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z", and "yy".
    A group is identified by an interval [start, end], where start and end denote the start and end indices (inclusive) of the group. In the above example, "xxxx" has the interval [3,6].
    A group is considered large if it has 3 or more characters.
    Return the intervals of every large group sorted in increasing order by start index.

    Example 1:
    Input: s = "abbxxxxzzy"
    Output: [[3,6]]
    Explanation: "xxxx" is the only large group with start index 3 and end index 6.

    Example 2:
    Input: s = "abc"
    Output: []
    Explanation: We have groups "a", "b", and "c", none of which are large groups.

    Example 3:
    Input: s = "abcdddeeeeaabbbcd"
    Output: [[3,5],[6,9],[12,14]]
    Explanation: The large groups are "ddd", "eeee", and "bbb".

    Example 4:
    Input: s = "aba"
    Output: []

    Constraints
    1 <= s.length <= 1000
    s contains lower-case English letters only.
    */

    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();
        int start = 0;
        for (int i = 1; i < s.length(); i++)
            if (s.charAt(i) != s.charAt(i - 1)) {
                add(start, i, result);
                start = i;
            }
        add(start, s.length(), result);
        return result;
    }

    void add(int start, int end, List<List<Integer>> result) {
        if (end - start < 3) return;
        List<Integer> temp = new ArrayList<>();
        temp.add(start);
        temp.add(end - 1);
        result.add(temp);
    }

    @Test
    public void test() {
        Assert.assertEquals(Stream.of(Stream.of(3, 6).collect(Collectors.toList())).collect(Collectors.toList()), largeGroupPositions("abbxxxxzzy"));
        Assert.assertEquals(Collections.emptyList(), largeGroupPositions("abc"));
        Assert.assertEquals(Stream.of(Stream.of(3, 5).collect(Collectors.toList()),
                Stream.of(6, 9).collect(Collectors.toList()),
                Stream.of(12, 14).collect(Collectors.toList())).collect(Collectors.toList()), largeGroupPositions("abcdddeeeeaabbbcd"));
        Assert.assertEquals(Collections.emptyList(), largeGroupPositions("aba"));

    }
}
