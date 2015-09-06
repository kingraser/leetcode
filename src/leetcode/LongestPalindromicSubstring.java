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
public class LongestPalindromicSubstring {
    /*
    解法1
    动态规划
    
    Define P[ i, j ] ← true iff the substring Si … Sj is a palindrome, otherwise false.    
    P[ i, j ] ← ( P[ i+1, j-1 ] and Si = Sj ) ，显然，如果一个子串是回文串，并且如果从它的左右两侧分别向外扩展的一位也相等，那么这个子串就可以从左右两侧分别向外扩展一位。
    
    其中的base case是
    P[ i, i ] ← true
    P[ i, i+1 ] ← ( Si = Si+1 )
    复杂度On^2
    
    解法2
    时间复杂度为O(N²)的算法-从中间向两边展开
    
    回文字符串显然有个特征是沿着中心那个字符轴对称。比如aha沿着中间的h轴对称，a沿着中间的a轴对称。那么aa呢？沿着中间的空字符''轴对称。
    所以对于长度为奇数的回文字符串，它沿着中心字符轴对称，对于长度为偶数的回文字符串，它沿着中心的空字符轴对称。
    对于长度为N的候选字符串，我们需要在每一个可能的中心点进行检测以判断是否构成回文字符串，这样的中心点一共有2N-1个(2N-1=N-1 + N)。
    检测的具体办法是，从中心开始向两端展开，观察两端的字符是否相同。
    
    解法3    
    On的解法    
    http://articles.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html    
     */

    public static String longestPalindrome(String s) {
        if (s.equals(new StringBuilder().append(s).reverse().toString())) return s;
        boolean[][] array = new boolean[s.length()][s.length()];
        bulid(s, array);
        return get(s, array);
    }

    private static String get(String s, boolean[][] array) {
        for (int k = 1; true; k++)
            for (int i = 0, j = s.length() - k + i; i < k; i++, j = s.length() - k + i)
                if (array[i][j]) return s.substring(i, j + 1);
    }

    private static void bulid(String s, boolean[][] array) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++)
            array[i][i] = true;
        for (int i = 0; i < s.length() - 1; i++)
            array[i][i + 1] = chars[i] == chars[i + 1];

        for (int j = 2, tempj = j; j < s.length(); j++, tempj = j)
            for (int i = 0; i < s.length() - j; i++, tempj++)
                array[i][tempj] = array[i + 1][tempj - 1] && chars[i] == chars[tempj];
    }

    public String longestPalindromeII(String s) {
        if (s.equals(new StringBuilder().append(s).reverse().toString())) return s;
        String longest = "";
        for (int i = 0; i < 2 * s.length() - 1; i++) {
            String sub = get(s, i);
            if (sub.length() > longest.length()) longest = sub;
        }
        return longest;
    }

    private String get(String s, int start) {
        StringBuilder sb = new StringBuilder();
        if (start % 2 == 0) {
            start /= 2;
            sb.append(s.charAt(start));
            int i = 1;
            while (start - i > -1 && start + i < s.length() && s.charAt(start - i) == s.charAt(start + i)) {
                sb.append(s.charAt(start + i));
                sb.insert(0, s.charAt(start - i));
                i++;
            }
        } else {
            start /= 2;
            int i = 0;
            while (start - i > -1 && start + 1 + i < s.length() && s.charAt(start - i) == s.charAt(start + 1 + i)) {
                sb.append(s.charAt(start - i));
                sb.insert(0, s.charAt(start + 1 + i));
                i++;
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("aba", longestPalindrome("abade"));
    }

}
