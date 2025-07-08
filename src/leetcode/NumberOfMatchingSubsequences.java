package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayDeque;

public class NumberOfMatchingSubsequences {
    /*
    Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
    A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
    For example, "ace" is a subsequence of "abcde".

    Example 1:
    Input: s = "abcde", words = ["a","bb","acd","ace"]
    Output: 3
    Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".

    Example 2:
    Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
    Output: 2

    Constraints:
        1 <= s.length <= 5 * 10^4
        1 <= words.length <= 5000
        1 <= words[i].length <= 50
        s and words[i] consist of only lowercase English letters.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {3, "abcde", new String[]{"a", "bb", "acd", "ace"}},
                {2, "dsahjpjauf", new String[]{"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"}},
                });
    }

    /**
     * @noinspection unchecked, DataFlowIssue
     */
    public int numMatchingSubseq(String s, String[] words) {
        int result = 0;
        ArrayDeque<int[]> waiting[] = new ArrayDeque[26], candidates;
        for (int i = 0; i < 26; i++) waiting[i] = new ArrayDeque<>();
        for (int i = 0; i < words.length; i++) waiting[words[i].charAt(0) - 'a'].add(new int[]{i, 1});
        for (byte b : s.getBytes())
            for (int length = (candidates = waiting[b - 'a']).size(), a[]; length-- > 0; )
                if ((a = candidates.poll())[1] < words[a[0]].length()) waiting[words[a[0]].charAt(a[1]++) - 'a'].add(a);
                else result++;
        return result;
    }
}
