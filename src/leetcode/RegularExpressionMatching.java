/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class RegularExpressionMatching {

    /*
    1作弊
    2递归
    3动规
    dp[i][j]表示字串 s.subString(0,i), p.subString(0,j) 是否可以匹配
    dp[i][j]=
        if(p.charAt(j - 1) != '*') 
            matches(s.charAt(i - 1), p.charAt(j - 1)) && dp[i - 1][j - 1] && i > 0
        else
            j >= 2 && (dp[i][j - 2] || (i > 0 && matches(s.charAt(i - 1), p.charAt(j - 2)) && dp[i - 1][j]))
    */

    public boolean isMatch(String s, String p) {
        return s.matches(p);
    }

    public boolean isMatchII(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() > 1 && p.charAt(1) == '*') return isMatch(s, p.substring(2))
                || (p.charAt(0) == '.' && !s.isEmpty() || s.charAt(0) == p.charAt(0)) && isMatch(s.substring(1), p);
        if (p.charAt(0) == '.') return !s.isEmpty() && isMatch(s.substring(1), p.substring(1));
        return s.charAt(0) == p.charAt(0) && isMatch(s.substring(1), p.substring(1));
    }

    public boolean isMatchIII(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s.length(); i++)
            for (int j = 1; j <= p.length(); j++)
                if (p.charAt(j - 1) != '*')
                    dp[i][j] = i > 0 && matches(s.charAt(i - 1), p.charAt(j - 1)) && dp[i - 1][j - 1];
                else dp[i][j] = j >= 2
                        && (dp[i][j - 2] || (i > 0 && matches(s.charAt(i - 1), p.charAt(j - 2)) && dp[i - 1][j]));
        return dp[s.length()][p.length()];
    }

    private boolean matches(char c, char p) {
        return p == '.' ? true : c == p;
    }

    @Test
    public void test() {
        Assert.assertFalse(isMatch("aa", "a"));
        Assert.assertTrue(isMatch("aa", "aa"));
        Assert.assertFalse(isMatch("aaa", "aa"));
        Assert.assertTrue(isMatch("aa", "a*"));
        Assert.assertTrue(isMatch("aa", ".*"));
        Assert.assertTrue(isMatch("ab", ".*"));
        Assert.assertTrue(isMatch("aab", "c*a*b"));
        Assert.assertTrue(isMatch("aab", "a*b"));
    }

}
