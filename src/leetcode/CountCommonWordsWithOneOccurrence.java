package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class CountCommonWordsWithOneOccurrence {
    /*
    Given two string arrays words1 and words2, return the number of strings that appear exactly once in each of the two arrays.

    Example 1:
    Input: words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
    Output: 2
    Explanation:
    - "leetcode" appears exactly once in each of the two arrays. We count this string.
    - "amazing" appears exactly once in each of the two arrays. We count this string.
    - "is" appears in each of the two arrays, but there are 2 occurrences of it in words1. We do not count this string.
    - "as" appears once in words1, but does not appear in words2. We do not count this string.
    Thus, there are 2 strings that appear exactly once in each of the two arrays.

    Example 2:
    Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
    Output: 0
    Explanation: There are no strings that appear in each of the two arrays.

    Example 3:
    Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
    Output: 1
    Explanation: The only string that appears exactly once in each of the two arrays is "ab".

    Constraints:
    1 <= words1.length, words2.length <= 1000
    1 <= words1[i].length, words2[j].length <= 30
    words1[i] and words2[j] consists only of lowercase English letters.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {2, new String[]{"leetcode", "is", "amazing", "as", "is"}, new String[]{"amazing", "leetcode", "is"}},
                {0, new String[]{"b", "bb", "bbb"}, new String[]{"a", "aa", "aaa"}},
                {1, new String[]{"a", "ab"}, new String[]{"a", "a", "a", "ab"}}});
    }

    public int countWords(String[] words1, String[] words2) {
        int result = 0;
        Integer value;
        Map<String, Integer> map = new HashMap<>(words1.length << 1);
        for (String s : words1) map.merge(s, 1, Integer::sum);
        for (String s : words2)
            if ((value = map.get(s)) == null) continue;
            else if (value == 1) {
                map.put(s, 0);
                result++;
            } else if (value == 0) {
                map.put(s, -1);
                result--;
            }
        return result;
    }
}
