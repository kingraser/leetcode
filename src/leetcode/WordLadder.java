package leetcode;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

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
        Set<String> dict = Sets.newHashSet("hot", "cog", "dog", "tot", "hog", "hop", "pot", "dot");
        assertEquals(3, ladderLength("hot", "dog", dict));
        dict = Sets.newHashSet("hot", "dot", "dog", "lot", "log");
        assertEquals(5, ladderLength("hit", "cog", dict));
    }

    public int ladderLength(String start, String end, Set<String> set) {
        return Objects.equals(start, end) ? 0
                : solve(new HashSet<>() {{add(start);}}, new HashSet<>() {{add(end);}}, set, 1);
    }

    public int solve(Set<String> start, Set<String> end, Set<String> dict, int level) {
        if (start.size() > end.size()) return solve(end, start, dict, level);
        Stream.of(start, end).forEach(dict::removeAll);
        Set<String> nexts = new HashSet<>();
        for (String s : start) {
            char[] chars = s.toCharArray();
            for (char i = 0, origin; i < s.length(); chars[i++] = origin)
                for (origin = chars[i], chars[i] = 'a'; chars[i] <= 'z'; chars[i]++) {
                    if (chars[i] == origin) continue;
                    String word = new String(chars);
                    if (end.contains(word)) return ++level;
                    if (dict.contains(word)) nexts.add(word);
                }
        }
        return nexts.isEmpty() ? 0 : solve(nexts, end, dict, ++level);
    }

}
