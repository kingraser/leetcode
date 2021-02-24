package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class VerifyingAnAlienDictionary {
    /*
    In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
    Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

    Example 1:
    Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
    Output: true
    Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.

    Example 2:
    Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
    Output: false
    Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.

    Example 3:
    Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
    Output: false
    Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).

    Constraints:
    1 <= words.length <= 100
    1 <= words[i].length <= 20
    order.length == 26
    All characters in words[i] and order are English lowercase letters.
    */

    public boolean isAlienSorted(String[] words, String order) {
        int[] orders = new int[26];
        IntStream.range(0, order.length()).forEach(i -> orders[order.charAt(i) - 'a'] = i);
        for (int i = 1; i < words.length; i++)
            if (compare(words[i - 1], words[i], orders) > 0) return false;
        return true;
    }

    int compare(String a, String b, int[] orders) {
        for (int i = 0, length = Math.min(a.length(), b.length()), compare; i < length; )
            if ((compare = Integer.compare(orders[a.charAt(i) - 'a'], orders[b.charAt(i++) - 'a'])) != 0)
                return compare;
        return Integer.compare(a.length(), b.length());
    }

    @Test
    public void test() {
        Assert.assertTrue(isAlienSorted(new String[]{"hello", "leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));
        Assert.assertFalse(isAlienSorted(new String[]{"word", "world", "row"}, "worldabcefghijkmnpqstuvxyz"));
        Assert.assertFalse(isAlienSorted(new String[]{"apple", "app"}, "abcdefghijklmnopqrstuvwxyz"));
    }
}
