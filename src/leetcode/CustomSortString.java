package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class CustomSortString {
    /*
    You are given two strings order and s. All the words of order are unique and were sorted in some custom order previously.
    Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.
    Return any permutation of s that satisfies this property.

    Example 1:
    Input: order = "cba", s = "abcd"
    Output: "cbad"
    Explanation:
    "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
    Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.

    Example 2:
    Input: order = "cbafg", s = "abcd"
    Output: "cbad"

    Constraints:
    1 <= order.length <= 26
    1 <= s.length <= 200
    order and s consist of lowercase English letters.
    All the characters of order are unique.
    */
    @Test
    public void test() {
        TestUtil.test(new Object[][]{{"cba", "abcd"}, {"cbafg", "abcd"}},
                this::isSorted);
    }

    private void isSorted(Object inputO, Object actualO) {
        String order = String.valueOf(Array.get(inputO, 0)), s = String.valueOf(actualO);
        int count[] = new int[128], oLen = order.length();
        order.chars().forEach(c -> count[c]++);
        int[] sArray = s.chars().filter(c -> count[c] > 0).toArray();
        for (int oIdx = 0, sIdx = 0; sIdx < sArray.length; sIdx++) {
            while (oIdx < oLen && sArray[sIdx] != order.charAt(oIdx)) oIdx++;
            if (oIdx == oLen) throw new AssertionError(String.format("%s isn't sorted with order %s!", s, order));
        }
    }

    public String customSortString(String order, String s) {
        int[] orderArray = new int[128];
        IntStream.range(0, order.length()).forEach(i -> orderArray[order.charAt(i)] = i);
        return new String(s.chars().boxed().sorted(Comparator.comparingInt(c -> orderArray[c])).mapToInt(i -> i).toArray(), 0, s.length());
    }

    public String customSortStringII(String order, String s) {
        int maxLen = 200, result[] = new int[maxLen], idx = 0, count[] = new int[128], sLen = s.length(), oLen = order.length();
        for (int i = 0; i < sLen; ) count[s.charAt(i++)]++;
        for (int i = 0, c; i < oLen; count[c] = 0) Arrays.fill(result, idx, idx += count[c = order.charAt(i++)], c);
        for (char c = 'a'; c <= 'z'; ) Arrays.fill(result, idx, idx += count[c], c++);
        return new String(result, 0, idx);
    }
}
