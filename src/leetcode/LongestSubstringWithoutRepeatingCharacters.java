/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

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
        int max = 0, len = 0;
        int[] map = new int[128];
        for (int i = 0, first = 0, c; i < s.length(); map[c] = ++i)
            if (map[c = s.charAt(i)] != 0) {
                if (max < len) max = len;
                if (first < map[c]) first = map[c];
                len = i - first + 1;
            } else len++;
        return max < len ? len : max;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, lengthOfLongestSubstring("abba"));
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbbbb"));
    }

}
