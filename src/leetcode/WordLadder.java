/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashSet;
import java.util.Set;

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
    Set<String> dict = Sets.newHashSet("hot", "cog", "dog", "tot", "hog", "hop", "pot", "dot");
    assertEquals(3, ladderLength("hot", "dog", dict));
    dict = Sets.newHashSet("hot", "dot", "dog", "lot", "log");
    assertEquals(5, ladderLength("hit", "cog", dict));
  }

  public int ladderLength(String start, String end, Set<String> set) {
    return solve(Sets.newHashSet(start), Sets.newHashSet(end), set, 1);
  }

  public int solve(Set<String> start, Set<String> end, Set<String> dict, int level) {
    if (start.isEmpty()) return 0;
    if (start.size() > end.size()) return solve(end, start, dict, level);
    dict.removeAll(start);
    dict.removeAll(end);
    Set<String> set = new HashSet<>();
    for (String s : start) {
      char[] head = s.toCharArray();
      for (char i = 0, origin; i < s.length(); head[i++] = origin)
        for (origin = head[i], head[i] = 'a'; head[i] <= 'z'; head[i]++) {
          if (head[i] == origin) continue;
          String word = new String(head);
          if (end.contains(word)) return ++level;
          if (dict.contains(word)) set.add(word);
        }
    }
    return solve(set, end, dict, ++level);
  }

}
