/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月15日<p>
//-------------------------------------------------------
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
        Assert.assertEquals(3,
                ladderLength("hot", "dog", Sets.newHashSet("hot", "cog", "dog", "tot", "hog", "hop", "pot", "dot")));
        Assert.assertEquals(5, ladderLength("hit", "cog", Sets.newHashSet("hot", "dot", "dog", "lot", "log")));
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        return solve(Sets.newHashSet(beginWord), Sets.newHashSet(endWord), wordList, 1);
    }

    public int solve(Set<String> start, Set<String> end, Set<String> wordList, int level) {
        if (start.isEmpty()) return 0;
        if (start.size() > end.size()) return solve(end, start, wordList, level);
        wordList.removeAll(start);
        wordList.removeAll(end);
        Set<String> set = new HashSet<>();
        for (String s : start) {
            char[] head = s.toCharArray();
            for (char i = 0, origin, j; i < s.length(); head[i++] = origin)
                for (origin = head[i], j = 'a'; j <= 'z'; j++) {
                    if (j == origin) continue;
                    head[i] = j;
                    String word = new String(head);
                    if (end.contains(word)) return ++level;
                    if (wordList.contains(word)) set.add(word);
                }
        }
        return solve(set, end, wordList, level + 1);
    }

}
