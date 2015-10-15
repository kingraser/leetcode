/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
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
        Assert.assertEquals(5, ladderLength("hit", "cog", Sets.newHashSet("hot", "dot", "dog", "lot", "log")));
    }

    public int ladderLength(String start, String end, Set<String> dict) {
        Set<String> visited = Sets.newHashSet(start);
        LinkedList<String> ladder = Lists.newLinkedList(visited);
        for (int level = 1; !ladder.isEmpty(); level++)
            for (int size = ladder.size(); size-- > 0;) {
                char[] head = ladder.pollFirst().toCharArray();
                for (char i = 0; i < head.length; i++) {
                    char origin = head[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (origin == j) continue;
                        head[i] = j;
                        String s = new String(head);
                        if (s.equals(end)) return ++level;
                        if (dict.contains(s) && !visited.contains(s)) {
                            ladder.addLast(s);
                            visited.add(s);
                        }
                    }
                    head[i] = origin;
                }
            }
        return 0;
    }

}
