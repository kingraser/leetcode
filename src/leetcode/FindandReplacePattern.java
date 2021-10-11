package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wit
 */
public class FindandReplacePattern {
    /*
    Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.
    A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
    Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.

    Example 1:
    Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
    Output: ["mee","aqq"]
    Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
    "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.

    Example 2:
    Input: words = ["a","b","c"], pattern = "a"
    Output: ["a","b","c"]

    Constraints:
    1 <= pattern.length <= 20
    1 <= words.length <= 50
    words[i].length == pattern.length
    pattern and words[i] are lowercase English letters.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of("mee", "aqq"), new String[]{"abc", "deq", "mee", "aqq", "dkd", "ccc"}, "abb"},
                {List.of("a", "b", "c"), new String[]{"a", "b", "c"}, "a"}
        });
    }

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int[] patternArray = getPattern(pattern);
        return Arrays.stream(words).filter(word -> matches(word, patternArray)).collect(Collectors.toList());
    }

    int[] getPattern(String pattern) {
        int[] result = new int[pattern.length()];
        for (int i = 0, j = 0, k, l, map[] = new int[128]; i < result.length; )
            result[i] = (l = map[k = pattern.charAt(i++)]) == 0 ? map[k] = ++j : l;
        return result;
    }

    private boolean matches(String word, int[] pattern) {
        for (int i = 0, j = 0, k, l, map[] = new int[128]; i < pattern.length; )
            if (pattern[i] != ((l = map[k = word.charAt(i++)]) == 0 ? map[k] = ++j : l)) return false;
        return true;
    }
}
