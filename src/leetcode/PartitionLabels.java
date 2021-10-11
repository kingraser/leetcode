package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class PartitionLabels {
    /*
    You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.
    Return a list of integers representing the size of these parts.

    Example 1:
    Input: s = "ababcbacadefegdehijhklij"
    Output: [9,7,8]
    Explanation:
    The partition is "ababcbaca", "defegde", "hijhklij".
    This is a partition so that each letter appears in at most one part.
    A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.

    Example 2:
    Input: s = "eccbbbbdec"
    Output: [10]

    Constraints:
    1 <= s.length <= 500
    s consists of lowercase English letters.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of(9, 7, 8), "ababcbacadefegdehijhklij"},
                {List.of(10), "eccbbbbdec"},
        });
    }

    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>(26);
        int sLen = s.length(), lastIdxes[] = new int[128];
        for (int i = 0; i < sLen; ) lastIdxes[s.charAt(i)] = i++;
        for (int i = 0, start = 0, last = 0; i < sLen; )
            if ((last = Integer.max(last, lastIdxes[s.charAt(i)])) == i++) {
                result.add(last - start + 1);
                start = last + 1;
            }
        return result;
    }
}
