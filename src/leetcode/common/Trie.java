/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Objects;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年5月25日;
//-------------------------------------------------------
public class Trie {
  public TrieNode root = new TrieNode(), march;

  public void add(String word) {
    march = root;
    word.chars().forEach(c -> {
      if (Objects.isNull(march.nexts[c -= 'a'])) march.nexts[c] = new TrieNode();
      march = march.nexts[c];
    });
    march.isLeaf = true;
  }

  public boolean search(String word) {
    return search(word.toCharArray(), root, 0, true);
  }

  public boolean startsWith(String word) {
    return search(word.toCharArray(), root, 0, false);
  }

  private boolean search(char[] word, TrieNode node, int idx, boolean isLeaf) {
    if (idx == word.length) return isLeaf ? node.isLeaf : true;
    if (word[idx] == '.'
        && Arrays.stream(node.nexts).filter(Objects::nonNull).anyMatch(next -> search(word, next, 1 + idx, isLeaf)))
      return true;
    return Objects.isNull(node.nexts[word[idx] -= 'a']) ? false : search(word, node.nexts[word[idx]], 1 + idx, isLeaf);
  }

  @Test
  public void test() {
    Trie trie = new Trie();
    trie.add("bad");
    trie.add("dad");
    trie.add("mad");
    assertFalse(trie.search("pad"));
    assertTrue(trie.search("bad"));
    assertTrue(trie.search(".ad"));
    assertTrue(trie.search("b.."));
  }

  public static class TrieNode {
    public TrieNode[] nexts = new TrieNode[26];
    public boolean isLeaf = false;
  }
}
