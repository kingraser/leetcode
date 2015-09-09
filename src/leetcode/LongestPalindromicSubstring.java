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
    P[ i, j ] ← ( P[ i+1, j-1 ] and Si = Sj )
    显然，如果一个子串是回文串，并且如果从它的左右两侧分别向外扩展的一位也相等，那么这个子串就可以从左右两侧分别向外扩展一位。
    
    其中的base case是
    P[ i, i ] ← true
    P[ i, i+1 ] ← ( Si = Si+1 )
    复杂度O(N²)
    
    解法2
    时间复杂度为O(N²)的算法-从中间向两边展开
    
    回文字符串显然有个特征是沿着中心那个字符轴对称。比如aha沿着中间的h轴对称，a沿着中间的a轴对称。那么aa呢？沿着中间的空字符''轴对称。
    所以对于长度为奇数的回文字符串，它沿着中心字符轴对称，对于长度为偶数的回文字符串，它沿着中心的空字符轴对称。
    对于长度为N的候选字符串，我们需要在每一个可能的中心点进行检测以判断是否构成回文字符串，这样的中心点一共有2N-1个(2N-1=N-1 + N)。
    检测的具体办法是，从中心开始向两端展开，观察两端的字符是否相同。
    
    解法3    
    On的解法    
    Manacher’s algorithm
    首先用一个非常巧妙的方式,将所有可能的奇数/ 偶数长度的回文子串都转换成了奇数长度:
    在每个字符的两边都插入一个特殊的符号。
    比如 abba 变成 #a#b#b#a# , aba 变成 #a#b#a# 。 
    为了进一步减少编码的复杂度,可以在字符串的开始加入另一个特殊字符,这样就不用特殊处理越界问题。
    比如$#a#b#a# 。
    下面以字符串12212321 为例,经过上一步,变成了 S[] = "$#1#2#2#1#2#3#2#1#";
    然后用一个数组 P[i] 来记录以字符S[i] 为中心的最长回文子串向左/ 右扩张的长度(包括S[i] )。
    比如S和P的对应关系:
    S # 1 # 2 # 2 # 1 # 2 # 3 # 2 # 1 #
    P 1 2 1 2 5 2 1 4 1 2 1 6 1 2 1 2 1
    (p.s. 可以看出,P[i]-1 正好是原字符串中回文串的总长度)
        
    那么怎么计算P[i] 呢?该算法增加两个辅助变量(其实一个就够了,两个更清晰)id 和mx。
    其中id 表示最大回文子串中心的位置,mx则为id+P[id] ,也就是最大回文子串的边界。
    然后可以得到一个非常神奇的结论,这个算法的关键点就在这里了:
    如果mx > i ,那么P[i] >= MIN(P[2* id - i], mx - i) 。
    实际上如果把它写得复杂一点,理解起来会简单很多
    
    //记j = 2 * id - i ,也就是说 j 是 i 关于 id 的对称点。
    if (mx - i > P[j])
        P[i] = P[j];
    else // P[j] >= mx - i
        P[i] = mx - i; // P[i] >= mx - i ,取最小值,之后再匹配更新。
        
    当 mx - i > P[j] 的时候,以S[j] 为中心的回文子串包含在以S[id] 为中心的回文子串中,
    由于 i 和 j 对称,以S[i] 为中心的回文子串必然包含在以S[id] 为中心的回文子串中,
    所以必有 P[i] = P[j] ,
    见下图。
                     j=2×id-i
          mx的对称点 j是i关于id的对称点        id                       i          mx
    ------|----------|------------------------|------------------------|----------|------
               ------|------                                     ------|------
                     
    当 P[j] >= mx - i 的时候,以S[j] 为中心的回文子串不一定完全包含于以S[id] 为中心的回文子串中
    但是基于对称性可知,下图中两个#之间的部分是相同的,也就是说以S[i] 为中心的回文子串,
    其向右至少会扩张到mx 的位置,也就是说 P[i] >= mx - i 。
    至于mx之后的部分是否对称,就只能老老实实去匹配了。
    
                     j=2×id-i
          mx的对称点 j是i关于id的对称点        id                       i          mx
    ------|----------|------------------------|------------------------|----------|------
     -----#----------|----------#-----                      #----------|----------#
     
    其实，由于 P[id] = mx ,所以 S[id-mx] != S[id+mx] ,
    那么当 P[j] > mx - i 的时候,可以肯定 P[i] = mx - i ,不需要再继续匹配了
    亦即只有P[j] == mx - i的情况需要匹配。
    不过在具体实现的时候即使不考虑这一点，也只是多一次匹配(必然会fail ),
    但是却要多加一个分支,所以上面的代码就不改了。
    */

    //1example code
    public String longestPalindromeI(String s) {
        int longestBegin = 0, maxLen = 1;
        boolean[][] table = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++)
            table[i][i] = true;
        for (int i = 0; i < s.length() - 1; i++)
            if (s.charAt(i) == s.charAt(i + 1)) {
                table[i][i + 1] = true;
                longestBegin = i;
                maxLen = 2;
            }
        for (int len = 3; len <= s.length(); len++)
            for (int i = 0, j = i + len - 1; j < s.length(); i++, j = i + len - 1)
                if (s.charAt(i) == s.charAt(j) && table[i + 1][j - 1]) {
                    table[i][j] = true;
                    longestBegin = i;
                    maxLen = len;
                }
        return s.substring(longestBegin, longestBegin + maxLen);
    }

    //2example code     
    String expandAroundCenter(String s, int l, int r) {
        for (; l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r); l--, r++);
        return l + 1 < r ? s.substring(l + 1, r) : "";
    }

    String longestPalindromeII(String s) {
        if (s.length() == 0) return "";
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length() - 1; i++) {
            String p1 = expandAroundCenter(s, i, i);
            if (p1.length() > longest.length()) longest = p1;
            String p2 = expandAroundCenter(s, i, i + 1);
            if (p2.length() > longest.length()) longest = p2;
        }
        return longest;
    }

    //3example code
    private String preProcess(String s) {
        StringBuilder sb = new StringBuilder("^");
        for (int i = 0; i < s.length(); sb.append("#").append(s.charAt(i)), i++);
        return sb.append(sb.length() == 1 ? "$" : "#$").toString();
    }

    private String longestPalindromeIII(String s) {
        String t = preProcess(s);
        int[] p = new int[t.length()];
        int c = 0, r = 0, maxC = 0, maxL = 0;
        for (int i = 1, i1 = 2 * c - i; i < t.length() - 1; i++, i1 = 2 * c - i) {
            if (r > i) p[i] = Math.min(r - i, p[i1]);
            if (r < i || p[i1] == r - i) for (; t.charAt(i + 1 + p[i]) == t.charAt(i - 1 - p[i]); p[i]++);
            if (i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
            if (p[i] > maxL) {
                maxC = i;
                maxL = p[i];
            }
        }
        return s.substring((maxC - 1 - maxL) / 2, (maxC - 1 + maxL) / 2);
    }

    @Test
    public void test() {
        Assert.assertEquals("aba", longestPalindromeI("abadd"));
        Assert.assertEquals("aba", longestPalindromeII("abadd"));
        Assert.assertEquals("aba", longestPalindromeIII("abadd"));
    }

}
