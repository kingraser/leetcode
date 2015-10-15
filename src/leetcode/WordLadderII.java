/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月15日<p>
//-------------------------------------------------------
public class WordLadderII {
    /*
    Given two words (beginWord and endWord), and a dictionary's word list, 
    find all shortest transformation sequence(s) from beginWord to endWord, such that:
    
    Only one letter can be changed at a time
    Each intermediate word must exist in the word list
    
    For example,
    
    Given:
    beginWord = "hit" endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]
    
    Return    
      [
        ["hit","hot","dot","dog","cog"],
        ["hit","hot","lot","log","cog"]
      ]
    */

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        List<List<String>> expected = Lists.newArrayList(Lists.newArrayList("red", "ted", "tad", "tax"),
                Lists.newArrayList("red", "ted", "tex", "tax"), Lists.newArrayList("red", "rex", "tex", "tax"));
        Assert.assertEquals(expected,
                findLadders("red", "tax", Sets.newHashSet("ted", "tex", "red", "tax", "tad", "den", "rex", "pee")));
    }

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<Node> result = Lists.newLinkedList();
        Set<String> visited = Sets.newHashSet(start);
        LinkedList<Node> ladder = Lists.newLinkedList();
        ladder.add(new Node(start));
        for (boolean notFound = true; !ladder.isEmpty() && notFound;) {
            Map<String, Node> levelMap = Maps.newHashMap();
            for (int size = ladder.size(); size-- > 0;) {
                Node parent = ladder.pollFirst();
                char[] head = parent.val.toCharArray();
                if (isOneChardiff(head, end)) {
                    notFound = false;
                    result.add(parent);
                }
                for (char i = 0; notFound && i < head.length; i++) {
                    char origin = head[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (origin == j) continue;
                        head[i] = j;
                        String s = new String(head);
                        if (dict.contains(s) && !visited.contains(s)) {
                            if (levelMap.containsKey(s)) levelMap.get(s).parents.add(parent);
                            else {
                                Node node = new Node(s);
                                node.parents.add(parent);
                                levelMap.put(s, node);
                                ladder.addLast(node);
                            }
                        }
                    }
                    head[i] = origin;
                }
            }
            visited.addAll(levelMap.keySet());
        }
        List<List<String>> answer = result.isEmpty() ? new LinkedList<>() : getLadder(result);
        for (List<String> list : answer)
            list.add(end);
        return answer;
    }

    boolean isOneChardiff(char[] array, String s) {
        int diff = 0;
        for (int i = 0; i < array.length && diff < 2; i++)
            if (array[i] != s.charAt(i)) diff++;
        return diff == 1;
    }

    private List<List<String>> getLadder(List<Node> nodes) {
        List<List<String>> result = new LinkedList<>();
        if (nodes.isEmpty()) result.add(new LinkedList<>());
        for (Node node : nodes) {
            List<List<String>> lists = getLadder(node.parents);
            for (List<String> list : lists)
                list.add(node.val);
            result.addAll(lists);
        }
        return result;
    }

    public class Node {

        String val;

        List<Node> parents = new LinkedList<>();

        Node(String s) {
            val = s;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof Node)) return false;
            Node other = (Node) o;
            return val.equals(other.val);
        }

        @Override
        public int hashCode() {
            return val.hashCode();
        }
    }
}
