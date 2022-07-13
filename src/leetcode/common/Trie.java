package leetcode.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class Trie {
	public TrieNode root = new TrieNode(), march;

	public void add(String word) {
		march = root;
		word.chars().forEach(c -> march = Objects.isNull(march.nexts[c -= 'a']) ? march.nexts[c] = new TrieNode() : march.nexts[c]);
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
		if (word[idx] != '.')
			return Objects.isNull(node.nexts[word[idx] -= 'a']) ? null : search(word, node.nexts[word[idx]], idx + 1);
		for (TrieNode next : node.nexts)
			if (Objects.nonNull(next) && Objects.nonNull(next = search(word, next, idx + 1))) return next;
		return null;
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
		assertEquals(List.of("bad"), trie.getWithPrefix("ba"));
	}

	public static class TrieNode {
		public TrieNode[] nexts = new TrieNode[26];
		public boolean isLeaf = false;
	}

	public String getShortestPrefix(String s) {
		int prefix = getShortestPrefix(s.toCharArray(), root, 0);
		return prefix == 0 ? s : s.substring(0, prefix);
	}

	private int getShortestPrefix(char[] word, TrieNode node, int idx) {
		return node.isLeaf ? idx :
				idx == word.length || Objects.isNull(node.nexts[word[idx] -= 'a'])
						? 0
						: getShortestPrefix(word, node.nexts[word[idx]], idx + 1);
	}
}
