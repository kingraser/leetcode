/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

import leetcode.common.Trie;
import leetcode.common.Trie.TrieNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月22日<p>
//-------------------------------------------------------
public class WordSearchII {

  /*
  Given a 2D board and a list of words from the dictionary, find all words in the board.    
  Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
  The same letter cell may not be used more than once in a word.
  
  For example,
  Given words = ["oath","pea","eat","rain"] and board =    
  [
    ['o','a','a','n'],
    ['e','t','a','e'],
    ['i','h','k','r'],
    ['i','f','l','v']
  ]    
  Return ["eat","oath"]. 
  
  Note:
  You may assume that all inputs are consist of lowercase letters a-z.   
  You would need to optimize your backtracking to pass the larger test. 
  Could you stop backtracking earlier?    
  If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
  What kind of data structure could answer such query efficiently? 
  Does a hash table work? Why or why not? How about a Trie? 
  If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.   
  */

  @Test
  public void test() {
    assertEquals(Sets.newHashSet("eat", "oath"),
        new HashSet<>(findWords(
            new char[][] { "oaan".toCharArray(), "etae".toCharArray(), "ihkr".toCharArray(), "iflv".toCharArray() },
            new String[] { "oath", "pea", "eat", "rain" })));
  }

  public List<String> findWords(char[][] board, String[] words) {
    Trie trie = new Trie();
    Arrays.stream(words).forEach(w -> trie.add(w));
    Set<String> res = new HashSet<>();
    for (int i = 0; i < board.length; i++)
      for (int j = 0; j < board[0].length; j++)
        dfs(board, new boolean[board.length][board[0].length], trie.root, i, j, trie, res,
            new char[board.length * board[0].length], 0);
    return new ArrayList<String>(res);
  }

  private void dfs(char[][] board, boolean[][] visited, TrieNode node, int x, int y, Trie trie, Set<String> res,
      char[] s, int idx) {
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) return;
    char c = board[x][y];
    node = node.nexts[c - 'a'];
    if (node == null) return;
    s[idx++] = c;
    visited[x][y] = true;
    if (node.isLeaf) res.add(new String(s, 0, idx));
    dfs(board, visited, node, x - 1, y, trie, res, s, idx);
    dfs(board, visited, node, x + 1, y, trie, res, s, idx);
    dfs(board, visited, node, x, y - 1, trie, res, s, idx);
    dfs(board, visited, node, x, y + 1, trie, res, s, idx);
    visited[x][y] = false;
    idx--;
  }
}
