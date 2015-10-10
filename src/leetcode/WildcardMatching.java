/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月10日<p>
//-------------------------------------------------------
public class WildcardMatching {
    /*
    Implement wildcard pattern matching with support for '?' and '*'.
    
    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).
    
    The matching should cover the entire input string (not partial).
    
    The function prototype should be:
    bool isMatch(const char *s, const char *p)
    
    Some examples:
    isMatch("aa","a") → false
    isMatch("aa","aa") → true
    isMatch("aaa","aa") → false
    isMatch("aa", "*") → true
    isMatch("aa", "a*") → true
    isMatch("ab", "?*") → true
    isMatch("aab", "c*a*b") → false    
    */

    public boolean isMatch(String s, String p) {
        int sPointer = 0, pPointer = 0, match = 0, starIdx = -1;
        while (sPointer < s.length()) {
            // advancing both pointers
            if (pPointer < p.length() && (p.charAt(pPointer) == '?'
                    || (s.charAt(sPointer) != '*' && s.charAt(sPointer) == p.charAt(pPointer)))) {
                sPointer++;
                pPointer++;
            }
            // * found, only advancing pattern pointer
            else if (pPointer < p.length() && p.charAt(pPointer) == '*') {
                starIdx = pPointer;
                match = sPointer;
                pPointer++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1) {
                pPointer = starIdx + 1;
                match++;
                sPointer = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (pPointer < p.length() && p.charAt(pPointer) == '*')
            pPointer++;

        return pPointer == p.length();
    }
}
