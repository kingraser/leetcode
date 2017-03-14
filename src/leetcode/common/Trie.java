package leetcode.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import org.junit.Test;

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
    return Objects.nonNull(march = search(word.toCharArray(), root, 0)) && march.isLeaf;
  }

  public boolean startsWith(String word) {
    return Objects.nonNull(search(word.toCharArray(), root, 0));
  }

  private TrieNode search(char[] word, TrieNode node, int idx) {
    if (idx == word.length) return node;
    if (word[idx] == '.') {
      for (TrieNode next : node.nexts)
        if (Objects.nonNull(next) && Objects.nonNull(next = search(word, next, idx + 1))) return next;
      return null;
    }
    return Objects.isNull(node.nexts[word[idx] -= 'a']) ? null : search(word, node.nexts[word[idx]], idx + 1);
  }

  public List<String> getWithPrefix(String prefix) {
    List<String> result = new ArrayList<>();
    dfs(result, search(prefix.toCharArray(), root, 0), prefix);
    return result;
  }

  private void dfs(List<String> result, TrieNode node, String prefix) {
    if (Objects.isNull(node)) return;
    if (node.isLeaf) {
      result.add(prefix);
      return;
    }
    IntStream.range(0, 26).forEach(i -> dfs(result, node.nexts[i], prefix + (char) (i + 'a')));
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
    assertEquals(Arrays.asList("bad"), trie.getWithPrefix("ba"));
  }

  public static class TrieNode {
    public TrieNode[] nexts = new TrieNode[26];
    public boolean isLeaf = false;
  }
}
