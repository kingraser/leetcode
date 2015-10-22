/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

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
        char[][] board = new char[][] { "oaan".toCharArray(), "etae".toCharArray(), "ihkr".toCharArray(),
                "iflv".toCharArray() };
        Set<String> expected = Sets.newHashSet("eat", "oath"),
                actual = Sets.newHashSet(findWords(board, new String[] { "oath", "pea", "eat", "rain" }));
        Assert.assertEquals(expected, actual);
    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words)
            trie.insert(word);
        Set<String> res = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        char[] s = new char[board.length * board[0].length];
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                dfs(board, visited, trie.root, i, j, trie, res, s, 0);
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

    class TrieNode {

        TrieNode[] nexts = new TrieNode[26];

        boolean isLeaf = false;

    }

    class Trie {

        TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode march = root;
            for (int i = 0, idx; i < word.length(); march = march.nexts[idx]) {
                idx = word.charAt(i++) - 'a';
                if (march.nexts[idx] == null) march.nexts[idx] = new TrieNode();
            }
            march.isLeaf = true;
        }
    }

}
