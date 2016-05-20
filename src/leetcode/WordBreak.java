/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class WordBreak {
    /*
    Given a string s and a dictionary of words dict, 
    determine if s can be segmented into a space-separated sequence of one or more dictionary words.
    
    For example, given
    s = "leetcode",
    dict = ["leet", "code"].
    
    Return true because "leetcode" can be segmented as "leet code". 
    */

    /*
            设状态为f(i),表示 s[0,i]是否可以分词,则状态转移方程为
    f(i) = any_of(f(j)&&s[j + 1, i] ∈ dict), 0 ≤ j < i
    */

    @Test
    public void test() {
        Assert.assertTrue(wordBreak("leetcode", Sets.newHashSet("leet", "code")));
    }

    public boolean wordBreak(String s, Set<String> dict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true; // 空字符串
        for (int i = 1, size = s.length(); i <= size; ++i)
            for (int j = i - 1; !f[i] && j >= 0; --j)
                if (f[j] && dict.contains(s.substring(j, i))) f[i] = true;
        return f[s.length()];
    }
}
