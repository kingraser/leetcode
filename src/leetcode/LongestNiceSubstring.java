package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Wit
 */
public class LongestNiceSubstring {
    /*
    A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.
    Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.

    Example 1:
    Input: s = "YazaAay"
    Output: "aAa"
    Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
    "aAa" is the longest nice substring.

    Example 2:
    Input: s = "Bb"
    Output: "Bb"
    Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.

    Example 3:
    Input: s = "c"
    Output: ""
    Explanation: There are no nice substrings.

    Example 4:
    Input: s = "dDzeE"
    Output: "dD"
    Explanation: Both "dD" and "eE" are the longest nice substrings.
    As there are multiple longest nice substrings, return "dD" since it occurs earlier.

    Constraints:
    1 <= s.length <= 100
    s consists of uppercase and lowercase English letters.
    */

    static final int SIZE = 100, EMPTY[] = new int[]{0, 0};

    public String longestNiceSubstring(String s) {
        int result[], length = s.length(), map[][][] = new int[26][2][SIZE + 1];
        char[] chars = s.toCharArray();
        for (int i = 0, idx, letter, caseIdx; i < length; )
            map[letter = getLetterIdx(idx = getCharIdx(chars[i]))][caseIdx = getCaseIdx(idx)][map[letter][caseIdx][SIZE]++] = i++;
        return new String(chars, (result = longestNiceSubstring(chars, map, 0, length, 1))[0], getLength(result));
    }

    /**
     * get char index
     *
     * @param character char
     * @return char index: positive for lower case, negative for upper case
     */
    int getCharIdx(char character) {
        return character >= 'a' && character <= 'z' ? character - 'a' : --character - 'Z';
    }

    /**
     * get letter index
     *
     * @param charIdx char index
     * @return letter index: 0-25
     */
    int getLetterIdx(int charIdx) {
        return charIdx >= 0 ? charIdx : charIdx + 26;
    }

    /**
     * get case index
     *
     * @param charIdx char index
     * @return case index: 0 for lower case, 1 for upper case
     */
    int getCaseIdx(int charIdx) {
        return charIdx < 0 ? 1 : 0;
    }

    /**
     * get longest nice sub-string
     *
     * @param chars          original string char array
     * @param map            3-dimension array
     *                       first dimension for letters(26)
     *                       second dimension for cases(2)
     *                       third dimension for index
     * @param start          start of sub-string inclusive
     * @param end            end of sub-string exclusive
     * @param currentLongest the current longest sub-string length for return without redundant calculation
     * @return the longest nice sub-string: [0] for start inclusive, [1] for end exclusive, [0, 0] for return fast
     */
    int[] longestNiceSubstring(char[] chars, int[][][] map, int start, int end, int currentLongest) {
        if (end - start <= currentLongest) return EMPTY;
        for (int i = start, prefix[], suffix[], prefixLen; i < end; i++) {
            if (isNice(chars[i], start, end, map)) continue;
            prefix = longestNiceSubstring(chars, map, start, i, currentLongest);
            suffix = longestNiceSubstring(chars, map, i + 1, end, Integer.max(currentLongest, prefixLen = getLength(prefix)));
            return prefixLen >= getLength(suffix) ? prefix : suffix;
        }
        return new int[]{start, end};
    }

    int getLength(int[] prefix) {
        return prefix[1] - prefix[0];
    }

    /**
     * check whether the sub-string is nice between start and end with character c
     *
     * @param c     character
     * @param start start of sub-string inclusive
     * @param end   end of sub-string exclusive
     * @param map   3-dimension array
     *              first dimension for letters(26)
     *              second dimension for cases(2)
     *              third dimension for index
     * @return true for nice, false for not nice
     */
    boolean isNice(char c, int start, int end, int[][][] map) {
        int charIdx = getCharIdx(c), letter = getLetterIdx(charIdx), caseIdx = getCaseIdx(charIdx) ^ 1, idxs[] = map[letter][caseIdx], size = idxs[SIZE], startIdx, endIdx;
        if ((startIdx = Arrays.binarySearch(idxs, 0, size, start)) >= 0) return true;
        if (startIdx == -size - 1) return false;
        if ((endIdx = Arrays.binarySearch(idxs, 0, size, end - 1)) >= 0) return true;
        return startIdx != endIdx;
    }

    @Test
    public void test() throws NoSuchMethodException {
        TestUtil.testEquals(
                new Object[][]{
                        {"aAa", "YazaAay"},
                        {"bB", "bB"},
                        {"", "c"},
                        {"dD", "dDzeE"}
                });
    }
}
