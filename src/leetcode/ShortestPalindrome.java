/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月17日<p>
//-------------------------------------------------------
public class ShortestPalindrome {

    /*
    Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
    Find and return the shortest palindrome you can find by performing this transformation.
    
    For example:
    
    Given "aacecaaa", return "aaacecaaa".    
    Given "abcd", return "dcbabcd".
    */

    /*
    找到以s[0]开头的最长回文子串,记为s(0,i+1)
    返回s(i+1).reverse + s
    */

    public String shortestPalindrome(String s) {
        int i;
        for (i = s.length() - 1; i > 0 && isNotPalindrome(s, i); i--);
        return new StringBuilder(s.substring(i + 1)).reverse().append(s).toString();
    }

    private boolean isNotPalindrome(String s, int end) {
        for (int i = 0; i < end; i++, end--)
            if (s.charAt(i) != s.charAt(end)) return true;
        return false;
    }

    public String shortestPalindromeII(String s) {
        String t = preProcess(s);
        int[] p = new int[t.length()];
        int c = 0, r = 0;//center radius 
        for (int i = 1, i1 = 2 * c - i; i < t.length() - 1; i++, i1 = 2 * c - i) {
            if (r > i) p[i] = Math.min(r - i, p[i1]);
            if (r < i || p[i1] == r - i) for (; t.charAt(i + 1 + p[i]) == t.charAt(i - 1 - p[i]); p[i]++);
            if (i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
        }
        for (c = p.length - 1; c >= 0 && p[c] + 1 != c; c--);
        return new StringBuilder(s.substring(p[c])).reverse().append(s).toString();
    }

    private String preProcess(String s) {
        StringBuilder sb = new StringBuilder("^");
        for (int i = 0; i < s.length(); sb.append("#").append(s.charAt(i)), i++);
        return sb.append(sb.length() == 1 ? "$" : "#$").toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("aaacecaaa", shortestPalindrome("aacecaaa"));
        Assert.assertEquals("dcbabcd", shortestPalindrome("abcd"));

        Assert.assertEquals("aaacecaaa", shortestPalindromeII("aacecaaa"));
        Assert.assertEquals("dcbabcd", shortestPalindromeII("abcd"));
    }

}
