/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class LongestSubstringWithoutRepeatingCharacters {

    /*
    如题
    abcabcbb is "abc", which the length is 3.
    bbbbb the longest substring is "b", with the length of 1.
    
    思路:维护一个窗口,窗口内无重复字母+水位法.
    窗口围护方法
        无重复,后指针向后.
        有重复,前指针向后,至无重复为止
     */

    public int lengthOfLongestSubstring(String s) {
        int first = 0, size = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) first = Math.max(first, map.get(s.charAt(i)) + 1);
            map.put(s.charAt(i), i);
            size = Math.max(size, i - first + 1);
        }
        return size;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));
    }

}
