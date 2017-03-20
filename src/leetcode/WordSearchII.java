package leetcode;

import static leetcode.BattleshipsinaBoard.DIRS;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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

  public List<String> findWords(char[][] board, String[] words) {
    Trie trie = new Trie();
    Arrays.stream(words).forEach(word -> trie.add(word));
    Set<String> result = new HashSet<>();
    for (int row = 0, rowCount = board.length; row < rowCount; row++)
      for (int col = 0, colCount = board[0].length, cs[] = new int[rowCount * colCount]; col < colCount; col++)
        dfs(board, new BitSet(), trie.root, row, col, result, cs, 0);
    return new ArrayList<String>(result);
  }

  private void dfs(char[][] board, BitSet visited, TrieNode node, int row, int col, Set<String> result, int[] s,
      int idx) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited.get(row * board[0].length + col)
        || Objects.isNull(node = node.nexts[board[row][col] - 'a']))
      return;
    s[idx++] = board[row][col];
    if (node.isLeaf) result.add(new String(s, 0, idx));
    visited.set(row * board[0].length + col);
    for (int[] dir : DIRS)
      dfs(board, visited, node, row + dir[0], col + dir[1], result, s, idx);
    visited.clear(row * board[0].length + col);
  }
}
