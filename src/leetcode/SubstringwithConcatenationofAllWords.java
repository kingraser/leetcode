/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class SubstringwithConcatenationofAllWords {

    /*
    You are given a string, S, and a list of words, L, that are all of the same length. 
    Find all starting indices of substring(s) in S 
    that is a concatenation of each word in L exactly once and without any intervening characters.
    For example, given:
    S: "barfoothefoobarman"
    L: ["foo", "bar"]
    You should return the indices: [0,9].(order does not matter).
    */

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = Lists.newArrayList();
        if (words == null || words.length == 0 || words[0].length() * words.length > s.length()) return result;
        Map<String, Integer> dictionary = Maps.newHashMapWithExpectedSize(words.length),
                current = Maps.newHashMapWithExpectedSize(words.length);
        int size = words[0].length(), len = size * words.length;
        for (int i = 0; i < words.length; i++)
            dictionary.put(words[i], dictionary.containsKey(words[i]) ? dictionary.get(words[i]) + 1 : 1);
        A: for (int i = 0, end = s.length() - len + 1; i < end; i++) {
            current.clear();
            for (int j = 0; j < words.length; j++) {
                String word = s.substring(i + j * size, i + (j + 1) * size);
                if (!dictionary.containsKey(word)
                        || (current.containsKey(word) && current.get(word) + 1 > dictionary.get(word)))
                    continue A;
                current.put(word, current.containsKey(word) ? current.get(word) + 1 : 1);
            }
            if (dictionary.equals(current)) result.add(i);
        }
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList(0, 9),
                findSubstring("barfoothefoobarman", new String[] { "foo", "bar" }));
    }
}
