package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class RemoveAllOccurrencesofaSubstring {
    /*
    Given two strings s and part, perform the following operation on s until all occurrences of the substring part are removed:
    Find the leftmost occurrence of the substring part and remove it from s.
    Return s after removing all occurrences of part.
    A substring is a contiguous sequence of characters in a string.

    Example 1:
    Input: s = "daabcbaabcbc", part = "abc"
    Output: "dab"
    Explanation: The following operations are done:
    - s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
    - s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
    - s = "dababc", remove "abc" starting at index 3, so s = "dab".
    Now s has no occurrences of "abc".

    Example 2:
    Input: s = "axxxxyyyyb", part = "xy"
    Output: "ab"
    Explanation: The following operations are done:
    - s = "axxxxyyyyb", remove "xy" starting at index 4 so s = "axxxyyyb".
    - s = "axxxyyyb", remove "xy" starting at index 3 so s = "axxyyb".
    - s = "axxyyb", remove "xy" starting at index 2 so s = "axyb".
    - s = "axyb", remove "xy" starting at index 1 so s = "ab".
    Now s has no occurrences of "xy".

    Constraints:
    1 <= s.length <= 1000
    1 <= part.length <= 1000
    s and part consists of lowercase English letters.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {"dab", "daabcbaabcbc", "abc"},
                {"ab", "axxxxyyyyb", "xy"},
                {"hijzgaovndkjiiuwjtcpdpbkrfsi",
                        "kpygkivtlqoockpygkivtlqoocssnextkqzjpycbylkaondsskpygkpygkivtlqoocssnextkqzjpkpygkivtlqoocssnextkqzjpycbylkaondsycbylkaondskivtlqoocssnextkqzjpycbylkaondssnextkqzjpycbylkaondshijzgaovndkjiiuwjtcpdpbkrfsi",
                        "kpygkivtlqoocssnextkqzjpycbylkaonds"}
        });
    }

    public String removeOccurrences(String s, String part) {
        char res[] = new char[s.length()], sA[] = s.toCharArray(), pA[] = part.toCharArray();
        int pLen = part.length(), size = 0, idxes[] = new int[s.length() + 1], next[] = getKMPNext(pA);
        for (char c : sA) if ((idxes[size + 1] = getIdx(res[size] = c, idxes[size++], next, pA)) == pLen) size -= pLen;
        return new String(res, 0, size);
    }

    int getIdx(char c, int index, int[] next, char[] chars) {
        while (index != -1 && chars[index] != c) index = next[index];
        return ++index;
    }

    int[] getKMPNext(char[] chars) {
        int next[] = new int[chars.length], left, right, last;
        for (next[0] = -1, left = -1, right = 0, last = chars.length - 1; right < last; )
            if (left >= 0 && chars[left] != chars[right]) left = next[left];
            else if (chars[++right] == chars[++left]) next[right] = next[left];
            else next[right] = left;
        return next;
    }
}
