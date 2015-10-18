/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月18日<p>
//-------------------------------------------------------
public class AddandSearchWordDatastructuredesign {
    /*
    Design a data structure that supports the following two operations:
    
    void addWord(word)
    bool search(word)
    
    search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
    
    For example:
    
    addWord("bad")
    addWord("dad")
    addWord("mad")
    search("pad") -> false
    search("bad") -> true
    search(".ad") -> true
    search("b..") -> true    
    */

    public class TrieNode {

        Character value;

        List<TrieNode> nexts = new ArrayList<>();

        boolean isLeaf = false;

        // Initialize your data structure here.
        public TrieNode() {
            value = null;
        }

        public TrieNode(char c) {
            value = c;
        }
    }

    private TrieNode root = new TrieNode('0');

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode march = root;
        A: for (char c : word.toCharArray()) {
            for (TrieNode node : march.nexts)
                if (node.value == c) {
                    march = node;
                    continue A;
                }
            TrieNode newNode = new TrieNode(c);
            march.nexts.add(newNode);
            march = newNode;
        }
        march.isLeaf = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return search(word.toCharArray(), root, 0);
    }

    public boolean search(char[] word, TrieNode node, int idx) {
        if (word == null || word.length == 0) return node.isLeaf;
        char c = word[idx];
        if (c == '.') {//深度优先搜索
            for (TrieNode tNode : node.nexts)
                if (search(word, tNode, 1 + idx)) return true;
        } else for (TrieNode tNode : node.nexts)
            if (c == tNode.value) return search(word, tNode, 1 + idx);
        return false;
    }
}
