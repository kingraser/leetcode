package leetcode;

/**
 * @author Wit
 */
public class ShortestDistancetoaCharacter {
    /*
    Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.

    Example 1:
    Input: S = "loveleetcode", C = 'e'
    Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]

    Note:
    S string length is in [1, 10000].
    C is a single character, and guaranteed to be in string S.
    All letters in S and C are lowercase.
    */
    public int[] shortestToChar(String s, char c) {
        int lastCIdx = -s.length(), result[] = new int[s.length()];
        for (int i = 0; i < s.length(); result[i] = i - lastCIdx, i++)
            if (s.charAt(i) == c) lastCIdx = i;
        for (int i = lastCIdx - 1; i >= 0; result[i] = Math.min(result[i], lastCIdx - i), i--)
            if (s.charAt(i) == c) lastCIdx = i;
        return result;
    }
}
