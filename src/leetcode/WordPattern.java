/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月8日<p>
//-------------------------------------------------------
public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        String[] array = str.split(" ");
        if (array.length != pattern.length()) return false;
        Map<String, Character> map = new HashMap<>(array.length);
        for (int i = 0; i < pattern.length(); i++)
            if (!map.containsKey(array[i])) {
                if (!map.containsValue(pattern.charAt(i))) map.put(array[i], pattern.charAt(i));
                else return false;
            } else if (map.get(array[i]) != pattern.charAt(i)) return false;
        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(wordPattern("abba", "dog cat cat dog"));
        Assert.assertFalse(wordPattern("abba", "dog cat cat fish"));
        Assert.assertFalse(wordPattern("abba", "dog dog dog dog"));
        Assert.assertFalse(wordPattern("aaaa", "dog cat cat dog"));
    }
}
