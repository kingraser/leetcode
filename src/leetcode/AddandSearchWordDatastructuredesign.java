/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

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

    public class TrieNode {

        Character value;

        TrieNode[] nexts = new TrieNode[26];

        boolean isLeaf = false;

        public TrieNode(char c) {
            value = c;
        }
    }

    private TrieNode root = new TrieNode('0');

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode march = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            TrieNode next = march.nexts[idx];
            if (next != null) {
                march = next;
                continue;
            }
            TrieNode newNode = new TrieNode(c);
            march.nexts[idx] = newNode;
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
        if (idx == word.length) return node.isLeaf;
        char c = word[idx];
        if (c == '.') {//深度优先搜索
            for (TrieNode tNode : node.nexts)
                if (tNode != null && search(word, tNode, 1 + idx)) return true;
        } else {
            int i = c - 'a';
            if (node.nexts[i] != null) return search(word, node.nexts[i], 1 + idx);
        }
        return false;
    }
}
