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

  int[][] dirs = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

  public List<String> findWords(char[][] board, String[] words) {
    Trie trie = new Trie();
    Arrays.stream(words).forEach(word -> trie.add(word));
    Set<String> result = new HashSet<>();
    for (int row = 0; row < board.length; row++)
      for (int col = 0; col < board[0].length; col++)
        dfs(board, new boolean[board.length][board[0].length], trie.root, row, col, trie, result,
            new char[board.length * board[0].length], 0);
    return new ArrayList<String>(result);
  }

  private void dfs(char[][] board, boolean[][] visited, TrieNode node, int row, int col, Trie trie, Set<String> result,
      char[] s, int idx) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]
        || (node = node.nexts[board[row][col] - 'a']) == null)
      return;
    s[idx++] = board[row][col];
    if (node.isLeaf) result.add(new String(s, 0, idx));
    visited[row][col] = true;
    for (int[] dir : dirs)
      dfs(board, visited, node, row + dir[0], col + dir[1], trie, result, s, idx);
    visited[row][col] = false;
  }
}
