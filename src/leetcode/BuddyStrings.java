package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class BuddyStrings {
    /*
    Given two strings A and B of lowercase letters, return true if you can swap two letters in A so the result is equal to B, otherwise, return false.
    Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at A[i] and A[j]. For example, swapping at indices 0 and 2 in "abcd" results in "cbad".

    Example 1:
    Input: A = "ab", B = "ba"
    Output: true
    Explanation: You can swap A[0] = 'a' and A[1] = 'b' to get "ba", which is equal to B.

    Example 2:
    Input: A = "ab", B = "ab"
    Output: false
    Explanation: The only letters you can swap are A[0] = 'a' and A[1] = 'b', which results in "ba" != B.

    Example 3:
    Input: A = "aa", B = "aa"
    Output: true
    Explanation: You can swap A[0] = 'a' and A[1] = 'a' to get "aa", which is equal to B.

    Example 4:
    Input: A = "aaaaaaabc", B = "aaaaaaacb"
    Output: true

    Example 5:
    Input: A = "", B = "aa"
    Output: false

    Constraints:
    0 <= A.length <= 20000
    0 <= B.length <= 20000
    A and B consist of lowercase letters.
    */

    public boolean buddyStrings(String a, String b) {
        if (a.length() != b.length()) return false;
        if (a.equals(b)) {
            int[] count = new int[26];
            for (int i = 0; i < a.length(); i++)
                if (++count[a.charAt(i) - 'a'] > 1) return true;
            return false;
        }
        int first = -1, second = -1;
        for (int i = 0; i < a.length(); i++)
            if (a.charAt(i) == b.charAt(i)) continue;
            else if (first == -1) first = i;
            else if (second == -1) second = i;
            else return false;
        return second != -1 && a.charAt(first) == b.charAt(second) && a.charAt(second) == b.charAt(first);
    }

    @Test
    public void test() {
        Assert.assertTrue(buddyStrings("ab", "ba"));
        Assert.assertFalse(buddyStrings("ab", "ab"));
        Assert.assertTrue(buddyStrings("aa", "aa"));
        Assert.assertTrue(buddyStrings("aaaaaaabc", "aaaaaaacb"));
        Assert.assertFalse(buddyStrings("", "aa"));
    }
}
