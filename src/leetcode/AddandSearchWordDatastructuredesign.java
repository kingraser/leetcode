/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月18日<p>
//-------------------------------------------------------
public class AddandSearchWordDatastructuredesign {
    /*
    Design a data structure that supports the following two operations:
    
    void addWord(word)
    bool search(word)
    
    search(word) can search a literal word or a regular expression string containing only letters a-z or .. 
    A . means it can represent any one letter.
    
    For example:
    
    addWord("bad")
    addWord("dad")
    addWord("mad")
    search("pad") -> false
    search("bad") -> true
    search(".ad") -> true
    search("b..") -> true    
    */

    @Test
    public void test() {
        AddandSearchWordDatastructuredesign trie = new AddandSearchWordDatastructuredesign();
        trie.addWord("bad");
        trie.addWord("dad");
        trie.addWord("mad");
        Assert.assertFalse(trie.search("pad"));
        Assert.assertTrue(trie.search("bad"));
        Assert.assertTrue(trie.search(".ad"));
        Assert.assertTrue(trie.search("b.."));
    }

    public class TrieNode {

        TrieNode[] nexts = new TrieNode[26];

        boolean isLeaf = false;

    }

    private TrieNode root = new TrieNode();

    public void addWord(String word) {
        TrieNode march = root;
        for (char c : word.toCharArray()) {
            if (Objects.isNull(march.nexts[c -= 'a'])) march.nexts[c] = new TrieNode();
            march = march.nexts[c];
        }
        march.isLeaf = true;
    }

    public boolean search(String word) {
        return search(word.toCharArray(), root, 0);
    }

    public boolean search(char[] word, TrieNode node, int idx) {
        if (idx == word.length) return node.isLeaf;
        if (word[idx] == '.') for (TrieNode tNode : node.nexts)
            if (Objects.nonNull(tNode) && search(word, tNode, 1 + idx)) return true;
        if (Objects.nonNull(node.nexts[word[idx] -= 'a'])) return search(word, node.nexts[word[idx]], 1 + idx);
        return false;
    }

}
