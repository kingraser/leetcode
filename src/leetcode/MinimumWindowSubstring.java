/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class MinimumWindowSubstring {
    /*
    Given a string S and a string T, 
    find the minimum window in S which will contain all the characters in T in complexity O(n).
    
    For example,
    S = "ADOBECODEBANC"
    T = "ABC"
    
    Minimum window is "BANC".
    
    Note:
    If there is no such window in S that covers all characters in T, return the empty string "".    
    If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S. 
    */

    public String minWindow(String s, String t) {
        int[] sMap = new int[127], tMap = new int[127];
        for (int i = 0; i < t.length(); tMap[t.charAt(i++)]++);
        int minStart = 0, minLen = Integer.MAX_VALUE;
        for (int start = 0, end = 0, count = 0; end < s.length(); end++) {
            if (tMap[s.charAt(end)] == 0) continue;
            if (++sMap[s.charAt(end)] <= tMap[s.charAt(end)]) count++;
            if (count < t.length()) continue;
            for (char c = s.charAt(start); tMap[c] == 0 || sMap[c] > tMap[c]; sMap[c]--, c = s.charAt(++start));
            if (minLen > (end - start + 1)) {
                minLen = end - start + 1;
                minStart = start;
            }
        }
        if (minLen == Integer.MAX_VALUE) return "";
        return s.substring(minStart, minStart + minLen);
    }

    @Test
    public void test() {
        Assert.assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
    }
}
