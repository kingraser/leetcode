/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
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

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(Arrays.asList("a", "c")),
                findLadders("a", "c", Sets.newHashSet("a", "b", "c")));
        Assert.assertEquals(
                Sets.newHashSet(Arrays.asList(Arrays.asList("red", "ted", "tad", "tax"),
                        Arrays.asList("red", "ted", "tex", "tax"), Arrays.asList("red", "rex", "tex", "tax"))),
                Sets.newHashSet(findLadders("red", "tax",
                        Sets.newHashSet("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"))));
    }

    public List<List<String>> findLadders(String begin, String end, Set<String> list) {
        Map<String, List<String>> path = new HashMap<>();
        Set<String> result = bfs(Sets.newHashSet(begin), Sets.newHashSet(end), list, path, new HashSet<>());//generate the ladder 
        return dfs(result, path, end);//find path
    }

    public Set<String> bfs(Set<String> s, Set<String> e, Set<String> l, Map<String, List<String>> map, Set<String> r) {
        if (s.size() > e.size()) return bfs(e, s, l, map, r);//search from both sides
        if (s.isEmpty()) return r;//no path
        l.removeAll(s);
        l.removeAll(e);//insure no circle
        Set<String> next = new HashSet<>();//nodes in next level 
        for (String str : s) {
            char[] head = str.toCharArray();
            for (char i = 0, origin; i < str.length(); head[i++] = origin)
                for (origin = head[i], head[i] = 'a'; head[i] <= 'z'; head[i]++) {
                    String word = new String(head);
                    if (e.contains(word)) log(word, str, r, map);//record the node level information
                    else if (l.contains(word)) log(word, str, next, map);
                }
        }
        return r.isEmpty() ? bfs(next, e, l, map, r) : r;//stop when there is at least a path
    }

    private void log(String word, String parent, Set<String> set, Map<String, List<String>> map) {
        set.add(word);
        map.computeIfAbsent(word, k -> new ArrayList<>()).add(parent);//ancestor list
    }

    private List<List<String>> dfs(Set<String> set, Map<String, List<String>> paths, String endWord) {
        List<List<String>> result = new ArrayList<>();
        if (set.isEmpty()) return result;
        for (String s : set) {
            List<List<String>> lists = dfs(s, paths), head = new ArrayList<>(), end = new ArrayList<>();
            for (List<String> list : lists)
                if (endWord.equals(list.get(0))) end.add(list);
                else head.add(list);
            result.addAll(end.isEmpty() ? head : join(head, end));//generate all possible paths
        }
        return result;
    }

    private List<List<String>> join(List<List<String>> heads, List<List<String>> ends) {
        List<List<String>> result = new ArrayList<>();
        for (List<String> head : heads)
            for (List<String> end : ends)
                result.add(joinString(head, end));
        return result;
    }

    private List<String> joinString(List<String> head, List<String> ends) {
        List<String> result = new ArrayList<>(head);
        for (int i = ends.size() - 2; i > -1; result.add(ends.get(i--)));
        return result;
    }

    private List<List<String>> dfs(String word, Map<String, List<String>> paths) {
        List<List<String>> result = new ArrayList<>();
        if (!paths.containsKey(word)) result.add(Lists.newArrayList(word));
        else for (String parent : paths.get(word)) {
            List<List<String>> lists = dfs(parent, paths);
            lists.forEach(l -> l.add(word));//get head/end parts of the ladder
            result.addAll(lists);
        }
        return result;
    }
}
