package leetcode;

/**
 * @author Wit
 */
public class CompareStringsbyFrequencyoftheSmallestCharacter {
    /*
    Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.
    Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.

    Example 1:
    Input: queries = ["cbd"], words = ["zaaaz"]
    Output: [1]
    Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").

    Example 2:
    Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
    Output: [1,2]
    Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").

    Constraints:
    1 <= queries.length <= 2000
    1 <= words.length <= 2000
    1 <= queries[i].length, words[i].length <= 10
    queries[i][j], words[i][j] are English lowercase letters.
    */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] res = new int[queries.length], count = new int[11];
        for (String word : words) count[getCount(word)]++;
        for (int i = 1, len = count.length; i < len; i++) count[i] += count[i - 1];
        for (int i = 0; i < queries.length; i++) res[i] = count[count.length - 1] - count[getCount(queries[i])];
        return res;
    }

    public int getCount(String word) {
        int sCount = 0;
        for (int i = 0, s = 'z', c; i < word.length(); i++)
            if ((c = word.charAt(i)) < s) {
                s = c;
                sCount = 1;
            } else if (c == s) sCount++;
        return sCount;
    }
}
