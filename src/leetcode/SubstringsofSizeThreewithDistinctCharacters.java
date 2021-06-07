package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class SubstringsofSizeThreewithDistinctCharacters {
    /*
    A string is good if there are no repeated characters.
    Given a string s, return the number of good substrings of length three in s.
    Note that if there are multiple occurrences of the same substring, every occurrence should be counted.
    A substring is a contiguous sequence of characters in a string.
    
    Example 1:
    Input: s = "xyzzaz"
    Output: 1
    Explanation: There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz". 
    The only good substring of length 3 is "xyz".
    
    Example 2:
    Input: s = "aababcabc"
    Output: 4
    Explanation: There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
    The good substrings are "abc", "bca", "cab", and "abc".
    
    Constraints:
    1 <= s.length <= 100
    s consists of lowercase English letters.
    */
    public int countGoodSubstrings(String s) {
        return countGoodSubstrings(s, 3);
    }

    public int countGoodSubstrings(String s, int size) {
        int res = 0;
        for (int i = 0, repeat = 0, len = s.length(), count[] = new int[123]; i < len; ) {
            if (count[s.charAt(i)]++ == 1) repeat++;
            if (i >= size && --count[s.charAt(i - size)] == 1) repeat--;
            if (++i >= size && repeat == 0) res++;
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(1, countGoodSubstrings("xyzzaz"));
        Assert.assertEquals(4, countGoodSubstrings("aababcabc"));
    }
}
