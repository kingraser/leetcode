/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class ImplementTriePrefixTree {
    /*
    Implement a trie with insert, search, and startsWith methods.    
    Note:
    You may assume that all inputs are consist of lowercase letters a-z. 
    */

    class TrieNode {

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

    public class Trie {

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode march = root;
            A: for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                for (int j = 0; j < march.nexts.size(); j++)
                    if (march.nexts.get(j).value == c) {
                        march = march.nexts.get(j);
                        continue A;
                    }
                TrieNode newNode = new TrieNode(c);
                march.nexts.add(newNode);
                march = newNode;
            }
            march.isLeaf = true;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode march = root;
            A: for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                for (int j = 0; j < march.nexts.size(); j++)
                    if (march.nexts.get(j).value == c) {
                        march = march.nexts.get(j);
                        continue A;
                    }
                return false;
            }
            return march.isLeaf;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode march = root;
            A: for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                for (int j = 0; j < march.nexts.size(); j++)
                    if (march.nexts.get(j).value == c) {
                        march = march.nexts.get(j);
                        continue A;
                    }
                return false;
            }
            return true;
        }
    }
}
