/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class ImplementstrStr {

    /*
    1作弊法
    2BF(brute force) O(mn)
    3KMP
    4BM(Boyer-Moore)
    5Rabin-Karp()
    */

    public int strStr(String l, String s) {//l for long; s for short
        return null == l || null == s ? -1 : l.indexOf(s);
    }

    public int strStrII(String l, String s) {
        if (s != null && l != null && l.length() > 0) A: for (int i = 0; i <= l.length() - s.length(); i++) {
            for (int j = 0; j < s.length(); j++)
                if (l.charAt(i + j) != s.charAt(j)) continue A;
            return i;
        }
        return -1;
    }

    public int strStrIII(String l, String s) {
        if (s != null && l != null && l.length() > 0) {
            int[] next = getNext(s);
            for (int i = 0, j = 0; i - j <= l.length() - s.length();)
                if (j == s.length()) return i - s.length();
                else if (j == -1 || l.charAt(i) == s.charAt(j)) {
                    i++;
                    j++;
                } else j = next[j];
        }
        return -1;
    }

    private int[] getNext(String s) {
        int[] next = new int[s.length() + 1];
        next[0] = -1;
        for (int i = 1, j = 0; i < s.length() - 1;) {
            for (; j > -1 && s.charAt(i) != s.charAt(j); j = next[j]);
            if (s.charAt(++i) == s.charAt(++j)) next[i] = next[j];
            else next[i] = j;
        }
        return next;
    }

    @Test
    public void test() {
        Assert.assertEquals(-1, strStr("mississippi", "sippia"));
        Assert.assertEquals(-1, strStrII("mississippi", "sippia"));
        Assert.assertEquals(-1, strStrIII("mississippi", "sippia"));

        Assert.assertEquals(6, strStr("mississippia", "sippia"));
        Assert.assertEquals(6, strStrII("mississippia", "sippia"));
        Assert.assertEquals(6, strStrIII("mississippia", "sippia"));
    }
}
