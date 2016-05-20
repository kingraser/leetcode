/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class WordBreakII {

    /*
    Given a string s and a dictionary of words dict, 
    add spaces in s to construct a sentence where each word is a valid dictionary word.
    
    Return all such possible sentences.
    
    For example, given
    s = "catsanddog",
    dict = ["cat", "cats", "and", "sand", "dog"]. 
    A solution is ["cats and dog", "cat sand dog"]. 
    */

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList("cat sand dog", "cats and dog"),
                wordBreak("catsanddog", Sets.newHashSet("cat", "cats", "and", "sand", "dog")));
    }

    public List<String> wordBreak(String s, Set<String> dic) {
        boolean[] f = new boolean[s.length() + 1];//长度为n的字符串有n+1个隔板
        boolean[][] prev = new boolean[s.length() + 1][s.length()];//prev[i][j]为true,表示s[j, i)是一个合法单词,可以从j处切开 第一行未用
        f[0] = true;
        for (int i = 1; i <= s.length(); ++i)
            for (int j = i - 1; j >= 0; --j)
                if (f[j] && dic.contains(s.substring(j, i))) {
                    f[i] = true;
                    prev[i][j] = true;
                }
        List<String> result = new LinkedList<>();
        genPath(s, prev, s.length(), new ArrayList<>(s.length()), result);
        return result;
    }

    private void genPath(String s, boolean[][] prev, int cur, List<String> path, List<String> result) {// DFS 遍历树,生成路径
        if (cur == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = path.size() - 1; i >= 0; i--)
                sb.append(path.get(i)).append(" ");
            result.add(sb.deleteCharAt(sb.length() - 1).toString());
        }
        for (int i = 0; i < s.length(); i++)
            if (prev[cur][i]) {
                path.add(s.substring(i, cur));
                genPath(s, prev, i, path, result);
                if (!path.isEmpty()) path.remove(path.size() - 1);
            }
    }
}
