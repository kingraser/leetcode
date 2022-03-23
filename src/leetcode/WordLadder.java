package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordLadder {

    /*
    Given two words (beginWord and endWord), and a dictionary's word list,
    find the length of shortest transformation sequence from beginWord to endWord, such that:
    Only one letter can be changed at a time
    Each intermediate word must exist in the word list

    For example,
    Given:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]

    As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
    return its length 5.

    Note:

    Return 0 if there is no such transformation sequence.
    All words have the same length.
    All words contain only lowercase alphabetic characters.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {3, "hot", "dog", Stream.of("hot", "cog", "dog", "tot", "hog", "hop", "pot", "dot").collect(Collectors.toSet())},
                {5, "hit", "cog", Stream.of("hot", "dot", "dog", "lot", "log").collect(Collectors.toSet())}
        });
    }

    public int ladderLength(String start, String end, Set<String> set) {
        return Objects.equals(start, end) ? 0
                : solve(new HashSet<>() {{add(start);}}, new HashSet<>() {{add(end);}}, set, 1);
    }

    int solve(Set<String> start, Set<String> end, Set<String> dict, int level) {
        if (start.size() > end.size()) return solve(end, start, dict, level);
        Stream.of(start, end).forEach(dict::removeAll);
        Set<String> newStarts = new HashSet<>();
        for (String s : start) {
            char[] chars = s.toCharArray();
            for (char i = 0, origin; i < s.length(); chars[i++] = origin)
                for (origin = chars[i], chars[i] = 'a'; chars[i] <= 'z'; chars[i]++) {
                    if (chars[i] == origin) continue;
                    String word = new String(chars);
                    if (end.contains(word)) return ++level;
                    if (dict.contains(word)) newStarts.add(word);
                }
        }
        return newStarts.isEmpty() ? 0 : solve(newStarts, end, dict, ++level);
    }

    public static class WordLadderNewVersion {
        /*
        A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

        Every adjacent pair of words differs by a single letter.
        Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
        sk == endWord
        Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

        Example 1:
        Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        Output: 5
        Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

        Example 2:
        Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
        Output: 0
        Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

        Constraints:
        1 <= beginWord.length <= 10
        endWord.length == beginWord.length
        1 <= wordList.length <= 5000
        wordList[i].length == beginWord.length
        beginWord, endWord, and wordList[i] consist of lowercase English letters.
        beginWord != endWord
        All the words in wordList are unique.
        */

        @Test
        public void test() {
            TestUtil.testEquals(new Object[][]{
                    {3, "hot", "dog", List.of("hot", "cog", "dog", "tot", "hog", "hop", "pot", "dot")},
                    {5, "hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")},
                    {0, "hit", "cog", List.of("hot", "dot", "dog", "lot", "log")}
            });
        }

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            long begin = code(beginWord), end = code(endWord);
            Set<Long> words = new HashSet<>(wordList.size() << 1);
            for (String s : wordList) words.add(code(s));
            if (!words.remove(end)) return 0;
            words.remove(begin);
            return ladderLength(new HashSet<>(wordList.size() << 1) {{add(begin);}},
                    new HashSet<>(wordList.size() << 1) {{add(end);}}, words, beginWord.length());
        }

        int ladderLength(Set<Long> starts, Set<Long> ends, Set<Long> words, int wordSize) {
            for (int step = 1; !starts.isEmpty() && !ends.isEmpty(); step++) {
                Set<Long> newStarts = new HashSet<>(words.size() << 1);
                for (long start : starts)
                    for (long i = wordSize, pow = 1L, flag = 31L; i-- > 0; pow <<= 5, flag <<= 5)
                        for (long j = 26, value = ((~0) ^ flag) & start; j-- > 0; value += pow)
                            if (value == start) continue;
                            else if (ends.contains(value)) return ++step;
                            else if (words.remove(value)) newStarts.add(value);
                if ((starts = newStarts).size() > ends.size()) {
                    Set<Long> temp = starts;
                    starts = ends;
                    ends = temp;
                }
            }
            return 0;
        }

        long code(String s) {
            long result = 0;
            for (char c : s.toCharArray()) {
                result <<= 5;
                result |= c - 'a';
            }
            return result;
        }
    }
}
