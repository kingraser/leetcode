/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class GroupAnagrams {
    /*
    Given an array of strings, group anagrams together.
    
    For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
    Return:
    
    [
      ["ate", "eat","tea"],
      ["nat","tan"],
      ["bat"]
    ]
    
    Note:
    
    For the return value, each inner list's elements must follow the lexicographic order.
    All inputs will be in lower-case.    
    */

    private static final int[] PRIMES = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61,
            67, 71, 73, 79, 83, 89, 97, 101, 107 };

    public List<List<String>> anagrams(String[] strs) {
        List<List<String>> list = Lists.newArrayList();
        Map<Long, List<String>> map = Maps.newHashMap();
        for (String s : strs) {
            long hashCode = getHashCode(s);
            if (!map.containsKey(hashCode)) map.put(hashCode, Lists.newArrayList());
            map.get(hashCode).add(s);
        }
        for (Entry<Long, List<String>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
            list.add(entry.getValue());
        }
        return list;
    }

    private long getHashCode(String s) {
        long hashCode = 1;
        for (char c : s.toCharArray())
            hashCode *= PRIMES[c - 'a'];
        return hashCode;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        List<List<String>> expected = Lists.newArrayList(Lists.newArrayList("ate", "eat", "tea"),
                Lists.newArrayList("nat", "tan"), Lists.newArrayList("bat"));
        Assert.assertEquals(expected, anagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }));
    }
}
